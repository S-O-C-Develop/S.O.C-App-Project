package com.soc.BackEnd.account;

import com.soc.BackEnd.account.dto.LoginDto;
import com.soc.BackEnd.account.dto.ResponseAccountDto;
import com.soc.BackEnd.account.dto.SignupDto;
import com.soc.BackEnd.api.CommonResponse;
import com.soc.BackEnd.api.ResponseService;
import com.soc.BackEnd.api.SingleResponse;
import com.soc.BackEnd.config.advice.exception.CustomUserNotFoundException;
import com.soc.BackEnd.config.advice.exception.CustomValidationException;
import com.soc.BackEnd.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final JavaMailSender javaMailSender;

    public CommonResponse signUp(SignupDto dto, Errors errors) {

        if(errors.hasErrors()) throw new CustomValidationException();
        if(accountRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new CustomValidationException("email");
        }
        if(accountRepository.findByStudentId(dto.getStudentId()).isPresent()){
            throw new CustomValidationException("studentId");
        }


        Account account = Account.builder()
                .studentId(dto.getStudentId())
                .nickname(dto.getNickname())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .roles(Collections.singletonList("ROLE_USER"))
                .isConfirm(false)
                .emailToken(UUID.randomUUID().toString())
                .build();

        accountRepository.save(account);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("SOC_Project 회원 가입 인증");
        message.setText("check-valid-email?emailToken="+account.getEmailToken()+"&email="+account.getEmail());
        javaMailSender.send(message);

        return responseService.getSuccessResponse();
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
}
