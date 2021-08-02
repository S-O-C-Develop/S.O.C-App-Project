package com.soc.backend.account.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Profile("dev")
@RequiredArgsConstructor
@Slf4j
@Component
public class SendMailService implements MailService{

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMessage(MessageData messageData) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,false,"UTF-8");
            helper.setTo(messageData.getTo());
            helper.setSubject(messageData.getTitle());
            helper.setText(messageData.getContent());
            javaMailSender.send(message);
            log.info("send email to -> "+ messageData.getTo());
        }catch (MessagingException e) {
          log.info("fail to send email",e);
        }
    }
}
