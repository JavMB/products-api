package com.javier.productsapi.product.application.command.update;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.common.util.FileUtils;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    public UpdateProductHandler(ProductRepository productRepository, FileUtils fileUtils) {
        this.productRepository = productRepository;
        this.fileUtils = fileUtils;
    }

    @Override
    public Void handle(UpdateProductRequest request) {

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .id(request.getId()) // mientras no tenemos bd
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .build();
        productRepository.upsert(product);

        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }

}
