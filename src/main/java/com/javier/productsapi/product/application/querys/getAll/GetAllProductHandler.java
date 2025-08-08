package com.javier.productsapi.product.application.querys.getAll;

import com.javier.productsapi.common.application.mediator.RequestHandler;
import com.javier.productsapi.common.domain.PaginationResult;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllProductHandler implements RequestHandler<GetAllProductRequest, GetAllProductResponse> {

    private final ProductRepository productRepository;


    @Override
    public GetAllProductResponse handle(GetAllProductRequest request) {

        log.info("Getting all products");

        PaginationResult<Product> products = productRepository.findAll(request.getPaginationQuery());

        return new GetAllProductResponse(products);

    }


    @Override
    public Class<GetAllProductRequest> getRequestType() {
        return GetAllProductRequest.class;
    }
}
