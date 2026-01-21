package com.github.nmsilos.cardzbackend.security;

import com.github.nmsilos.cardzbackend.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = tokenManager.getSubject(token);

            if (email != null) {
                userRepository.findByEmail(email)
                        .ifPresent(user -> {
                            var authentication =
                                    new UsernamePasswordAuthenticationToken(
                                            user,
                                            null,
                                            user.getAuthorities()
                                    );
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        });
            } else {
                System.out.println("Token inválido ou expirado.");
            }
        }
        filterChain.doFilter(request, response);
    }
}
