package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;


    @Override
    public UserResponseDto userCreateOwner(UserRequestDto userRequestDto) {
        User request = userRequestMapper.requestToUser(userRequestDto);
        User response = userServicePort.createOwner(request);
        return userResponseMapper.userToResponse(response);
    }

    @Override
    public UserResponseDto userGetById(Long id){
        User user = userServicePort.getUserById(id);
        return userResponseMapper.userToResponse(user);
    }
}
