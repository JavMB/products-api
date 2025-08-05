package com.javier.productsapi.product.application.querys.getById;

import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.exception.ProductNotFoundException;
import com.javier.productsapi.product.domain.port.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProductByIdHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductByIdHandler handler;

    @BeforeEach
    void setUp() {
        // Set up necessary mocks or resources
    }

    @Test
    void shouldReturnProductWhenFound() {
        // Arrange
        Long productId = 1L;
        Product mockProduct = Product.builder().id(productId).name("Test Product").build();
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        GetProductByIdRequest request = new GetProductByIdRequest(productId);

        // Act
        GetProductByIdResponse response = handler.handle(request);

        // Assert
        assertNotNull(response);
        assertEquals(productId, response.getProduct().getId());
        assertEquals("Test Product", response.getProduct().getName());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        GetProductByIdRequest request = new GetProductByIdRequest(productId);

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> handler.handle(request));
    }


}