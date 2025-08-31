package com.javier.productsapi.user.domain.port;

import com.javier.productsapi.user.domain.User;

public interface AuthenticationPort {

    String authenticate(User user);

}
