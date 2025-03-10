package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter  implements IUserPersistencePort {

    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;



    @Override
    public User save(User user) {
        UserEntity entity = userEntityMapper.userToEntity(user);
        UserEntity savedEntity = userRepository.save(entity);
        return userEntityMapper.entityToUser(savedEntity);
    }

    @Override
    public Optional<User> findById (Long id){
    Optional<UserEntity> UserEntity = userRepository.findById(id);
    return UserEntity.map(userEntityMapper::entityToUser);
    }
}
