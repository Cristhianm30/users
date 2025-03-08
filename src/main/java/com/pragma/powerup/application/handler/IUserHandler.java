package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.User;

public interface IUserHandler {

    UserResponseDto userCreateUser(UserRequestDto userRequestDto);
}
