package com.github.nmsilos.cardzbackend.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.nmsilos.cardzbackend.dto.card.CardRequestDTO;
import com.github.nmsilos.cardzbackend.dto.card.CardResponseDTO;
import com.github.nmsilos.cardzbackend.dto.card.CardUpdateDTO;
import com.github.nmsilos.cardzbackend.exception.custom.RequiredFieldMissingException;
import com.github.nmsilos.cardzbackend.exception.custom.ResourceNotFoundException;
import com.github.nmsilos.cardzbackend.mapper.CardMapper;
import com.github.nmsilos.cardzbackend.model.Card;
import com.github.nmsilos.cardzbackend.model.Deck;
import com.github.nmsilos.cardzbackend.repository.CardRepository;
import com.github.nmsilos.cardzbackend.repository.DeckRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardMapper mapper;

    @Transactional
    public CardResponseDTO create(CardRequestDTO card) {
        try {
            Deck deck = deckRepository.getReferenceById(card.getDeckId());
            Card newCard = repository.save(buildCard(card));
            return mapper.toResponse(newCard);
        }
        catch (DataIntegrityViolationException ex) {
            throw new RequiredFieldMissingException("Non-nullable fields are mandatory");
        }
    }

    @Transactional(readOnly = true)
    public CardResponseDTO getCardById(UUID id) {
        Card card = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        return mapper.toResponse(card);
    }

    @Transactional
    public CardResponseDTO update(CardUpdateDTO card) {
        try {
            Card oldCard = repository.getReferenceById(card.getId());
            if (card.getFront() != null) {
                oldCard.setFront(card.getFront());
            }
            if (card.getBack() != null) {
                oldCard.setBack(card.getBack());
            }
            repository.save(oldCard);
            return mapper.toResponse(oldCard);
        }
        catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Card not found");
        }
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private Card buildCard(CardRequestDTO dto) {
        Card card = mapper.toEntity(dto);
        card.setDeck(deckRepository.getReferenceById(dto.getDeckId()));
        return card;
    }
}
