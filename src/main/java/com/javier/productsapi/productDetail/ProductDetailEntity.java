package com.javier.productsapi.productDetail;

import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "product_details")
public class ProductDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String specifications;
    private String warranty;
    private String provider;


    @OneToOne(mappedBy = "productDetailEntity")
    private ProductEntity productEntity;

}
