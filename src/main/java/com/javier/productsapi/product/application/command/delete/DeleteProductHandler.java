package com.javier.productsapi.product.application.command.delete;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.domain.ProductRepository;

public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private ProductRepository productRepository;

    public DeleteProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(DeleteProductRequest request) {

        productRepository.deleteById(request.getId());

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}

