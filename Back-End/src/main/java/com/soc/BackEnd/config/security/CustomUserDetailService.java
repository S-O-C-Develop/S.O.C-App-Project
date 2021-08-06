package com.soc.backend.config.security;

import com.soc.backend.account.Account;
import com.soc.backend.account.AccountRepository;
import com.soc.backend.config.advice.exception.CustomException;
import com.soc.backend.config.advice.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String studentId) {
        Optional<Account> optionalAccount = accountRepository.findByStudentId(studentId);
        if (!optionalAccount.isPresent()) throw new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND);
        return new CustomUserDetails(optionalAccount.get());
    }

}
