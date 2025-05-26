package com.lydev.emailservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class EmailRequest {

    @Email
    @NotBlank
    private String to;

    private List<@Email String> cc;

    private List<@Email String> bcc;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;
}
