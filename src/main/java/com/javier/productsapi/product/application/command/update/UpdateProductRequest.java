package com.javier.productsapi.product.application.command.update;

import com.javier.productsapi.common.application.mediator.Request;
import com.javier.productsapi.review.domain.Review;
import lombok.Data;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String provider;
    private Review review;
    private Long categoryId;


}
