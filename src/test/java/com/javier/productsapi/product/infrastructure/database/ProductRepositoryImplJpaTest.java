package com.javier.productsapi.product.infrastructure.database;

import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import com.javier.productsapi.product.infrastructure.database.repository.QueryProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ProductRepositoryImplJpaTest {

    @Autowired
    private QueryProductRepository repository;


    @Test
    void shouldNotReturnProductWhenFound() {
        Long productId = 1L;
        Optional<ProductEntity> product = repository.findById(productId);
        assertTrue(product.isEmpty(), "Expected no product to be found for ID: " + productId);

    }

    @Test
    void shouldReturnWhenProductFound() {
        ProductEntity productEntity = new ProductEntity();
        ProductEntity save = repository.save(productEntity);

        Optional<ProductEntity> optionalProduct = repository.findById(save.getId());
        assertTrue(optionalProduct.isPresent(), "Expected product to be found for ID: " + save.getId());

    }


}