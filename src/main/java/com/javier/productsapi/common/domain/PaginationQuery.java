package com.javier.productsapi.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationQuery {

    private int page;
    private int size;

}
