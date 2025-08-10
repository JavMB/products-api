package com.javier.productsapi.category.domain;

import com.javier.productsapi.product.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Category {

    private Long id;
    private String name;

    List<Product> products;



}
