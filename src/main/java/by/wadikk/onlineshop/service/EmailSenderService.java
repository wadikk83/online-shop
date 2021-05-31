package by.wadikk.onlineshop.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailSenderService {

    void EmailSenderService(JavaMailSender javaMailSender);

    void sendEmail(SimpleMailMessage email);

}
