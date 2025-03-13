package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IPasswordEncoder;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.validations.UserValidations;

public class UserUseCase  implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final UserValidations userValidations;
    private final IRoleServicePort roleServicePort;
    private final IPasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, UserValidations userValidations, IRoleServicePort roleServicePort, IPasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.userValidations = userValidations;
        this.roleServicePort = roleServicePort;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createOwner(User user) {
        userValidations.validateOwner(user);
        user.setRole(roleServicePort.getOwnerRole());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userPersistencePort.save(user);
    }

    @Override
    public User getUserById (Long id){
        return userPersistencePort.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }

    @Override
    public User createEmployee(User user) {
        userValidations.validateEmployee(user);
        user.setRole(roleServicePort.getEmployeeRole());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userPersistencePort.save(user);
    }

}
