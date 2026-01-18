package com.github.nmsilos.cardzbackend.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    private String username;
    private String password;
    private String email;

}
