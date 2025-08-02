package com.javier.productsapi.product.infrastructure.database;

import com.javier.productsapi.product.domain.Product;
import com.javier.productsapi.product.domain.ProductRepository;
import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final List<ProductEntity> products;

    public ProductRepositoryImpl() {
        this.products = new ArrayList<>();
    }

    @Override
    public void upsert(Product product) { // post y update a la vez
        deleteById(product.getId()); // elimina si existe
        products.add(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
