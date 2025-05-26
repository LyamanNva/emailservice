package com.lydev.emailservice.mapper;

import com.lydev.emailservice.dto.EmailRequest;
import com.lydev.emailservice.model.EmailLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface EmailMapper {

    @Mapping(target = "cc", expression = "java(String.join(\",\", request.getCc() != null ? request.getCc() : java.util.Collections.emptyList()))")
    @Mapping(target = "bcc", expression = "java(String.join(\",\", request.getBcc() != null ? request.getBcc() : java.util.Collections.emptyList()))")
    @Mapping(target = "sentAt", expression = "java(LocalDateTime.now())")
    EmailLog toEmailLog(EmailRequest request);
}
