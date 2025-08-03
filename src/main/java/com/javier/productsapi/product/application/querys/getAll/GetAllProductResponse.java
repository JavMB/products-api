package com.javier.productsapi.product.application.querys.getAll;


import com.javier.productsapi.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GetAllProductResponse {

    private List<Product> product;

}
