package com.javier.productsapi.product.application.command.create;

import com.javier.productsapi.common.application.mediator.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductRequest implements Request<CreateProductResponse> {


    private String name;
    private String description;
    private Double price;
    private MultipartFile file;


}
