package com.javier.productsapi.user.application.command.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserResponse {

    private String token;
}
