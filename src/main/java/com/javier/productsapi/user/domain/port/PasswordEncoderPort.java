package com.javier.productsapi.user.domain.port;

public interface PasswordEncoderPort {

    String encode(String password);
}
