package com.soc.BackEnd.account.mail;

import com.soc.BackEnd.account.Account;
import com.soc.BackEnd.account.AccountRepository;
import com.soc.BackEnd.config.advice.exception.CustomUserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("local")
@RequiredArgsConstructor
@Component
public class ConsoleMailService implements MailService{

    private final AccountRepository accountRepository;

    @Override
    public void sendMessage(MessageData messageData) {
        log.info("send email to -> "+ messageData.getTo());

        Account account = accountRepository.findByEmail(messageData.getTo()).orElseThrow(CustomUserNotFoundException::new);
        account.changeConfirm();

    }
}
