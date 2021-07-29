package com.soc.backend.account;

import com.soc.backend.account.dto.LoginDto;
import com.soc.backend.account.dto.PasswordUpdateDto;
import com.soc.backend.account.dto.ResponseAccountDto;
import com.soc.backend.account.dto.SignupDto;
import com.soc.backend.account.mail.MailService;
import com.soc.backend.account.mail.MessageData;
import com.soc.backend.api.CommonResponse;
import com.soc.backend.api.ResponseService;
import com.soc.backend.api.SingleResponse;
import com.soc.backend.config.advice.exception.CustomUserNotFoundException;
import com.soc.backend.config.advice.exception.CustomValidationException;
import com.soc.backend.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailService mailService;

    public CommonResponse signUp(SignupDto dto, Errors errors) {

        if(errors.hasErrors()) throw new CustomValidationException();
        if(accountRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new CustomValidationException("email");
        }
        if(accountRepository.findByStudentId(dto.getStudentId()).isPresent()){
            throw new CustomValidationException("studentId");
        }
        if(accountRepository.findByNickname(dto.getStudentId()).isPresent()){
            throw new CustomValidationException("nickname");
        }


        Account account = Account.builder()
                .studentId(dto.getStudentId())
                .nickname(dto.getNickname())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .roles(Collections.singletonList(RoleType.ROLE_USER))
                .isConfirm(false)
                .emailToken(UUID.randomUUID().toString())
                .build();

        accountRepository.save(account);

        sendEmailToAccount(account);

        return responseService.getSuccessResponse();
    }

    private void sendEmailToAccount(Account account) {
        MessageData messageData = MessageData.builder()
                .to(account.getEmail())
                .title("S.O.C 회원가입 인증")
                .content("check-valid-email?emailToken="+ account.getEmailToken()+"&email="+ account.getEmail())
                .build();
        mailService.sendMessage(messageData);
    }

    public SingleResponse<String> signIn(LoginDto dto, Errors errors) {

        if(errors.hasErrors()) throw new CustomValidationException();

        Account accountEntity = accountRepository.findByStudentId(dto.getStudentId()).orElseThrow(CustomUserNotFoundException::new);
        if(!passwordEncoder.matches(dto.getPassword(),accountEntity.getPassword())){
            throw new CustomUserNotFoundException();
        }

        return responseService.getSingleResponse(jwtTokenProvider.createToken(accountEntity.getStudentId(),accountEntity.getRoles()));


    }

    public SingleResponse<ResponseAccountDto> getAccount(String studentId) {
        Account accountEntity =  accountRepository.findByStudentId(studentId).orElseThrow(CustomUserNotFoundException::new);
        return responseService.getSingleResponse(new ResponseAccountDto(accountEntity));
    }

    public SingleResponse<String> changePwd(PasswordUpdateDto dto, Errors errors, String userName) {
        if(errors.hasErrors()) throw new CustomValidationException();
        Account accountEntity = accountRepository.findByStudentId(userName).orElseThrow(CustomUserNotFoundException::new);
        if(!passwordEncoder.matches(dto.getBeforePassword(),accountEntity.getPassword())){
            throw new CustomUserNotFoundException();
        }
        accountEntity.changePassword(passwordEncoder.encode(dto.getUpdatePassword()));
        return responseService.getSingleResponse("비밀번호 변경 완료");
    }

    public SingleResponse<String> changeNickname(String updateNickname, String userName) {
        Account accountEntity = accountRepository.findByStudentId(userName).orElseThrow(CustomUserNotFoundException::new);
        if(updateNickname.length() <8 || updateNickname.length() > 20)
            throw new CustomValidationException();

        if (!updateNickname.equals(accountEntity.getNickname())){
            if(accountRepository.findByNickname(updateNickname).isPresent()) throw new CustomValidationException("nickname");
            accountEntity.changeNickname(updateNickname);
        }
        else if(accountRepository.findByNickname(updateNickname).isPresent()) throw new CustomValidationException("duplication");

        return responseService.getSingleResponse("nickname 변경 완료");
    }
}
