package com.lydev.emailservice.controller;

import com.lydev.emailservice.dto.EmailMessageRabbit;
import com.lydev.emailservice.dto.EmailQueueDto;
import com.lydev.emailservice.producer.EmailProducer;
import com.lydev.emailservice.service.EmailQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailQueueController {

    private final EmailQueueService emailQueueService;
    private final EmailProducer emailProducer;



    @PostMapping("/queue")
    public ResponseEntity<String> queueEmail(@RequestParam String to, @RequestParam String name) {
        EmailQueueDto dto = new EmailQueueDto(to, name);
        emailQueueService.queueEmail(dto);
        return ResponseEntity.ok("Email queue-ya əlavə olundu.(Redis)");
    }

    @PostMapping("/sendRabbit")
    public ResponseEntity<String> sendWithRabbit(@RequestParam String to, @RequestParam String name) {
        EmailMessageRabbit message = new EmailMessageRabbit();
        message.setTo(to);
        message.setName(name);

        emailProducer.sendEmailToQueue(message);
        return ResponseEntity.ok("Email queue-ya əlavə olundu.(Rabbit)");
    }
}
