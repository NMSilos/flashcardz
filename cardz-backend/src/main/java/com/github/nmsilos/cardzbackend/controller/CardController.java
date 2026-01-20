package com.github.nmsilos.cardzbackend.controller;

import com.github.nmsilos.cardzbackend.dto.card.CardRequestDTO;
import com.github.nmsilos.cardzbackend.dto.card.CardResponseDTO;
import com.github.nmsilos.cardzbackend.dto.card.CardUpdateDTO;
import com.github.nmsilos.cardzbackend.model.Card;
import com.github.nmsilos.cardzbackend.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/cards")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping("/create")
    private ResponseEntity<CardResponseDTO> create(@RequestBody CardRequestDTO card) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(card));
    }

    @GetMapping("/{id}")
    private ResponseEntity<CardResponseDTO> getCardById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCardById(id));
    }

    @PutMapping("/update")
    private ResponseEntity<CardResponseDTO> update(@RequestBody CardUpdateDTO card) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(card));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
