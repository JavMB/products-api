package com.javier.productsapi.product.application;

import com.javier.productsapi.common.mediator.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductCreateRequest implements Request<Void> {

    private final Long id;
    private String name;
    private String description;
    private Double price;
    private String image;


}
