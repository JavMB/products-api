package com.javier.productsapi.product.infrastructure.api;

import com.javier.productsapi.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController implements ProductApi {




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
