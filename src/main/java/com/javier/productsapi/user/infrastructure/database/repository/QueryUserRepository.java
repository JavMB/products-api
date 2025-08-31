package com.javier.productsapi.user.infrastructure.database.repository;

import com.javier.productsapi.user.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryUserRepository extends JpaRepository<UserEntity, Long> {
}
