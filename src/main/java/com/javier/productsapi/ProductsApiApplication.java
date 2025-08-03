package com.javier.productsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(exclude = {})
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*README.*")
})

public class ProductsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApiApplication.class, args);

    }

}
