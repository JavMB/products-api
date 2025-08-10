package com.javier.productsapi.productDetail.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javier.productsapi.productDetail.ProductDetailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
@Profile("*!test")
public class ProductDetailSeeder implements CommandLineRunner {


    private final QueryProductDetailRepository productDetailRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws Exception {
        long count = productDetailRepository.count();

        if (count == 0) {

            Resource resource = resourceLoader.getResource("classpath:products_details.json");

            List<ProductDetailEntity> productsDetailEntities = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });

            productDetailRepository.saveAll(productsDetailEntities);
        }

    }
}



