package com.lydev.emailservice.controller;

import com.lydev.emailservice.dto.EmailRequest;
import com.lydev.emailservice.service.impl.EmailServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailServiceImpl emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody @Valid EmailRequest request) {
        emailService.sendEmail(request);
        return ResponseEntity.ok("Email uğurla göndərildi!");
    }

    @PostMapping("/sendHtml")
    public ResponseEntity<String> sendHtmlEmail(
            @RequestParam String to,
            @RequestParam String name) {
        emailService.sendHtmlEmail(to, name);
        return ResponseEntity.ok("HTML email göndərildi!");
    }
}
