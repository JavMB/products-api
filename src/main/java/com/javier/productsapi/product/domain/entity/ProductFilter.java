package com.javier.productsapi.product.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductFilter {

    private String name;
    private String description;
    private Double priceMin;
    private Double priceMax;


}
