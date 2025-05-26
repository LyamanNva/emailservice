package com.lydev.emailservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Email Service API")
                        .version("v1.0.0")
                        .description("RESTful API documentation for the asynchronous email delivery system using RabbitMQ and Redis.")
                        .contact(new Contact()
                                .name("Laman Nuriyeva")
                                .email("lemannuriyeva006@gmail.com")
                                .url("https://github.com/LyamanNva"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
