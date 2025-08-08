package com.javier.productsapi.product.application.querys.getById;

import com.javier.productsapi.common.application.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {
    private Long id;

}
