package com.github.nmsilos.cardzbackend.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TokenDTO {

    UUID id;
    String token;

}
