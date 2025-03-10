package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.RoleDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    User responseToUser(UserResponseDto userResponseDto);
    UserResponseDto userToResponse(User user);
    RoleDto roletoDto (Role role);

}
