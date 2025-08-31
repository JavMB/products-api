package com.javier.productsapi.user.infrastructure.api;

import com.javier.productsapi.common.application.mediator.Mediator;
import com.javier.productsapi.user.application.command.login.LoginUserRequest;
import com.javier.productsapi.user.application.command.login.LoginUserResponse;
import com.javier.productsapi.user.application.command.register.RegisterUserRequest;
import com.javier.productsapi.user.application.command.register.RegisterUserResponse;
import com.javier.productsapi.user.infrastructure.api.dto.LoginRequestDto;
import com.javier.productsapi.user.infrastructure.api.dto.RegisterRequestDto;
import com.javier.productsapi.user.infrastructure.api.dto.TokenResponseDto;
import com.javier.productsapi.user.infrastructure.api.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "User API operations")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final Mediator mediator;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginUserRequest request = userMapper.mapToLoginUserRequest(loginRequestDto);
        LoginUserResponse response = mediator.dispatch(request);
        TokenResponseDto tokenResponseDto = userMapper.mapToTokenResponseDto(response);


        return ResponseEntity.ok(tokenResponseDto);

    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {

        RegisterUserRequest request = userMapper.mapToRegisterUserRequest(registerRequestDto);
        RegisterUserResponse response = mediator.dispatch(request);
        TokenResponseDto tokenResponseDto = userMapper.mapToTokenResponseDto(response);


        return ResponseEntity.ok(tokenResponseDto);


    }


}
