package com.javier.productsapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    List<Product> products;

    public ProductController() {
        this.products = new ArrayList<>(List.of(
                Product.builder().id(1L).name("Product 1").description("Description 1").price(100.00).image("image1").build(),
                Product.builder().id(2L).name("Product 2").description("Description 2").price(100.00).image("image2").build()));


    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String pageSize) {
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = products.stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ResponseEntity.ok(product);
    }

    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {

        products.add(product);
        return ResponseEntity.created(URI.create("/api/v1/products/".concat(product.getId().toString()))).build();

    }

    @PutMapping("/{id}")  // se podria enviar solo el body con el id, pero asi creo que es mejor
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        boolean removed = products.removeIf(p -> p.getId().equals(id));

        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
