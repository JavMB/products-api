package com.javier.productsapi.product.application.command.create;

import com.javier.productsapi.common.mediator.RequestHandler;
import com.javier.productsapi.product.domain.entity.Product;
import com.javier.productsapi.product.domain.port.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;


@Service
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private ProductRepository productRepository;

    public CreateProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(CreateProductRequest request) {

        MultipartFile file = request.getFile();

        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        String uniqueFileName = UUID.randomUUID().toString().concat("-").concat(filename);

        Path path = Path.of("uploads/products/");


        try (InputStream inputStream = file.getInputStream()) {
            if (Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file");
        }


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
