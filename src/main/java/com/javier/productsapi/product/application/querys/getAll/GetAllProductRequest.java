package com.javier.productsapi.product.application.querys.getAll;

import com.javier.productsapi.common.application.mediator.Request;
import com.javier.productsapi.common.domain.PaginationQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllProductRequest implements Request<GetAllProductResponse> {

    private PaginationQuery paginationQuery;

}
