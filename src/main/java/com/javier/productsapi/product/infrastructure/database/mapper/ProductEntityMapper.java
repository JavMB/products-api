package com.javier.productsapi.product.infrastructure.database.mapper;

import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {

    ProductEntity mapToProductEntity(Product product);

    Product mapToProduct(ProductEntity productEntity);

}
