package com.github.nmsilos.cardzbackend.repository;

import com.github.nmsilos.cardzbackend.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}
