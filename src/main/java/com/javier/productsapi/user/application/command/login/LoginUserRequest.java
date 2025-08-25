package com.javier.productsapi.user.application.command.login;

import lombok.Data;

@Data
public class LoginUserRequest {

    private String email;
    private String password;
}
