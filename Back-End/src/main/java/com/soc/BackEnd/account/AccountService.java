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
import com.soc.backend.config.advice.exception.CustomException;
import com.soc.backend.config.advice.exception.CustomExceptionStatus;
import com.soc.backend.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
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

    public CommonResponse signUp(SignupDto dto) {

        if(accountRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new CustomException(CustomExceptionStatus.EMAIL_DUPLICATION);
        }
        if(accountRepository.findByStudentId(dto.getStudentId()).isPresent()){
            throw new CustomException(CustomExceptionStatus.STUDENT_ID_DUPLICATE);
        }
        if(accountRepository.findByNickname(dto.getStudentId()).isPresent()){
            throw new CustomException(CustomExceptionStatus.NICKNAME_DUPLICATION);
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

    public SingleResponse<String> signIn(LoginDto dto) {

        Optional<Account> optionalAccount = accountRepository.findByStudentId(dto.getStudentId());
        if (!optionalAccount.isPresent()) throw new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND);
        Account accountEntity = optionalAccount.get();
        if(!passwordEncoder.matches(dto.getPassword(),accountEntity.getPassword())){
            throw new CustomException(CustomExceptionStatus.PASSWORD_NOT_CORRECT);
        }

        return responseService.getSingleResponse(jwtTokenProvider.createToken(accountEntity.getStudentId(),accountEntity.getRoles()));


    }

    public SingleResponse<ResponseAccountDto> getAccount(String studentId) {
        Optional<Account> optionalAccount = accountRepository.findByStudentId(studentId);
        if (!optionalAccount.isPresent()) {
            throw new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND);
        }
        return responseService.getSingleResponse(new ResponseAccountDto(optionalAccount.get()));
    }

    public SingleResponse<String> changePwd(PasswordUpdateDto dto, String userName) {
        Optional<Account> optionalAccount = accountRepository.findByStudentId(userName);
        if (!optionalAccount.isPresent()) {
            throw new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND);
        }
        Account accountEntity = optionalAccount.get();
        if(!passwordEncoder.matches(dto.getBeforePassword(),accountEntity.getPassword())){
            throw new CustomException(CustomExceptionStatus.PASSWORD_NOT_CORRECT);
        }
        accountEntity.changePassword(passwordEncoder.encode(dto.getUpdatePassword()));
        return responseService.getSingleResponse("비밀번호 변경 완료");
    }

    public SingleResponse<String> changeNickname(String updateNickname, String userName) {
        Optional<Account> optionalAccount = accountRepository.findByStudentId(userName);
        if (!optionalAccount.isPresent()) throw new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND);
        Account accountEntity = optionalAccount.get();
        if(updateNickname.length() <8 || updateNickname.length() > 20)
            throw new CustomException(CustomExceptionStatus.NICKNAME_VALIDATION_ERROR);

        if (!updateNickname.equals(accountEntity.getNickname())){
            if(accountRepository.findByNickname(updateNickname).isPresent()) throw new CustomException(CustomExceptionStatus.NICKNAME_DUPLICATION_SELF);;
            accountEntity.changeNickname(updateNickname);
        }
        else if(accountRepository.findByNickname(updateNickname).isPresent()) throw new CustomException(CustomExceptionStatus.NICKNAME_DUPLICATION);

        return responseService.getSingleResponse("nickname 변경 완료");
    }
}
