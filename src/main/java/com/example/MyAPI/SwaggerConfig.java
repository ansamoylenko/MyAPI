package com.example.MyAPI;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("My API")
                                .version("2.1")
                                .contact(
                                    new Contact()
                                        .email("a.n.samoylenko@outlook.com")
                                        .url("https://github.com/ansamoylenko")
                                        .name("Samoylenko Alexandr")
                )
                );

    }



}