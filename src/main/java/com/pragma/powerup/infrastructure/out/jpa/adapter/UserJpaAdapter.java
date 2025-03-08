package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;


public class UserJpaAdapter  implements IUserPersistencePort {

    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;

    public UserJpaAdapter(IUserEntityMapper userEntityMapper, IUserRepository userRepository) {
        this.userEntityMapper = userEntityMapper;
        this.userRepository = userRepository;
    }


    @Override
    public User save(User user) {
        UserEntity entity = userEntityMapper.userToEntity(user);
        UserEntity savedEntity = userRepository.save(entity);
        User response = userEntityMapper.entityToUser(savedEntity);
        return response;
    }
}
