package com.javier.productsapi.product.application.querys.getAll;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.domain.Product;
import com.javier.productsapi.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductHandler implements RequestHandler<GetAllProductRequest, GetAllProductResponse> {

    private final ProductRepository productRepository;


    @Override
    public GetAllProductResponse handle(GetAllProductRequest request) {

        List<Product> products = productRepository.findAll();
        return new GetAllProductResponse(products);
    }


    @Override
    public Class<GetAllProductRequest> getRequestType() {
        return GetAllProductRequest.class;
    }
}
