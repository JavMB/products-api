package com.javier.productsapi.productDetail.infrastructure;

import com.javier.productsapi.productDetail.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryProductDetailRepository extends JpaRepository<ProductDetailEntity,Long> {

}
