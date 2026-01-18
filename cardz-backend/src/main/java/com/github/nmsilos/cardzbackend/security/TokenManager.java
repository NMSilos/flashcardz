package com.github.nmsilos.cardzbackend.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.nmsilos.cardzbackend.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenManager {

    @Value("${jwt.secretkey}")
    private String secretKey;

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("Minha API")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId().toString())
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("flashcardz-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            System.out.println("Erro ao validar token: " + e.getMessage());
            return null;
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}
