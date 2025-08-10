package com.javier.productsapi.product.infrastructure.database;

import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import com.javier.productsapi.product.infrastructure.database.mapper.ProductEntityMapper;
import com.javier.productsapi.product.infrastructure.database.repository.QueryProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {
    @Mock
    private QueryProductRepository repository;
    @Mock
    private ProductEntityMapper productEntityMapper;
    @Mock
    private ProductRepositoryImpl productRepository;

    @Test
    void shouldNotReturnProductWhenFound() {
        Long productId = 1L;
        Optional<Product> product = productRepository.findById(productId);
        assertTrue(product.isEmpty(), "Expected no product to be found for ID: " + productId);

    }

    @Test
    void shouldReturnWhenProductFound() {
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        productEntity.setName("Test Product");
        productEntity.setDescription("This is a test product");
        productEntity.setPrice(100.0);

        Product product = Product.builder()
                .id(productId)
                .name("Test Product")
                .description("This is a test product")
                .price(100.0)
                .build();


        // Simulate the repository returning a product
        when(repository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(productEntityMapper.mapToProduct(any())).thenReturn(product);

        Optional<Product> result = productRepository.findById(productId);
        assertTrue(result.isPresent(), "Expected product to be found for ID: " + productId);

    }


}