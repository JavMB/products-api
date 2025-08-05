package com.javier.productsapi.IT;

import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductIT {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.upsert(
                Product.builder().id(1L).name("Product 1").description("Product 1 description").price(100.0).build()
        );
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteById(1L);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getProductByIdExists() {

        ResponseEntity<ProductDto> response = restTemplate.getForEntity("http://localhost:8080/api/v1/products/1", ProductDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Product 1", response.getBody().getName());
        assertEquals("Product 1 description", response.getBody().getDescription());
        assertEquals(100.0, response.getBody().getPrice());
    }


}
