package com.javier.productsapi.user.infrastructure.authentication;

import com.javier.productsapi.user.domain.port.AuthenticationPort;
import com.javier.productsapi.user.infrastructure.database.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;


    @Override
    public String authenticate(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );
        UserEntity user = (UserEntity) authentication.getPrincipal();




    }
}
