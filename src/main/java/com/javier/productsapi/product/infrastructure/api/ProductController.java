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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final Mediator mediator;
    private final ProductMapper productMapper;

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize) {
        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());
        List<ProductDto> productDtos = response.getProduct().stream().map(productMapper::mapToProductDto).toList();

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        return ResponseEntity.ok(productDto);

    }

    @PostMapping("") //@Valid por las anotaciones de jakarta de los dto
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto productDto) {
        CreateProductRequest request = productMapper.maptoCreateProductRequest(productDto);

        mediator.dispatch(request);

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();

    }

    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@ModelAttribute @Valid UpdateProductDto productDto) {

        UpdateProductRequest updateProductRequest = productMapper.maptoUpdateProductRequest(productDto);

        mediator.dispatch(updateProductRequest);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        mediator.dispatch(new DeleteProductRequest(id));

        return ResponseEntity.noContent().build();

    }
}
