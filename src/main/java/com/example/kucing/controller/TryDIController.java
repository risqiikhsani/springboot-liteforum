package com.example.kucing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.kucing.services.NotificationService;

@Controller
public class TryDIController {
    private final NotificationService notificationService;

    // use @Qualifier if you have multiple implementations of the same interface
    // but if there is @Primary on the implementation to mark one of them as the default choice, you don't need to use @Qualifier
    @Autowired
    public TryDIController(@Qualifier("emailService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/trydi")
    public String sendNotification() {
        notificationService.send("Hello from TryDIController");
        return "test";
    }
}
