package com.javier.productsapi.user.domain.port;

import com.javier.productsapi.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User upsert(User user);

}
