package com.example.kucing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.kucing.services.EmailService;
import com.example.kucing.services.NotificationService;
import com.example.kucing.services.SmsService;

@Configuration
public class AppConfig {

    @Bean
    public NotificationService notificationService() {
        return new SmsService();
    }
}
