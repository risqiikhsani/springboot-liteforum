package com.example.kucing.services;

import org.springframework.stereotype.Component;

@Component("callService")
public class CallService implements NotificationService {
    public void send(String string) {
        System.out.println("Call sent" + string);
    }
}
