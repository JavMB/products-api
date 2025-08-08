package com.javier.productsapi.product.infrastructure.database.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import com.javier.productsapi.product.infrastructure.database.repository.QueryProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner {

    private final QueryProductRepository productRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        long count = productRepository.count();

        if (count == 0) {

            entityManager.createNativeQuery("ALTER SEQUENCE products_id_seq RESTART WITH 1").executeUpdate();


            Resource resource = resourceLoader.getResource("classpath:products.json");

            List<ProductEntity> products = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });
            productRepository.saveAll(products);

        }
    }
}
