package com.javier.productsapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    List<Product> products;

    public ProductController() {
        this.products = List.of(
                Product.builder().id(2L).name("Product 1").description("Description 1").price(100.00).image("image1").build(),
                Product.builder().id(2L).name("Product 2").description("Description 2").price(100.00).image("image2").build()
        );

    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam String pageSize) {
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return products.stream().filter()
    }


}
