package com.pragma.powerup.infrastructure.security;


import com.pragma.powerup.domain.spi.IPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


public class BCryptPasswordEncoderAdapter implements IPasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoderAdapter(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encode(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }
}
