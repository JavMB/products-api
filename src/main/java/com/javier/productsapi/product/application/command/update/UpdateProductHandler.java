package com.javier.productsapi.product.application.command.update;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.application.command.create.CreateProductRequest;
import com.javier.productsapi.product.domain.Product;
import com.javier.productsapi.product.domain.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private ProductRepository productRepository;

    public UpdateProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(UpdateProductRequest request) {
        Product product = Product.builder()
                .id(request.getId()) // mientras no tenemos bd
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .build();
        productRepository.upsert(product);

        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }

}
