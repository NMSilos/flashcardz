package com.github.nmsilos.cardzbackend.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Cripter {
    public static String criptografar(String texto){
        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder(12);
        return encoder.encode(texto);
    }
}
