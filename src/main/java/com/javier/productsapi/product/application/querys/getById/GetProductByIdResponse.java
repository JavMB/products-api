package com.javier.productsapi.product.application.querys.getById;


import com.javier.productsapi.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetProductByIdResponse {

    private Product product;

}
