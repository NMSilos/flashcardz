package com.github.nmsilos.cardzbackend.mapper;

import com.github.nmsilos.cardzbackend.dto.deck.DeckRequestDTO;
import com.github.nmsilos.cardzbackend.dto.deck.DeckResponseDTO;
import com.github.nmsilos.cardzbackend.model.Deck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CardMapper.class)
public interface DeckMapper {

    Deck toEntity(DeckRequestDTO deck);

    @Mapping(target = "userId", source = "user.id")
    DeckResponseDTO toResponse(Deck deck);

}
