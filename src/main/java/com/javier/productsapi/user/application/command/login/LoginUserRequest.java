package com.javier.productsapi.user.application.command.login;

import com.javier.productsapi.common.application.mediator.Request;
import lombok.Data;

@Data
public class LoginUserRequest implements Request<LoginUserResponse> {

    private String email;
    private String password;
}
