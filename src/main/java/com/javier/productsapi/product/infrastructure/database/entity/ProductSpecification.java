package com.javier.productsapi.product.infrastructure.database.entity;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    // los where de las consultas se pueden hacer con Specification
    public static Specification<ProductEntity> byName(String name) {

        return (root, query, cb)
                -> name == null ? null : cb.like(root.get("name"), "%" + name + "%");

    }

    public static Specification<ProductEntity> byDescription(String description) {

        return (root, query, cb)
                -> description == null ? null : cb.like(root.get("description"), "%" + description + "%");

    }

    public static Specification<ProductEntity> byPriceRange(Double price) {

        return (root, query, cb)
                -> price == null ? null : cb.between(root.get("price"), price, price);


    }

}
