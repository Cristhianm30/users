package com.pragma.powerup.infrastructure.security;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserServicePort userServicePort;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userServicePort.getUserByEmail(email);
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_"+user.getRole().getName())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
