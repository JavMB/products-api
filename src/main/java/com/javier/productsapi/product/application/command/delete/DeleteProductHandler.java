package com.javier.productsapi.product.application.command.delete;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(DeleteProductRequest request) {

        productRepository.deleteById(request.getId());

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}


