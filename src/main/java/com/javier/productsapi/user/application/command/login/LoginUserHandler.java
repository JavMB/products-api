package com.javier.productsapi.user.application.command.login;

import com.javier.productsapi.common.application.mediator.RequestHandler;
import com.javier.productsapi.user.domain.port.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {

    private final AuthenticationPort authenticationPort;



    @Override
    public LoginUserResponse handle(LoginUserRequest request) {

        String token = authenticationPort.authenticate(request.getEmail(),  request.getPassword());

        return new LoginUserResponse(token);
    }

    @Override
    public Class<LoginUserRequest> getRequestType() {
        return LoginUserRequest.class;
    }
}
