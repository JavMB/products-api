package com.javier.productsapi.product.infrastructure.database.mapper;

import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import com.javier.productsapi.review.domain.Review;
import com.javier.productsapi.review.infrastructure.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {

    @Mapping(target = "productDetailEntity", source = "productDetail")
    @Mapping(target = "productDetailEntity.productEntity", ignore = true)
    ProductEntity mapToProductEntity(Product product);

    @Mapping(target = "productDetail", source = "productDetailEntity")
    @Mapping(target = "productDetail.product", ignore = true)
    Product mapToProduct(ProductEntity productEntity);



    @Mapping(target = "product", ignore = true)
    Review maptoReview(ReviewEntity reviewEntity); // estos se podrian haber extendido de otro mapper, ahora los escanea de aqui

    @Mapping(target = "productEntity", ignore = true)
    ReviewEntity maptoReviewEntity(Review review);


}
