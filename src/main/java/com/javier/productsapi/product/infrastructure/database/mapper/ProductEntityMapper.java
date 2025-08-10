package com.javier.productsapi.product.infrastructure.database.mapper;

import com.javier.productsapi.category.infrastructure.CategoryEntityMapper;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import com.javier.productsapi.review.infrastructure.mapper.ReviewEntityMapper;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {ReviewEntityMapper.class, CategoryEntityMapper.class})
public interface ProductEntityMapper {

    @Mapping(target = "productDetailEntity", source = "productDetail")
    @Mapping(target = "productDetailEntity.productEntity", ignore = true)
    ProductEntity mapToProductEntity(Product product);

    @Mapping(target = "productDetail", source = "productDetailEntity")
    @Mapping(target = "productDetail.product", ignore = true)
    Product mapToProduct(ProductEntity productEntity);


    // AfterMapping para linkear cuando vienen de entrada Product->ProductEntity  despues el mappeo para no perder integridad ,
    //  hacerlo con helpers(ADDERS) de la Entity que usara MapStruct o Servicios.

    @AfterMapping
    default void linkReviews(@MappingTarget ProductEntity productEntity) {
        if (productEntity.getReviews() != null) {
            productEntity.getReviews().forEach(r -> r.setProductEntity(productEntity));
        }

    }

}
