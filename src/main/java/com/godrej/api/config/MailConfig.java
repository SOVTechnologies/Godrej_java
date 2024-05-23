package com.godrej.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.godrejinds.com");
        mailSender.setPort(25);
        mailSender.setUsername("");  // No username
        mailSender.setPassword("");  // No password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        // Uncomment if using TLS
        // props.put("mail.smtp.starttls.enable", "true");
        // Uncomment if using SSL
        // props.put("mail.smtp.ssl.enable", "true");

        props.put("mail.debug", "true");

        return mailSender;
    }
}

