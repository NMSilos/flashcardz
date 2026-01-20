package com.github.nmsilos.cardzbackend.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.nmsilos.cardzbackend.dto.deck.DeckRequestDTO;
import com.github.nmsilos.cardzbackend.dto.deck.DeckResponseDTO;
import com.github.nmsilos.cardzbackend.dto.deck.DeckUpdateDTO;
import com.github.nmsilos.cardzbackend.exception.custom.RequiredFieldMissingException;
import com.github.nmsilos.cardzbackend.exception.custom.ResourceNotFoundException;
import com.github.nmsilos.cardzbackend.mapper.DeckMapper;
import com.github.nmsilos.cardzbackend.model.Deck;
import com.github.nmsilos.cardzbackend.repository.DeckRepository;
import com.github.nmsilos.cardzbackend.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectRetrievalFailureException;
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

    @Autowired
    private DeckMapper mapper;

    @Transactional
    public DeckResponseDTO create(DeckRequestDTO deck) {
        try {
            Deck newDeck = buildDeck(deck);
            repository.save(newDeck);
            return mapper.toResponse(newDeck);
        }
        catch (DataIntegrityViolationException e) {
            throw new RequiredFieldMissingException("Required field(s) missing.");
        }
        catch (ObjectRetrievalFailureException e) {
            throw new ResourceNotFoundException("User ID not found.");
        }
    }

    @Transactional(readOnly = true)
    public DeckResponseDTO getDeckById(UUID id) {
        Deck deck = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Deck not found"));
        return mapper.toResponse(deck);
    }

    @Transactional
    public DeckResponseDTO update(DeckUpdateDTO deck) {
        Deck oldDeck = repository.findById(deck.getId()).orElseThrow(() -> new ResourceNotFoundException("Deck not found"));
        if (deck.getName() != null) {
            oldDeck.setName(deck.getName());
            repository.save(oldDeck);
        }
        return mapper.toResponse(oldDeck);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private Deck buildDeck(DeckRequestDTO dto) {
        Deck deck = mapper.toEntity(dto);

        deck.setId(UuidCreator.getTimeOrderedEpoch());
        deck.setCreationDate(new Date());
        deck.setUser(userRepository.getReferenceById(dto.getUser().getId()));

        return deck;
    }

}
