package com.citizens.consumer;

import com.citizens.model.NotificationMessage;
import com.citizens.service.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NotificationConsumerTest {

    @Test
    void testKafkaMessageTriggersEmail() {
        EmailService emailService = Mockito.mock(EmailService.class);
        NotificationConsumer listener = new NotificationConsumer(emailService);

        NotificationMessage msg = new NotificationMessage();
        msg.setEmail("test@example.com");

        listener.listen(msg);

        Mockito.verify(emailService, Mockito.times(1))
                .sendTransactionEmail(msg);
    }

}