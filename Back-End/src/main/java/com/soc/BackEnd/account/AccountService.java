package com.soc.backend.account;

import com.soc.backend.account.dto.*;
import com.soc.backend.config.response.exception.CustomException;
import com.soc.backend.config.response.exception.CustomExceptionStatus;
import com.soc.backend.config.security.CustomUserDetails;
import com.soc.backend.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long signUp(SignupReq dto) {

        if(accountRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new CustomException(CustomExceptionStatus.DUPLICATED_EMAIL);
        }
        if(accountRepository.findByStudentId(dto.getStudentId()).isPresent()){
            throw new CustomException(CustomExceptionStatus.POST_USERS_EXISTS_STUDENTID);
        }
        if(accountRepository.findByNickname(dto.getStudentId()).isPresent()){
            throw new CustomException(CustomExceptionStatus.DUPLICATED_NICKNAME);
        }


        Account account = Account.createAccount(dto);
        account.changePassword(passwordEncoder.encode(dto.getPassword()));

        Account save = accountRepository.save(account);


        return save.getAccountId();
    }


    public SignInRes signIn(LoginReq dto) {

        Account account = accountRepository.findByStudentId(dto.getStudentId())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.FAILED_TO_LOGIN));
        if(!passwordEncoder.matches(dto.getPassword(),account.getPassword())){
            throw new CustomException(CustomExceptionStatus.FAILED_TO_LOGIN);
        }

        return new SignInRes(account.getAccountId(), jwtTokenProvider.createToken(account.getStudentId(),account.getRole()));

    }

    public ResponseAccountRes getAccount(CustomUserDetails customUserDetails) {
        Account account = customUserDetails.getAccount();
        return new ResponseAccountRes(account);
    }

    public void changePwd(PasswordUpdateReq dto, CustomUserDetails customUserDetails) {
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
