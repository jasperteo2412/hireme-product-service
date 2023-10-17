package com.hireme.product.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi assignmentsApi() {
        return GroupedOpenApi.builder()
                .group("assignments-api")
                .pathsToMatch("/assignments/**")
                .build();
    }

    @Bean
    public GroupedOpenApi paymentApi() {
        return GroupedOpenApi.builder()
                .group("payment-api")
                .pathsToMatch("/api/payments/**")
                .build();
    }

    // configure additional groups as needed
}
