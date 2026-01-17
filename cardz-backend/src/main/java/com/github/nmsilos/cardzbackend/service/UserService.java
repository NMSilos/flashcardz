package com.github.nmsilos.cardzbackend.service;

import com.github.nmsilos.cardzbackend.model.User;
import com.github.nmsilos.cardzbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User register(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        repository.save(newUser);
        return newUser;
    }
}
