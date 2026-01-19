package com.github.nmsilos.cardzbackend.mapper;

import com.github.nmsilos.cardzbackend.dto.user.UserRequestDTO;
import com.github.nmsilos.cardzbackend.dto.user.UserResponseDTO;
import com.github.nmsilos.cardzbackend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DeckMapper.class)
public interface UserMapper {

    User toEntity(UserRequestDTO user);

    UserResponseDTO toResponse(User user);

}
