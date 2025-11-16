package com.citizens.service;

import com.citizens.model.NotificationMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mail;

    public EmailService(JavaMailSender mail) {
        this.mail = mail;
    }

    public void sendTransactionEmail(NotificationMessage msg) {
        try {
            MimeMessage mime = mail.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mime, true);

            helper.setTo(msg.getEmail());
            helper.setSubject("Transaction Notification");
            helper.setText("""
                    Hello,
                    
                    A transaction occurred on your account:
                    
                    Account ID: %s
                    Transaction ID: %s
                    Amount: %.2f
                    Status: %s
                    
                    """.formatted(
                    msg.getAccountId(),
                    msg.getTransactionId(),
                    msg.getAmount(),
                    msg.getStatus()
            ));

            mail.send(mime);
        } catch (MessagingException e) {
            throw new RuntimeException("Could not send email", e);
        }
    }

}