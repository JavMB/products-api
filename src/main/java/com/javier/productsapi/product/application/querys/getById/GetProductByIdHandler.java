package com.javier.productsapi.product.application.querys.getById;

import com.javier.productsapi.common.application.mediator.RequestHandler;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.exception.ProductNotFoundException;
import com.javier.productsapi.product.domain.port.ProductRepository;
import com.javier.productsapi.review.infrastructure.mapper.ReviewEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {
    private final ReviewEntityMapper reviewEntityMapper;

    private final ProductRepository productRepository;


    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {

        log.info("Getting product by id {}", request.getId());

        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException(request.getId()));

       // product.getReviews().stream().map(Review::getScore).mapToDouble(x-> x).average();

        //product.getReviews().forEach(review->{})

        log.info("Found product with id {}", product.getId());

        return new GetProductByIdResponse(product);
    }


    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
