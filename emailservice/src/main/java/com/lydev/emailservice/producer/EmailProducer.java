package com.lydev.emailservice.producer;

import com.lydev.emailservice.dto.EmailMessageRabbit;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendEmailToQueue(EmailMessageRabbit message) {
        rabbitTemplate.convertAndSend("email.exchange", "email.routing.key", message);
    }
}
