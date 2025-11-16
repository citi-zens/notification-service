package com.citizens.consumer;

import com.citizens.model.NotificationMessage;
import com.citizens.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final EmailService emailService;

    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "transaction-topic", groupId = "notification-service")
    public void listen(NotificationMessage message) {
        emailService.sendTransactionEmail(message);
    }

}