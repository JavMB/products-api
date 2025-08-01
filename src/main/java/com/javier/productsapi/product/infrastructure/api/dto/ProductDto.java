package com.javier.productsapi.product.infrastructure.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // o records
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;




}
