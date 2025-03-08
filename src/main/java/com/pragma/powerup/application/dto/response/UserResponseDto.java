package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String name;
    private String lastName;
    private String documentNumber;
    private String cellPhone;
    private LocalDate birthDate;
    private String email;
    private Role role;

}
