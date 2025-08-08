package com.javier.productsapi.product.infrastructure.api.mapper;

import com.javier.productsapi.product.application.command.create.CreateProductRequest;
import com.javier.productsapi.product.application.command.update.UpdateProductRequest;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.infrastructure.api.dto.CreateProductDto;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import com.javier.productsapi.product.infrastructure.api.dto.UpdateProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper para convertir entre ProductDto y Product usando MapStruct , yo le digo como lo quiero en la interfaz.
 * <p>
 * componentModel = SPRING: lo registra como bean de Spring para inyección automática.
 * unmappedSourcePolicy = ERROR: obliga a mapear todos los campos del origen, lanzando error si falta alguno.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface ProductMapper {
    //@Mapping(target = "id",source ="product_id") con sus anotaciones podriamos modificar , o incluso meter codigo sencillo.

    CreateProductRequest maptoCreateProductRequest(CreateProductDto createProductDto);

    UpdateProductRequest maptoUpdateProductRequest(UpdateProductDto updateProductDto);

    @Mapping(target = "provider",source = "productDetail.provider")
    ProductDto mapToProductDto(Product product);


}
