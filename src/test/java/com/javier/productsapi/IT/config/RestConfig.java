package com.javier.productsapi.IT.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class RestConfig {
    //config para la security(jwt)
    @Value("${jwt.token}")
    private String token;

    @Bean
    public TestRestTemplate restTemplate() {
        return new TestRestTemplate(new RestTemplateBuilder()
                .defaultHeader("Authorization", "Bearer ".concat(token))
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .rootUri("http://localhost:8080")

        );

    }
}
