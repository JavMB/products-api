package com.javier.productsapi.review.domain;

import com.javier.productsapi.product.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
    private Long id;
    private String comment;
    private Integer score;

    private Product product;


}
