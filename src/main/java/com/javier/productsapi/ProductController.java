package com.javier.productsapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/products")
public class ProductController {

    @GetMapping("")
    public String getAllProducts(@RequestParam String pageSize) {
        return "Product controller".concat(" ").concat(pageSize);
    }

    public String getProductById(@PathVariable Long id) {
        return "Product controller".concat(id.toString());
    }




}
