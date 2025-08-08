package com.javier.productsapi.product.infrastructure.database.specification;

import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
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

    public static Specification<ProductEntity> byPrice(Double minPrice, Double maxPrice) {

        return (root, query, cb)
                -> minPrice == null && maxPrice == null ? null
                : cb.between(root.get("price"), minPrice == null ? 0 : minPrice, maxPrice == null ? Double.MAX_VALUE : maxPrice);


    }

}
