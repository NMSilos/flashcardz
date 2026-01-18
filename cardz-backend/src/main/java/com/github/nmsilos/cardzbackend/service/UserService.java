package com.github.nmsilos.cardzbackend.service;

import com.github.nmsilos.cardzbackend.dto.user.UserRequestDTO;
import com.github.nmsilos.cardzbackend.dto.user.UserResponseDTO;
import com.github.nmsilos.cardzbackend.mapper.UserMapper;
import com.github.nmsilos.cardzbackend.model.User;
import com.github.nmsilos.cardzbackend.repository.UserRepository;
import com.github.nmsilos.cardzbackend.security.Cripter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Transactional
    public UserResponseDTO register(UserRequestDTO user) {
        User newUser = mapper.toEntity(user);
        newUser.setPassword(Cripter.criptografar(user.getPassword()));
        repository.save(newUser);
        return mapper.toResponse(newUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id) {
        User user = repository.findById(id).orElse(null);
        return mapper.toResponse(user);
    }

    @Transactional
    public UserResponseDTO update(UserRequestDTO user) {
        User oldUser = repository.findByEmail(user.getEmail());
        if (oldUser != null) {
            if (user.getPassword() != null) {
                oldUser.setPassword(Cripter.criptografar(user.getPassword()));
            }
            if (user.getUsername() != null) {
                oldUser.setUsername(user.getUsername());
            }
            repository.save(oldUser);
            return mapper.toResponse(oldUser);
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
