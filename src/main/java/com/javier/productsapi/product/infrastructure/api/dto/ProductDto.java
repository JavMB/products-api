package com.javier.productsapi.product.infrastructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data // o records
public class ProductDto {


    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image; // tiene la ruta interna de donde esta ubicada la imagen


}
