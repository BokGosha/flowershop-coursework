package com.example.flowershop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.flowershop.services.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Async
    @Override
    public void sendEmail(String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(username);
        message.setTo("goshik04r@gmail.com");
        message.setSubject("Заказ");
        message.setText(text);

        javaMailSender.send(message);
    }
}
