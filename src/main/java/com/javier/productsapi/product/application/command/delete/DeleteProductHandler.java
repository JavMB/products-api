package com.javier.productsapi.product.application.command.delete;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(DeleteProductRequest request) {



        log.info("Delete product request with id {}", request.getId());

        productRepository.deleteById(request.getId());

        log.info("Deleted product with id {}", request.getId());


        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}


