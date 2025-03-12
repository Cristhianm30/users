package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.AuthRequestDto;
import com.pragma.powerup.application.dto.response.AuthResponseDto;
import com.pragma.powerup.application.handler.IAuthHandler;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IJwtTokenProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandlerImpl implements IAuthHandler {

    private final AuthenticationManager authenticationManager;
    private final IJwtTokenProviderPort jwtTokenProvider;
    private final IUserServicePort userServicePort;

    @Override
    public AuthResponseDto authenticate(AuthRequestDto request) {
        // Autenticar usando Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Obtener el User del dominio
        User user = userServicePort.getUserByEmail(request.getEmail());

        // Generar token usando el modelo del dominio
        return new AuthResponseDto(jwtTokenProvider.generateToken(user));
    }
}
