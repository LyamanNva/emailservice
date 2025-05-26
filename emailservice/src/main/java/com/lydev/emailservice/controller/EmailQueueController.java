package com.lydev.emailservice.controller;

import com.lydev.emailservice.dto.EmailQueueDto;
import com.lydev.emailservice.service.EmailQueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailQueueController {

    private final EmailQueueService emailQueueService;

    public EmailQueueController(EmailQueueService emailQueueService) {
        this.emailQueueService = emailQueueService;
    }

    @PostMapping("/queue")
    public ResponseEntity<String> queueEmail(@RequestParam String to, @RequestParam String name) {
        EmailQueueDto dto = new EmailQueueDto(to, name);
        emailQueueService.queueEmail(dto);
        return ResponseEntity.ok("Email queue-ya əlavə olundu.");
    }
}
