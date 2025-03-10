package com.pragma.powerup.infrastructure.out.jpa.repository;


import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);

}
