package com.example.Blog_simple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {
     @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Blog Simple API")
                .version("1.0")
                .description("Documentation de l'API REST du blog développé avec Spring Boot avec la plateforme Cleeroute")
                .contact(new Contact()
                    .email("joycengassam24@gmail.com")
                )
            );
    }
}
