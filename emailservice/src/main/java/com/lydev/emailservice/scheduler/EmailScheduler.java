package com.lydev.emailservice.scheduler;

import com.lydev.emailservice.dto.EmailQueueDto;
import com.lydev.emailservice.service.EmailQueueService;
import com.lydev.emailservice.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailScheduler {

    private final EmailQueueService emailQueueService;
    private final EmailServiceImpl emailService;


    @Scheduled(fixedDelay = 3000)
    public void processQueuedEmails() {
        EmailQueueDto email = emailQueueService.getNextEmail();
        if (email != null) {
            emailService.sendHtmlEmail(email.getTo(), email.getName());
        }
    }
}
