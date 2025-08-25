package com.javier.productsapi.user.infrastructure.api;

import com.javier.productsapi.common.application.mediator.Mediator;
import com.javier.productsapi.user.infrastructure.api.dto.LoginRequestDto;
import com.javier.productsapi.user.infrastructure.api.dto.RegisterRequestDto;
import com.javier.productsapi.user.infrastructure.api.dto.TokenResponseDto;
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

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {


    return ResponseEntity.ok(null);

    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {





        return ResponseEntity.ok(null);


    }


}
