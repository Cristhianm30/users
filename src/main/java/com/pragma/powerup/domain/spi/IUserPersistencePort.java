package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;


public interface IUserPersistencePort {
    User save (User user);
}
