package com.javier.productsapi.user.application.command.register;

import com.javier.productsapi.common.application.mediator.RequestHandler;
import com.javier.productsapi.user.domain.User;
import com.javier.productsapi.user.domain.UserRole;
import com.javier.productsapi.user.domain.port.AuthenticationPort;
import com.javier.productsapi.user.domain.port.PasswordEncoderPort;
import com.javier.productsapi.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final AuthenticationPort authenticationPort;

    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {

        boolean existsByEmail = userRepository.existsByEmail(request.getEmail());
        if (existsByEmail) {
            throw new RuntimeException("Email already exists");
        }

        String encoded = passwordEncoderPort.encode(request.getPassword());

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encoded)
                .role(UserRole.USER)
                .build();


        User upsert = userRepository.upsert(user);
        String token = authenticationPort.authenticate(upsert);
        return new RegisterUserResponse(token);

    }

    @Override
    public Class<RegisterUserRequest> getRequestType() {
        return RegisterUserRequest.class;
    }
}
