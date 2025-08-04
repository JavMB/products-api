package com.javier.productsapi.product.application.command.create;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.common.util.FileUtils;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    public CreateProductHandler(ProductRepository productRepository, FileUtils fileUtils) {
        this.productRepository = productRepository;
        this.fileUtils = fileUtils;
    }

    @Override
    public Void handle(CreateProductRequest request) {

        log.info("Create product request with id {}", request.getId());

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
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }

//    @Override   se podria con reflexion , pero sabemos exactamente que devolvemos.
//    @SuppressWarnings("unchecked")
//    public Class<ProductCreateRequest> getRequestType() {
//        return (Class<ProductCreateRequest>) ((ParameterizedType) getClass()
//                .getGenericSuperclass())
//                .getActualTypeArguments()[0];
//    }
}
