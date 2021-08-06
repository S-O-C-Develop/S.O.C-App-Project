package com.soc.backend.account;

import com.soc.backend.account.dto.*;
import com.soc.backend.account.mail.MailService;
import com.soc.backend.account.mail.MessageData;
import com.soc.backend.config.response.ResponseService;
import com.soc.backend.config.response.exception.CustomException;
import com.soc.backend.config.response.exception.CustomExceptionStatus;
import com.soc.backend.config.security.CustomUserDetails;
import com.soc.backend.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailService mailService;

    public Long signUp(SignupDto dto) {

        if(accountRepository.findByEmail(dto.getEmail()).isPresent())
            throw new CustomException(CustomExceptionStatus.DUPLICATED_EMAIL);
        if(accountRepository.findByStudentId(dto.getStudentId()).isPresent()){
            throw new CustomException(CustomExceptionStatus.POST_USERS_EXISTS_STUDENTID);
        }
        if(accountRepository.findByNickname(dto.getStudentId()).isPresent()){
            throw new CustomException(CustomExceptionStatus.DUPLICATED_NICKNAME);
        }


        Account account = Account.createAccount(dto);
        account.changePassword(passwordEncoder.encode(dto.getPassword()));

        Account save = accountRepository.save(account);

        sendEmailToAccount(account);

        return save.getAccountId();
    }

    private void sendEmailToAccount(Account account) {
        MessageData messageData = MessageData.builder()
                .to(account.getEmail())
                .title("S.O.C 회원가입 인증")
                .content("check-valid-email?emailToken="+ account.getEmailToken()+"&email="+ account.getEmail())
                .build();
        mailService.sendMessage(messageData);
    }

    public SignInRes signIn(LoginDto dto) {

        Optional<Account> optionalAccount = accountRepository.findByStudentId(dto.getStudentId());
        if (!optionalAccount.isPresent()) throw new CustomException(CustomExceptionStatus.FAILED_TO_LOGIN);
        Account accountEntity = optionalAccount.get();
        if(!passwordEncoder.matches(dto.getPassword(),accountEntity.getPassword())){
            throw new CustomException(CustomExceptionStatus.FAILED_TO_LOGIN);
        }

        return new SignInRes(accountEntity.getAccountId(), jwtTokenProvider.createToken(accountEntity.getStudentId(),accountEntity.getRole()));

    }

    public ResponseAccountDto getAccount(CustomUserDetails customUserDetails) {
        Account account = customUserDetails.getAccount();
        return new ResponseAccountDto(account);
    }

    public void changePwd(PasswordUpdateDto dto, CustomUserDetails customUserDetails) {
        Account accountEntity = customUserDetails.getAccount();
        if(!passwordEncoder.matches(dto.getBeforePassword(),accountEntity.getPassword())){
            throw new CustomException(CustomExceptionStatus.NOT_CORRECT_PASSWORD);
        }
        accountEntity.changePassword(passwordEncoder.encode(dto.getUpdatePassword()));
    }

    public void changeNickname(String updateNickname, CustomUserDetails customUserDetails) {
        Account account = customUserDetails.getAccount();
        if(updateNickname.length() <8 || updateNickname.length() > 20)
            throw new CustomException(CustomExceptionStatus.POST_USERS_INVALID_NICKNAME);

        if (!updateNickname.equals(account.getNickname())){
            if(accountRepository.findByNickname(updateNickname).isPresent()) throw new CustomException(CustomExceptionStatus.DUPLICATED_NICKNAME);
            account.changeNickname(updateNickname);
        }
        else throw new CustomException(CustomExceptionStatus.DUPLICATED_NICKNAME_SELF);
    }
}
