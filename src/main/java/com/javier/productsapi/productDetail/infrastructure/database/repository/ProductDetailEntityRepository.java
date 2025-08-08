package com.javier.productsapi.productDetail.infrastructure.database.repository;

import com.javier.productsapi.productDetail.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailEntityRepository extends JpaRepository<ProductDetailEntity, Long> {
}