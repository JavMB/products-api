package com.javier.productsapi.product.domain.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("The product with the following message was not found: " + id);
    }
}
