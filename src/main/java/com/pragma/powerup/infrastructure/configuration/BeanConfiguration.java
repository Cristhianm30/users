package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IObjectServicePort;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.spi.IObjectPersistencePort;
import com.pragma.powerup.domain.spi.IPasswordEncoder;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.ObjectUseCase;
import com.pragma.powerup.domain.usecase.RoleUseCase;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.domain.usecase.validations.UserValidations;
import com.pragma.powerup.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IObjectRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.security.BCryptPasswordEncoderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public IObjectPersistencePort objectPersistencePort(IObjectEntityMapper objectEntityMapper) {
        return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IObjectServicePort objectServicePort(IObjectPersistencePort objectPersistencePort) {
        return new ObjectUseCase(objectPersistencePort);
    }


    @Bean
    public IUserServicePort iUserServicePort(IUserPersistencePort userPersistencePort,
                                             UserValidations userValidations,
                                             IRoleServicePort roleServicePort,
                                             IPasswordEncoder passwordEncoder){
        return new UserUseCase(userPersistencePort,userValidations,roleServicePort,passwordEncoder);
    }

    @Bean
    public IUserPersistencePort iUserPersistencePort(){
        return new UserJpaAdapter(userEntityMapper,userRepository);
    }

    @Bean
    public UserValidations userValidations(){
        return new UserValidations();
    }

    @Bean
    public IRoleServicePort iRoleServicePort(IRolePersistencePort iRolePersistencePort){
        return new RoleUseCase(iRolePersistencePort);

    }

    @Bean
    public IRolePersistencePort iRolePersistencePort(){
        return new RoleJpaAdapter(roleRepository,roleEntityMapper);
    }

    @Bean
    public IPasswordEncoder iPasswordEncoder(){
        return new BCryptPasswordEncoderAdapter(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }



}