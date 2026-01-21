package com.github.nmsilos.cardzbackend.repository;

import com.github.nmsilos.cardzbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
