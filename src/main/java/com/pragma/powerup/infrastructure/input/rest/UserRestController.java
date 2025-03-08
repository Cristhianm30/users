package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final IUserHandler userHandler;

    public UserRestController(IUserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @PostMapping("/create/owner")
    public ResponseEntity<UserResponseDto> createOwner(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userHandler.userCreateUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
