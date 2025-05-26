package com.lydev.emailservice.mapper;

import com.lydev.emailservice.dto.EmailRequest;
import com.lydev.emailservice.model.EmailLog;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T11:32:38+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class EmailMapperImpl implements EmailMapper {

    @Override
    public EmailLog toEmailLog(EmailRequest request) {
        if ( request == null ) {
            return null;
        }

        EmailLog emailLog = new EmailLog();

        emailLog.setCc( String.join(",", request.getCc() != null ? request.getCc() : java.util.Collections.emptyList()) );
        emailLog.setBcc( String.join(",", request.getBcc() != null ? request.getBcc() : java.util.Collections.emptyList()) );
        emailLog.setSentAt( LocalDateTime.now() );

        return emailLog;
    }
}
