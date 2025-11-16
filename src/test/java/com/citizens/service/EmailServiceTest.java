package com.citizens.service;

import com.citizens.model.NotificationMessage;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class EmailServiceTest {

    @Test
    void testSendTransactionEmail() throws Exception {
        JavaMailSender mailSender = Mockito.mock(JavaMailSender.class);

        // Return a real MimeMessage so Spring can populate it
        MimeMessage mimeMessage = new MimeMessage((Session) null);
        Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        EmailService emailService = new EmailService(mailSender);

        NotificationMessage msg = new NotificationMessage();
        msg.setEmail("test@example.com");
        msg.setAmount(100.00);
        msg.setStatus("SUCCESS");
        msg.setAccountId("12345");
        msg.setTransactionId("tx-001");

        emailService.sendTransactionEmail(msg);

        // Verify an email was sent
        Mockito.verify(mailSender, times(1)).send(any(MimeMessage.class));
    }


}