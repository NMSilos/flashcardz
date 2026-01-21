package com.github.nmsilos.cardzbackend.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO {

    String email;
    String password;

}
