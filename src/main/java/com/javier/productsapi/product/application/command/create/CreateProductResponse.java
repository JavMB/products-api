package com.javier.productsapi.product.application.command.create;


import com.javier.productsapi.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductResponse {

    private Product product;

}
