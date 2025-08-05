package com.javier.productsapi.IT;

import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
@AutoConfigureMockMvc
public class ProductIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

        log.info("Setting up test");
        productRepository.upsert(
                Product.builder().id(1L).name("Product 1").description("Product 1 description").price(100.0).build()
        );
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteById(1L);
        log.info("Tearing down test");
    }


    @Test
    public void getProductByIdExists() {

        ResponseEntity<ProductDto> response = restTemplate.getForEntity("http://localhost:8080/api/v1/products/1", ProductDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Product 1", response.getBody().getName());
        assertEquals("Product 1 description", response.getBody().getDescription());
        assertEquals(100.0, response.getBody().getPrice());
    }

    @Test
    public void saveProduct() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "image.jpeg", "image/jpeg", "image".getBytes());


        mockMvc.perform(multipart(HttpMethod.POST, "http://localhost:8080/api/v1/products")
                .file(file)
                .param("id", "2")
                .param("name", "Product 2")
                .param("description", "Description 2")
                .param("price", "200.0")
                .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isCreated());


    }


}
