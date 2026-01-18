package com.github.nmsilos.cardzbackend.mapper;

import com.github.nmsilos.cardzbackend.dto.user.UserRegisterDTO;
import com.github.nmsilos.cardzbackend.dto.user.UserResponseDTO;
import com.github.nmsilos.cardzbackend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegisterDTO user);

    UserResponseDTO toResponse(User user);

}
