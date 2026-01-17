package com.github.nmsilos.cardzbackend.service;

import com.github.nmsilos.cardzbackend.model.Deck;
import com.github.nmsilos.cardzbackend.model.User;
import com.github.nmsilos.cardzbackend.repository.DeckRepository;
import com.github.nmsilos.cardzbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class DeckService {

    @Autowired
    private DeckRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Deck create(Deck deck) {
        User user = userRepository.getReferenceById(deck.getUser().getId());
        deck.setCreationDate(new Date());
        deck.setUser(user);
        return repository.save(deck);
    }

    @Transactional(readOnly = true)
    public Deck getDeckById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Deck update(Deck deck) {
        Deck oldDeck = getDeckById(deck.getId());
        oldDeck.setName(deck.getName());
        return repository.save(oldDeck);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
