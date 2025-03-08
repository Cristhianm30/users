package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    public UserHandlerImpl(IUserServicePort userServicePort, IUserRequestMapper userRequestMapper, IUserResponseMapper userResponseMapper) {
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
    }

    @Override
    public UserResponseDto userCreateUser(UserRequestDto userRequestDto) {
        User request = userRequestMapper.requestToUser(userRequestDto);
        User response = userServicePort.createUser(request);
        return userResponseMapper.userToResponse(response);
    }
}
