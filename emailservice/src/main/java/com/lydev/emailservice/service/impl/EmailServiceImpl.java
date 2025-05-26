package com.lydev.emailservice.service.impl;

import com.lydev.emailservice.dto.EmailRequest;
import com.lydev.emailservice.exception.CustomException;
import com.lydev.emailservice.mapper.EmailMapper;
import com.lydev.emailservice.model.EmailLog;
import com.lydev.emailservice.repository.EmailLogRepository;
import com.lydev.emailservice.service.EmailService;
import com.lydev.emailservice.service.EmailTemplateService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final EmailTemplateService templateService;

    private final EmailLogRepository emailLogRepository;
    private final EmailMapper emailMapper;

    @Override
    public void sendEmail(EmailRequest request) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(request.getTo());
            if (request.getCc() != null && !request.getCc().isEmpty())
                helper.setCc(request.getCc().toArray(new String[0]));
            if (request.getBcc() != null && !request.getBcc().isEmpty())
                helper.setBcc(request.getBcc().toArray(new String[0]));

            helper.setSubject(request.getSubject());
            helper.setText(request.getBody(), true);
            helper.setFrom("your_email@gmail.com");

            mailSender.send(message);

            EmailLog emailLog = emailMapper.toEmailLog(request);
            emailLogRepository.save(emailLog);

        } catch (MessagingException e) {
            throw new CustomException("Email göndərilmədi: " + e.getMessage());
        }
    }

    public void sendHtmlEmail(String to, String name) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", name);

        String htmlContent = templateService.loadTemplate("email.html", model);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setSubject("Xoş Gəldin, " + name + "!");
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Email göndərilə bilmədi", e);
        }
    }
}
