package com.project.railway.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }

    @Async
    public void sendHTMLMessage(MimeMessage message){ javaMailSender.send(message);}
}
