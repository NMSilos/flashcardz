package com.github.nmsilos.cardzbackend.service;

import com.github.nmsilos.cardzbackend.dto.user.UserRegisterDTO;
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
    public UserResponseDTO register(UserRegisterDTO user) {
        User newUser = mapper.toEntity(user);
        newUser.setPassword(Cripter.criptografar(user.getPassword()));
        repository.save(newUser);
        return mapper.toResponse(newUser);
    }

    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public User update(User user) {
        User oldUser = repository.findById(user.getId()).orElseThrow(null);
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        return repository.save(oldUser);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
