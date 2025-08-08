package com.javier.productsapi.product.application.command.delete;

import com.javier.productsapi.common.application.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeleteProductRequest implements Request<Void> {
    private Long id;

}
