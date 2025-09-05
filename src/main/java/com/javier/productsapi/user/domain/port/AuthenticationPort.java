package com.javier.productsapi.user.domain.port;


public interface AuthenticationPort {

    String authenticate(String username, String password);

}
