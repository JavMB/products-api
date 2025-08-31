package com.javier.productsapi.user.domain.port;

import com.javier.productsapi.user.domain.User;

public interface UserRepository {

    boolean existsByEmail(String email);

    User upsert(User user);

}
