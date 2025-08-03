package com.javier.productsapi.product.application.command.update;

import com.javier.productsapi.common.mediator.Request;
import lombok.Data;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;


}
