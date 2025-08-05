package com.javier.productsapi.product.infrastructure.api;

import com.javier.productsapi.common.mediator.Mediator;
import com.javier.productsapi.product.application.command.create.CreateProductRequest;
import com.javier.productsapi.product.application.command.delete.DeleteProductRequest;
import com.javier.productsapi.product.application.command.update.UpdateProductRequest;
import com.javier.productsapi.product.application.querys.getAll.GetAllProductRequest;
import com.javier.productsapi.product.application.querys.getAll.GetAllProductResponse;
import com.javier.productsapi.product.application.querys.getById.GetProductByIdRequest;
import com.javier.productsapi.product.application.querys.getById.GetProductByIdResponse;
import com.javier.productsapi.product.infrastructure.api.dto.CreateProductDto;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import com.javier.productsapi.product.infrastructure.api.dto.UpdateProductDto;
import com.javier.productsapi.product.infrastructure.api.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product", description = "Product API operations")
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductApi {

    private final Mediator mediator;
    private final ProductMapper productMapper;

    @Operation(summary = "Get all products",description = "Get all products")
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize) {

        log.info("Getting all products");

        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());

        List<ProductDto> productDtos = response.getProduct().stream().map(productMapper::mapToProductDto).toList();

        log.info("Found {} products", productDtos.size());

        return ResponseEntity.ok(productDtos);
    }

    @Operation(summary = "Get product by id",description = "Get product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        log.info("Getting product by id: {}", id);

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        log.info("Found product by id: {}", productDto.getId());

        return ResponseEntity.ok(productDto);

    }
    @Operation(summary = "Save product",description = "Save product")
    @PostMapping("") //@Valid por las anotaciones de jakarta de los dto
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto productDto) {
        log.info("Saving product with id: {}", productDto.getId());

        CreateProductRequest request = productMapper.maptoCreateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Saved product with id: {}", productDto.getId());

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();

    }
    @Operation(summary = "Update product",description = "Update product")
    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@ModelAttribute @Valid UpdateProductDto productDto) {

        log.info("Updating product with id: {}", productDto.getId());

        UpdateProductRequest updateProductRequest = productMapper.maptoUpdateProductRequest(productDto);

        mediator.dispatch(updateProductRequest);

        log.info("Updated product with id: {}", productDto.getId());

        return ResponseEntity.noContent().build();

    }
    @Operation(summary = "Delete product",description = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        log.info("Deleting product with id: {}", id);

        mediator.dispatchAsync(new DeleteProductRequest(id)); // otro hilo del ThreadPool cojeria el caso de uso (ejemplo)

        log.info("Deleted product with id: {}", id);

        return ResponseEntity.accepted().build();
    }
}
