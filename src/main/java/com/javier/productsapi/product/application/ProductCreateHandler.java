package com.javier.productsapi.product.application;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.domain.Product;
import com.javier.productsapi.product.domain.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductCreateHandler implements RequestHandler<ProductCreateRequest, Void> {

    private ProductRepository productRepository;

    public ProductCreateHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(ProductCreateRequest request) {
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
    public Class<ProductCreateRequest> getRequestType() {
        return ProductCreateRequest.class;
    }

//    @Override   se podria con reflexion , pero sabemos exactamente que devolvemos.
//    @SuppressWarnings("unchecked")
//    public Class<ProductCreateRequest> getRequestType() {
//        return (Class<ProductCreateRequest>) ((ParameterizedType) getClass()
//                .getGenericSuperclass())
//                .getActualTypeArguments()[0];
//    }
}
