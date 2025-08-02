package com.javier.productsapi.product.infrastructure.api.dto;

import lombok.Data;


@Data // o records
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;




}
