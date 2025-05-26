package com.lydev.emailservice.service;

import com.lydev.emailservice.dto.EmailRequest;

public interface EmailService {
    void sendEmail(EmailRequest request);
}
