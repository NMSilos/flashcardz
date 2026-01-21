package com.github.nmsilos.cardzbackend.service;

import com.github.nmsilos.cardzbackend.dto.login.LoginDTO;
import com.github.nmsilos.cardzbackend.dto.login.TokenDTO;
import com.github.nmsilos.cardzbackend.dto.user.UserRequestDTO;
import com.github.nmsilos.cardzbackend.dto.user.UserResponseDTO;
import com.github.nmsilos.cardzbackend.exception.custom.InvalidUserException;
import com.github.nmsilos.cardzbackend.exception.custom.RequiredFieldMissingException;
import com.github.nmsilos.cardzbackend.exception.custom.ResourceNotFoundException;
import com.github.nmsilos.cardzbackend.mapper.UserMapper;
import com.github.nmsilos.cardzbackend.model.User;
import com.github.nmsilos.cardzbackend.repository.UserRepository;
import com.github.nmsilos.cardzbackend.security.Cripter;
import com.github.nmsilos.cardzbackend.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenManager tokenManager;

    @Transactional
    public UserResponseDTO register(UserRequestDTO user) {
        User newUser = mapper.toEntity(user);
        try {
            newUser.setPassword(Cripter.criptografar(user.getPassword()));
            repository.save(newUser);
        }
        catch (DataIntegrityViolationException ex) {
            throw new RequiredFieldMissingException("Non-nullable fields are mandatory");
        }
        return mapper.toResponse(newUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapper.toResponse(user);
    }

    @Transactional
    public UserResponseDTO update(UserRequestDTO user) {
        User oldUser = repository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));

        if (user.getPassword() != null) {
            oldUser.setPassword(Cripter.criptografar(user.getPassword()));
        }
        if (user.getUsername() != null) {
            oldUser.setUsername(user.getUsername());
        }
        repository.save(oldUser);
        return mapper.toResponse(oldUser);

    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public TokenDTO login(LoginDTO data) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
            var authentication = manager.authenticate(authenticationToken);

            if (authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                String token = tokenManager.generateToken(user);
                return new TokenDTO(user.getId(), token);
            }
            return null;
        } catch (AuthenticationException e) {
            throw new InvalidUserException("Login error: email or password incorrect");
        }
    }

}
