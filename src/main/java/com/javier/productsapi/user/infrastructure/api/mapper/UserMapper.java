package com.javier.productsapi.user.infrastructure.api.mapper;

import com.javier.productsapi.user.application.command.login.LoginUserRequest;
import com.javier.productsapi.user.application.command.login.LoginUserResponse;
import com.javier.productsapi.user.application.command.register.RegisterUserRequest;
import com.javier.productsapi.user.application.command.register.RegisterUserResponse;
import com.javier.productsapi.user.infrastructure.api.dto.LoginRequestDto;
import com.javier.productsapi.user.infrastructure.api.dto.RegisterRequestDto;
import com.javier.productsapi.user.infrastructure.api.dto.TokenResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface UserMapper {

    LoginUserRequest mapToLoginUserRequest(LoginRequestDto loginRequestDto);


    RegisterUserRequest mapToRegisterUserRequest(RegisterRequestDto registerRequestDto);

    TokenResponseDto mapToTokenResponseDto(LoginUserResponse loginUserResponse);

    TokenResponseDto mapToTokenResponseDto(RegisterUserResponse registerUserResponse);


}
