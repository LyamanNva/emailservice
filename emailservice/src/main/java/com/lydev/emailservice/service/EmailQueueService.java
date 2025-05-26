package com.lydev.emailservice.service;

import com.lydev.emailservice.dto.EmailQueueDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailQueueService {

    private static final String EMAIL_QUEUE = "emailQueue";

    private final RedisTemplate<String, Object> redisTemplate;

    public EmailQueueService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void queueEmail(EmailQueueDto dto) {
        redisTemplate.opsForList().rightPush(EMAIL_QUEUE, dto);
    }

    public EmailQueueDto getNextEmail() {
        return (EmailQueueDto) redisTemplate.opsForList().leftPop(EMAIL_QUEUE);
    }
}
