package com.javier.productsapi.product.application.querys.getById;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.exception.ProductNotFoundException;
import com.javier.productsapi.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {

    private final ProductRepository productRepository;


    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {

        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException(request.getId()));

        return new GetProductByIdResponse(product);
    }


    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
