package com.javier.productsapi.product.infrastructure.database.entity;

import com.javier.productsapi.productDetail.ProductDetailEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Long id;
    private String name;
    @Column(length = 500)
    private String description;
    private Double price;
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_detail_entity_id", unique = true)
    private ProductDetailEntity productDetailEntity;

}
