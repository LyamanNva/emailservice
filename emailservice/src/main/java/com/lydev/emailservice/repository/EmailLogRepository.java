package com.lydev.emailservice.repository;

import com.lydev.emailservice.model.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailLogRepository extends JpaRepository<EmailLog, String> {
}
