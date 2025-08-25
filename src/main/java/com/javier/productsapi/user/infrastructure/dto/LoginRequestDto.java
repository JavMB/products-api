package com.javier.productsapi.user.infrastructure.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;


}
