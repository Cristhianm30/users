package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

public interface IJwtTokenProviderPort {
    String generateToken(User user);
    boolean validateToken(String token);
    String getEmailFromToken(String token);
}