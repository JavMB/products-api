//package com.javier.productsapi.IT;
//
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.TestPropertySource;
//
//@TestConfiguration
//public class ApiHelper {
//
//    @Bean
//    public TestRestTemplate getRestTemplate() {
//        return new TestRestTemplate(new RestTemplateBuilder()
//                .basicAuthentication("spring", "spring")
//                .connectTimeout(java.time.Duration.ofSeconds(10))
//
//        );
//
//    }
//}
