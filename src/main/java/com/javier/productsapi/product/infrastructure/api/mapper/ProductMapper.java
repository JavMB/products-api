package com.javier.productsapi.product.infrastructure.api.mapper;

import com.javier.productsapi.product.application.CreateProductRequest;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper para convertir entre ProductDto y Product usando MapStruct.
 * <p>
 * componentModel = SPRING: lo registra como bean de Spring para inyección automática.
 * unmappedSourcePolicy = ERROR: obliga a mapear todos los campos del origen, lanzando error si falta alguno.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface ProductMapper {
    CreateProductRequest maptoCreateProductRequest(ProductDto productDto);

}
