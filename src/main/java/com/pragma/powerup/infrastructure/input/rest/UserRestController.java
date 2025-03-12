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

    @PostMapping("/owner")
    public ResponseEntity<UserResponseDto> createOwner(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userHandler.userCreateOwner(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById (@PathVariable Long id){
        UserResponseDto user = userHandler.userGetById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam String email) {
        UserResponseDto user = userHandler.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


}
