package com.pragma.powerup.infrastructure.security;


import com.pragma.powerup.domain.spi.IPasswordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



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
