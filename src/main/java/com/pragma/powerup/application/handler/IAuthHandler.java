package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.AuthRequestDto;
import com.pragma.powerup.application.dto.response.AuthResponseDto;

public interface IAuthHandler {
    AuthResponseDto authenticate(AuthRequestDto request);
}
