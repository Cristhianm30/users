package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

import java.util.Optional;


public interface IUserPersistencePort {
    User save (User user);
    User findById (Long id);
    User getUserByEmail(String email);

}
