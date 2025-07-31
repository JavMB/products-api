package com.javier.productsapi.product.infrastructure.api;

import com.javier.productsapi.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductApi {

    ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String pageSize);

    ResponseEntity<Product> getProductById(@PathVariable Long id);

    ResponseEntity<Void> saveProduct(@RequestBody Product product);

    ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product);

    ResponseEntity<Product> deleteProduct(@PathVariable Long id);


}
