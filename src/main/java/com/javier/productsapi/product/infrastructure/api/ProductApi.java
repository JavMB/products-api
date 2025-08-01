package com.javier.productsapi.product.infrastructure.api;

import com.javier.productsapi.product.domain.Product;
import com.javier.productsapi.product.infrastructure.api.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductApi {

    ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize);

    ResponseEntity<ProductDto> getProductById(@PathVariable Long id);

    ResponseEntity<Void> saveProduct(@RequestBody ProductDto productDto);

    ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto);

    ResponseEntity<Void> deleteProduct(@PathVariable Long id);


}
