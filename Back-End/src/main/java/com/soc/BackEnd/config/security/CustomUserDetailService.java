package com.soc.BackEnd.config.security;

import com.soc.BackEnd.account.Account;
import com.soc.BackEnd.account.AccountRepository;
import com.soc.BackEnd.config.advice.exception.CustomUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String studentId) {
        Account accountEntity = accountRepository.findByStudentId(studentId).orElseThrow(CustomUserNotFoundException::new);
        return new CustomUserDetails(accountEntity);
    }

}
