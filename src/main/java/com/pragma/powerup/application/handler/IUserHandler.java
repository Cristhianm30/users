package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;


public interface IUserHandler {

    UserResponseDto userCreateOwner(UserRequestDto userRequestDto);
    UserResponseDto userGetById (Long id);
    UserResponseDto getUserByEmail( String email);
    UserResponseDto userCreateEmployee(UserRequestDto userRequestDto);
    UserResponseDto userCreateClient(UserRequestDto userRequestDto);
}
