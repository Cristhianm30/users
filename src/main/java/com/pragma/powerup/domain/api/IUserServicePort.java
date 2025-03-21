package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.User;

public interface IUserServicePort {

    User createOwner(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    User createEmployee(User user);
    User createClient(User user);
}
