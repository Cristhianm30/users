package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.validations.UserValidations;

public class UserUseCase  implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final UserValidations userValidations;

    public UserUseCase(IUserPersistencePort userPersistencePort, UserValidations userValidations) {
        this.userPersistencePort = userPersistencePort;
        this.userValidations = userValidations;
    }


    @Override
    public User createUser(User user) {
        return userPersistencePort.save(user);
    }

}
