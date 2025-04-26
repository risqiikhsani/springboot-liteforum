package com.example.kucing.services;

import org.springframework.stereotype.Component;

@Component("smsService")
public class SmsService implements NotificationService {
    public void send(String string) {
        System.out.println("Sms sent" + string);
    }
}
