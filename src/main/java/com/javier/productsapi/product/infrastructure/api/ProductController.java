package com.javier.productsapi.product.infrastructure.api;

import com.javier.productsapi.common.mediator.Mediator;
import com.javier.productsapi.product.application.command.create.CreateProductRequest;
import com.javier.productsapi.product.application.querys.getById.GetProductByIdRequest;
import com.javier.productsapi.product.application.querys.getById.GetProductByIdResponse;
import com.javier.productsapi.product.domain.Product;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import com.javier.productsapi.product.infrastructure.api.mapper.ProductMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private Mediator mediator;
    private final ProductMapper productMapper;

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        return ResponseEntity.ok(productDto);

    }

    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@RequestBody ProductDto productDto) {
        CreateProductRequest request = productMapper.maptoCreateProductRequest(productDto);

        mediator.dispatch(request);

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();

    }

    @PutMapping("/{id}")  // se podria enviar solo el body con él, id, pero asi creo que es mejor
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
//        Optional<Product> productOptional = products.stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst();
//
//        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        boolean removed = products.removeIf(p -> p.getId().equals(id));
//
//        if (removed) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
        return ResponseEntity.noContent().build();

    }
}
