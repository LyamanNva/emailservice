package com.lydev.emailservice.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

@Service
public class EmailTemplateService {

    public String loadTemplate(String templateName, Map<String, Object> model) {
        try {
            ClassPathResource resource = new ClassPathResource("templates/" + templateName);
            InputStream inputStream = resource.getInputStream();
            String template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            for (Map.Entry<String, Object> entry : model.entrySet()) {
                template = template.replace("{{" + entry.getKey() + "}}", entry.getValue().toString());
            }

            return template;
        } catch (IOException e) {
            throw new RuntimeException("Server xətası: Failed to load email template", e);
        }
    }
}
