package com.javier.productsapi.product.application.command.update;

import com.javier.productsapi.category.domain.Category;
import com.javier.productsapi.category.infrastructure.CategoryEntityMapper;
import com.javier.productsapi.category.infrastructure.QueryCategoryRepository;
import com.javier.productsapi.common.application.mediator.RequestHandler;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.exception.ProductNotFoundException;
import com.javier.productsapi.product.domain.port.ProductRepository;
import com.javier.productsapi.productDetail.domain.ProductDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private final ProductRepository productRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final QueryCategoryRepository queryCategoryRepository;

    public UpdateProductHandler(ProductRepository productRepository, CategoryEntityMapper categoryEntityMapper, QueryCategoryRepository queryCategoryRepository) {
        this.productRepository = productRepository;

        this.queryCategoryRepository = queryCategoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;

    }

    @Override
    public Void handle(UpdateProductRequest request) {

        log.info("Received request to update product with id {}", request.getId());

        //String uniqueFileName = fileUtils.saveProductImage(request.getFile());


        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException(request.getId()));

        ProductDetail productDetail = product.getProductDetail();

        productDetail.setProvider(request.getProvider());

        product.getReviews().add(request.getReview());

        Category category = queryCategoryRepository.findById(request.getCategoryId()).map(categoryEntityMapper::mapToCategory).
                orElseThrow(() -> new RuntimeException(request.getCategoryId() + " not found"));

        product.getCategories().add(category);

        productRepository.upsert(product);


        log.info("Updated product with id {}", request.getId());

        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }

}
