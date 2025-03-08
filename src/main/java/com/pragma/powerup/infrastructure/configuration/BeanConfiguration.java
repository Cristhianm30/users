package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IObjectServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.spi.IObjectPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.ObjectUseCase;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.domain.usecase.validations.UserValidations;
import com.pragma.powerup.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IObjectRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;

    @Bean
    public IObjectPersistencePort objectPersistencePort() {
        return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IObjectServicePort objectServicePort() {
        return new ObjectUseCase(objectPersistencePort());
    }


    @Bean
    public IUserServicePort iUserServicePort(IUserPersistencePort userPersistencePort, UserValidations userValidations){
        return new UserUseCase(userPersistencePort,userValidations);
    }

    @Bean
    public IUserPersistencePort iUserPersistencePort(){
        return new UserJpaAdapter(userEntityMapper,userRepository);
    }

    @Bean
    public UserValidations userValidations(){
        return new UserValidations();
    }



}