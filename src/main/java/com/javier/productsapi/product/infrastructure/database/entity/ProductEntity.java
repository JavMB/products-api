package com.javier.productsapi.product.infrastructure.database.entity;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ProductEntity {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
}
