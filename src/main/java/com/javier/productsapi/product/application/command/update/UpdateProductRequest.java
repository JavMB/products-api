package com.javier.productsapi.product.application.command.update;

import com.javier.productsapi.common.application.mediator.Request;
import com.javier.productsapi.product.infrastructure.api.dto.ReviewDto;
import lombok.Data;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String provider;
    private ReviewDto review;
    private Long categoryId;


}
