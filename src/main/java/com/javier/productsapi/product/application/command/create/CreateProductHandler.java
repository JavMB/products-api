package com.javier.productsapi.product.application.command.create;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private ProductRepository productRepository;

    public CreateProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(CreateProductRequest request) {
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
