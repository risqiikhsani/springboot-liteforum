package com.example.kucing.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("emailService")
@Primary
public class EmailService implements NotificationService {
    public void send(String string) {
        System.out.println("Email sent" + string);
    }
}
