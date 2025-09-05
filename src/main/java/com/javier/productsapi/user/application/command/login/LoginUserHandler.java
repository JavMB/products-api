package com.javier.productsapi.user.application.command.login;

import com.javier.productsapi.common.application.mediator.RequestHandler;
import com.javier.productsapi.user.domain.User;
import com.javier.productsapi.user.domain.port.AuthenticationPort;
import com.javier.productsapi.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {

    private final UserRepository userRepository;
    private final AuthenticationPort authenticationPort;



    @Override
    public LoginUserResponse handle(LoginUserRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        String token = authenticationPort.authenticate(user);

        return new LoginUserResponse(token);


    }

    @Override
    public Class<LoginUserRequest> getRequestType() {
        return LoginUserRequest.class;
    }
}
