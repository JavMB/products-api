package com.javier.productsapi.product.application.querys.getAll;


import com.javier.productsapi.common.domain.PaginationResult;
import com.javier.productsapi.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetAllProductResponse {

    private PaginationResult<Product> productsPage;

}
