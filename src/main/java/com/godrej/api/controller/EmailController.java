package com.godrej.api.controller;

import com.godrej.api.model.EmailRequest;
import com.godrej.api.model.TransCustomer;
import com.godrej.api.repository.CustomerRepository;
import com.godrej.api.repository.PaymentRepository;
import com.godrej.api.service.EmailService;
import jakarta.mail.*;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

@CrossOrigin

//@RestController
//@RequestMapping("/api")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ResourceLoader resourceLoader;


    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PaymentRepository  paymentRepository;

    @Autowired
    private EmailService emailService;
    @Value("${spring.mail.username}")
    private String from;

    public String sendEmail(TransCustomer customer ,String trackingId) throws MessagingException {


        // Sender's email credentials
        String senderEmail = "customercare.gpl@godrejproperties.com";
        String senderPassword = "";//frgtjnyucxftjbckiuyt

        // Receiver's email
        String receiverEmail = customer.getEmailid();

        // SMTP server configuration
        String smtpHost = "smtp.godrejinds.com";
        int smtpPort = 25; // Port for TLS

        // Email subject
        String subject = "Godrej Email";

        // Load HTML content from file
        String htmlContent = "<!doctypehtml>\n" +
                "    <html xmlns=http://www.w3.org/1999/xhtml>\n" +
                "    <meta content=\"text/html; charset=UTF-8\" http-equiv=Content-Type>\n" +
                "    <meta content=\"IE=edge\" http-equiv=X-UA-Compatible>\n" +
                "    <meta content=\"width=device-width,initial-scale=1\" name=viewport>\n" +
                "    <link\n" +
                "        href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&family=Satisfy&display=swap\"\n" +
                "        rel=stylesheet>\n" +
                "    <link href=https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css rel=stylesheet>\n" +
                "    <title></title>\n" +
                "    <style>\n" +
                "        html {\n" +
                "            width: 100%\n" +
                "        }\n" +
                "\n" +
                "        .queries p {\n" +
                "            color: #497e6b;\n" +
                "            font-size: 12px\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            -webkit-text-size-adjust: none;\n" +
                "            -ms-text-size-adjust: none;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background: #eceff3;\n" +
                "            height: 100vh\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            border-spacing: 0;\n" +
                "            table-layout: auto;\n" +
                "            margin: 0 auto\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            display: block !important;\n" +
                "            overflow: hidden !important\n" +
                "        }\n" +
                "\n" +
                "        .yshortcuts a {\n" +
                "            border-bottom: none !important\n" +
                "        }\n" +
                "\n" +
                "        img:hover {\n" +
                "            opacity: .9 !important\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            color: #48bdb5;\n" +
                "            text-decoration: none\n" +
                "        }\n" +
                "\n" +
                "        .textbutton a {\n" +
                "            font-family: Poppins, sans-serif !important\n" +
                "        }\n" +
                "\n" +
                "        .btn-link a {\n" +
                "            color: #fff !important\n" +
                "        }\n" +
                "\n" +
                "        .details {\n" +
                "            font-family: Poppins, sans-serif;\n" +
                "            font-size: 15px;\n" +
                "            padding-top: 5px\n" +
                "        }\n" +
                "\n" +
                "        .details-type {\n" +
                "            list-style-type: none;\n" +
                "            padding: 0;\n" +
                "            font-family: Poppins, sans-serif;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 25px\n" +
                "        }\n" +
                "\n" +
                "        li {\n" +
                "            margin: 0 !important\n" +
                "        }\n" +
                "\n" +
                "        .submitted p {\n" +
                "            font-family: Poppins, sans-serif;\n" +
                "            font-size: 15px;\n" +
                "            margin: 0\n" +
                "        }\n" +
                "\n" +
                "        .thankyou {\n" +
                "            font-family: Poppins, sans-serif;\n" +
                "            color: #27374c;\n" +
                "            font-size: 24px;\n" +
                "            font-weight: 700\n" +
                "        }\n" +
                "\n" +
                "        .intro {\n" +
                "            font-family: Poppins, sans-serif;\n" +
                "            color: #414a51;\n" +
                "            font-size: 15px;\n" +
                "            text-align: left;\n" +
                "            text-align: justify\n" +
                "        }\n" +
                "\n" +
                "        .marg-0 {\n" +
                "            margin: 0\n" +
                "        }\n" +
                "\n" +
                "        .queries {\n" +
                "            font-family: Poppins, sans-serif;\n" +
                "            color: #333;\n" +
                "            font-size: 12px;\n" +
                "            line-height: 20px\n" +
                "        }\n" +
                "\n" +
                "        .footer-link {\n" +
                "            font-family: Poppins, sans-serif;\n" +
                "            color: #fff;\n" +
                "            font-size: 15px\n" +
                "        }\n" +
                "\n" +
                "        .copyright {\n" +
                "            font-size: 13px;\n" +
                "            text-align: center;\n" +
                "            margin: 0\n" +
                "        }\n" +
                "\n" +
                "        .c-orange {\n" +
                "            color: #f4a540\n" +
                "        }\n" +
                "\n" +
                "        @media only screen and (max-width:640px) {\n" +
                "            body {\n" +
                "                margin: 0;\n" +
                "                width: auto !important;\n" +
                "                font-family: Poppins, sans-serif !important\n" +
                "            }\n" +
                "\n" +
                "            .table-inner {\n" +
                "                width: 90% !important;\n" +
                "                max-width: 90% !important\n" +
                "            }\n" +
                "\n" +
                "            .table-full {\n" +
                "                width: 100% !important;\n" +
                "                max-width: 100% !important;\n" +
                "                text-align: left !important\n" +
                "            }\n" +
                "\n" +
                "            .details-type {\n" +
                "                font-size: 14px !important\n" +
                "            }\n" +
                "\n" +
                "            .copyright {\n" +
                "                font-size: 11px !important;\n" +
                "                color: #fff\n" +
                "            }\n" +
                "\n" +
                "            .submitted p {\n" +
                "                font-size: 12px\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        @media only screen and (max-width:479px) {\n" +
                "            body {\n" +
                "                width: auto !important;\n" +
                "                font-family: Poppins, sans-serif !important\n" +
                "            }\n" +
                "\n" +
                "            .full {\n" +
                "                width: 100%\n" +
                "            }\n" +
                "\n" +
                "            .table-inner {\n" +
                "                width: 90% !important;\n" +
                "                text-align: center !important\n" +
                "            }\n" +
                "\n" +
                "            .table-full {\n" +
                "                width: 100% !important;\n" +
                "                max-width: 100% !important;\n" +
                "                text-align: left !important\n" +
                "            }\n" +
                "\n" +
                "            .details-type {\n" +
                "                font-size: 14px !important\n" +
                "            }\n" +
                "\n" +
                "            .copyright {\n" +
                "                font-size: 10px !important\n" +
                "            }\n" +
                "\n" +
                "            .submitted p {\n" +
                "                font-size: 12px\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            text-decoration: none;\n" +
                "            font-family: Poppins, sans-serif\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "    <body class=body>\n" +
                "        <table border=0 cellpadding=0 cellspacing=0 align=center class=full width=50% bgcolor=#F1F7E0 height=50%>\n" +
                "            <tr>\n" +
                "                <td align=center>\n" +
                "                    <table border=0 cellpadding=0 cellspacing=0 align=center class=table-inner>\n" +
                "                        <tr>\n" +
                "                            <td align=center>\n" +
                "                                <table border=0 cellpadding=0 cellspacing=0 width=100%>\n" +
                "                                    <tr>\n" +
                "                                        <td class=\"d-flex justify-content-end\"\n" +
                "                                            style=line-height:0;margin-top:10px;margin-right:20px><img alt=Logo\n" +
                "                                                src=godrej-logo1.png\n" +
                "                                                style=display:block;line-height:0;border:0;width:30%>\n" +
                "                                </table>\n" +
                "                    </table>\n" +
                "            <tr>\n" +
                "                <td align=left>\n" +
                "                    <table border=0 cellpadding=0 cellspacing=0 align=center class=table-inner\n" +
                "                        style=width:90%;margin-top:40px>\n" +
                "                        <tr>\n" +
                "                            <td align=left style=padding-left:15px;padding-right:15px>\n" +
                "                                <table border=0 cellpadding=0 cellspacing=0 align=left width=100%>\n" +
                "                                    <tr>\n" +
                "                                        <td align=left class=queries>\n" +
                "                                            <p style=\"line-height:1.4;margin:0 0 10px 0;text-align:left\">Dear Name!\n" +
                "                                            <p style=\"line-height:1.4;margin:0 0 10px 0;text-align:left\">Greetings from\n" +
                "                                                Godrej Properties\n" +
                "                                            <p style=\"line-height:1.4;margin:0 0 10px 0;text-align:left\">Your EOI,\n" +
                "                                                acknowledgement {reference code} is against following details:\n" +
                "                                            <ul style=color:#497e6b>\n" +
                "                                                <li>"+customer.getMobile()+"\n" +
                "                                                 <li>"+customer.getProjectName()+"\n" +
                "                                                 <li>"+customer.getTypologypreference()+"\n" +
                "                                                <li>"+customer.getEmailid()+"\n" +
                "                                                 <li>"+customer.getAddress()+"\n" +
                "                                                 <li>"+customer.getSource_name()+"\n" +
                "                                                 <li>"+customer.getPan()+"\n" +
                "                                                 <li>"+customer.getAadhaarcardnumber()+"\n" +
                "                                                <li>"+trackingId+"\n" +
                "                                            </ul>\n" +
                "                                </table>\n" +
                "                        <tr>\n" +
                "                            <td align=center><img alt=logo-green src=godrejHillviewLogoGreen.png height=100 width=140>\n" +
                "                    </table>\n" +
                "            <tr>\n" +
                "                <td align=center>\n" +
                "                    <table border=0 cellpadding=0 cellspacing=0 align=center class=\"table-inner mt-3\"\n" +
                "                        style=max-width:580px width=580>\n" +
                "                        <tr>\n" +
                "                            <td>\n" +
                "                                <table border=0 cellpadding=0 cellspacing=0 align=center width=100%\n" +
                "                                    style=\"border-top:1px solid #000\">\n" +
                "                                    <tr>\n" +
                "                                        <td style=display:flex><img alt=\"\" src=qr.png height=50 width=50>\n" +
                "                                        <td class=footer-link height=30>\n" +
                "                                            <p style=color:#000!important;margin:0!important;font-size:10px>The project\n" +
                "                                                is registered under MahaRERA bearing No. P52000055348 available at <a\n" +
                "                                                    href=http://maharera.mahaonline.gov.in>http://maharera.mahaonline.gov.in</a>\n" +
                "                                                . The project is being developed by Godrej Projects Development Limited.\n" +
                "                                                This audio visual contains artist's impressions and stock footages. No\n" +
                "                                                warranty is expressly or impliedly given that the completed development\n" +
                "                                                will comply in any degree as depicted. The Sale is subject to terms of\n" +
                "                                                Application Form and Agreement for Sale and Deed of Conveyance. All\n" +
                "                                                specifications of the plot shall be as per the final agreement between\n" +
                "                                                the Parties. Recipients are advised to apprise themselves of the\n" +
                "                                                necessary and relevant information of the project/offer prior to making\n" +
                "                                                any purchase decisions. The official website of Godrej Properties Ltd.\n" +
                "                                                is <a href=www.godrejproperties.com>www.godrejproperties.com</a>. Please\n" +
                "                                                do not rely on the information provided on any other website.The\n" +
                "                                                infrastructure facilities mentioned are proposed to be developed by the\n" +
                "                                                Government and other authorities and we cannot predict the timing or the\n" +
                "                                                actual provisioning of these facilities, as the same is beyond our\n" +
                "                                                control. We shall not be responsible and liable for any delay or\n" +
                "                                                non-provisioning of the same. Acknowledgement reference number\n" +
                "                                                generation is not the EOI confirmation nor priority number. Confirmation\n" +
                "                                                will be provided post verification of documents. This portal is for EOI\n" +
                "                                                submission by individuals only. For Applicant/s who are Company,\n" +
                "                                                Partnership Form, Limited Liability Partnership (LLP), Trust, Hindu\n" +
                "                                                Undivided Family (HUF), or any other body corporate/ incorporated, they\n" +
                "                                                are requested to contact our sales team directly, who can be reached at\n" +
                "                                                <a\n" +
                "                                                    href=godrejhillviewestate@godrejproperties.com>godrejhillviewestate@godrejproperties.com</a>.\n" +
                "                                </table>\n" +
                "                    </table>\n" +
                "            <tr>\n" +
                "                <td height=10>\n" +
                "        </table>\n" +
                "        <script src=https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js></script>";

        // Create properties for the SMTP session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create session
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));

            // Set Subject: header field
            message.setSubject(subject);

            // Set HTML content
            message.setContent(htmlContent, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "Email sent successfully!";

    }
}

