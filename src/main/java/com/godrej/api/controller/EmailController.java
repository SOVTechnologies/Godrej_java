package com.godrej.api.controller;

import com.godrej.api.service.EmailService;
import com.godrej.api.model.EmailRequest;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@CrossOrigin

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;
    @Value("${spring.mail.username}")
    private String from;
    @GetMapping("/send-email")
    //@RequestBody EmailRequest emailRequest
    public String sendEmail() {

        String host = "smtp.gmail.com"; // replace with your SMTP server
        String port = "465"; // replace with your SMTP port, typically 25, 587, or 465 for SSL
        // Set up the properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "false");
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(props);
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testfbb3@gmail.com")); // replace with your email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("testfbb3@gmail.com")); // replace with recipient email
            message.setSubject("Test Email Without Authentication");
            message.setText("This is a test email sent without authentication using Jakarta Mail");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        emailService.sendSimpleMessage("testfbb3@gmail.com", "test", "text");
       return "Email sent successfully!";

    }

    private void sendHtmlEmail(String to, String subject, String name) throws MessagingException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent = "<h1>Hello, " + name + "!</h1><p>This is a test email.</p>";
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }
}

