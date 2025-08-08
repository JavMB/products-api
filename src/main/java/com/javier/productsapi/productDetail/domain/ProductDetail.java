package com.javier.productsapi.productDetail.domain;

import com.javier.productsapi.product.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetail {

    private Long id;
    private String specification;
    private String description;
    private String warranty;
    private String provider;

    private Product product;

}
