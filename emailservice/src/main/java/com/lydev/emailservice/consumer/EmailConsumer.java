package com.lydev.emailservice.consumer;

import com.lydev.emailservice.dto.EmailMessageRabbit;
import com.lydev.emailservice.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailServiceImpl emailService;

    @Async
    @RabbitListener(queues = "email.queue")
    public void receiveEmailMessage(EmailMessageRabbit message) {
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        emailService.sendHtmlEmail(message.getTo(), message.getName());    }
}
