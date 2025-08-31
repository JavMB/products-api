package com.javier.productsapi.user.application.command.login;

import com.javier.productsapi.common.application.mediator.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {


    @Override
    public LoginUserResponse handle(LoginUserRequest request) {
        return null;
    }

    @Override
    public Class<LoginUserRequest> getRequestType() {
        return LoginUserRequest.class;
    }
}
