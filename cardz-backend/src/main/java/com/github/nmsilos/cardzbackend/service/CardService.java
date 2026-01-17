package com.github.nmsilos.cardzbackend.service;

import com.github.nmsilos.cardzbackend.model.Card;
import com.github.nmsilos.cardzbackend.model.Deck;
import com.github.nmsilos.cardzbackend.repository.CardRepository;
import com.github.nmsilos.cardzbackend.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private DeckRepository deckRepository;

    @Transactional
    public Card create(Card card) {
        Deck deck = deckRepository.getReferenceById(card.getDeck().getId());
        card.setDeck(deck);
        return repository.save(card);
    }

    @Transactional(readOnly = true)
    public Card getCardById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Card update(Card card) {
        Card oldCard = getCardById(card.getId());
        oldCard.setFront(card.getFront());
        oldCard.setBack(card.getBack());
        return repository.save(oldCard);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
