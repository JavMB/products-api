package com.javier.productsapi.product.application.command.create;

import com.javier.productsapi.common.application.mediator.RequestHandler;
import com.javier.productsapi.common.infrastructure.util.FileUtils;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CreateProductHandler implements RequestHandler<CreateProductRequest, CreateProductResponse> {

    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    public CreateProductHandler(ProductRepository productRepository, FileUtils fileUtils) {
        this.productRepository = productRepository;
        this.fileUtils = fileUtils;
    }

    @Override
    public CreateProductResponse handle(CreateProductRequest request) {

        log.info("Creating product with name: {}", request.getName());

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder().name(request.getName()).description(request.getDescription()).price(request.getPrice()).image(uniqueFileName).build();

        Product storedProduct = productRepository.upsert(product);

        log.info("Product created with id: {}", storedProduct.getId());

        return new CreateProductResponse(storedProduct);
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
