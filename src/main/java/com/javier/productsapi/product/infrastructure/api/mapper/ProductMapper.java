package com.javier.productsapi.product.infrastructure.api.mapper;

import com.javier.productsapi.category.domain.Category;
import com.javier.productsapi.product.application.command.create.CreateProductRequest;
import com.javier.productsapi.product.application.command.update.UpdateProductRequest;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.infrastructure.api.dto.CreateProductDto;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import com.javier.productsapi.product.infrastructure.api.dto.ReviewDto;
import com.javier.productsapi.product.infrastructure.api.dto.UpdateProductDto;
import com.javier.productsapi.review.domain.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper para convertir entre ProductDto y Product usando MapStruct , yo le digo como lo quiero en la interfaz.
 * <p>
 * componentModel = SPRING: lo registra como bean de Spring para inyección automática.
 * unmappedSourcePolicy = ERROR: obliga a mapear todos los campos del origen, lanzando error si falta alguno.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    CreateProductRequest maptoCreateProductRequest(CreateProductDto createProductDto);

    UpdateProductRequest maptoUpdateProductRequest(UpdateProductDto updateProductDto);

    @Mapping(target = "provider", source = "productDetail.provider")
    ProductDto mapToProductDto(Product product);

    @Mapping(target = "product", ignore = true)
    Review mapToReview(ReviewDto reviewDto);

    default List<String> mapToCategoryNames(List<Category> categories) {
        return categories.stream().map(Category::getName).toList();
    }


}
