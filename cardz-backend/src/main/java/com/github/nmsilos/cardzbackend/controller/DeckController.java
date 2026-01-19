package com.github.nmsilos.cardzbackend.controller;

import com.github.nmsilos.cardzbackend.dto.deck.DeckRequestDTO;
import com.github.nmsilos.cardzbackend.dto.deck.DeckResponseDTO;
import com.github.nmsilos.cardzbackend.model.Deck;
import com.github.nmsilos.cardzbackend.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/decks")
public class DeckController {

    @Autowired
    private DeckService service;

    @PostMapping("/create")
    private ResponseEntity<DeckResponseDTO> create(@RequestBody DeckRequestDTO deck) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(deck));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Deck> getDeckById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getDeckById(id));
    }

    @PutMapping("/update")
    private ResponseEntity<Deck> update(@RequestBody Deck deck) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(deck));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
