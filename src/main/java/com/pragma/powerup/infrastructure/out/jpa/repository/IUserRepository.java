package com.pragma.powerup.infrastructure.out.jpa.repository;


import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById (Long id);
    Optional<UserEntity> findByEmail(String email);
}
