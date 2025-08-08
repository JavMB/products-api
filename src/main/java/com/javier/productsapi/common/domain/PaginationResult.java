package com.javier.productsapi.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResult<T> {

    private List<T> content;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;


}
