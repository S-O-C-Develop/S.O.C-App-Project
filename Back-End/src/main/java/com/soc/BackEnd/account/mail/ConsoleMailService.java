package com.soc.backend.account.mail;

import com.soc.backend.account.Account;
import com.soc.backend.account.AccountRepository;
import com.soc.backend.config.response.exception.CustomException;
import com.soc.backend.config.response.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Profile("local")
@RequiredArgsConstructor
@Component
public class ConsoleMailService implements MailService{

    private final AccountRepository accountRepository;

    @Override
    public void sendMessage(MessageData messageData) {
        log.info("send email to -> "+ messageData.getTo());
        Optional<Account> optionalAccount = accountRepository.findByEmail(messageData.getTo());
        if (!optionalAccount.isPresent()) throw new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND);
        optionalAccount.get().changeConfirm();
    }
}
