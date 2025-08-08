package com.javier.productsapi.product.infrastructure.database.repository;

import com.javier.productsapi.product.infrastructure.database.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QueryProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    Optional<ProductEntity> findByNameContaining(String name);

    List<ProductEntity> findAllByPriceBetween(Double min, Double max);

    @Query("""
            SELECT p
            FROM ProductEntity p
            WHERE p.name LIKE concat('%', :name, '%')
            OR p.description LIKE concat('%', :description, '%')
            OR p.price BETWEEN :minPrice AND :priceBefore
            
            """)
    List<ProductEntity> findProductsDetails(String name, String description, Double minPrice, Double maxPrice);

    boolean existsByName(String name);


    long countByPrice(Double price);


    Page<ProductEntity> findAll(Specification<ProductEntity> specification, Pageable pageable);

    @EntityGraph(attributePaths = {"productDetailEntity"})
    Optional<ProductEntity> findById(Long id);


}
