package com.javier.productsapi.product.application.command.update;

import com.javier.productsapi.common.application.mediator.RequestHandler;
import com.javier.productsapi.common.infrastructure.util.FileUtils;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    public UpdateProductHandler(ProductRepository productRepository, FileUtils fileUtils) {
        this.productRepository = productRepository;
        this.fileUtils = fileUtils;
    }

    @Override
    public Void handle(UpdateProductRequest request) {

        log.info("Received request to update product with id {}", request.getId());

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .id(request.getId()) // mientras no tenemos bd
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .build();
        productRepository.upsert(product);

        log.info("Updated product with id {}", request.getId());

        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }

}
