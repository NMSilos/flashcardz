package com.github.nmsilos.cardzbackend.mapper;

import com.github.nmsilos.cardzbackend.dto.card.CardRequestDTO;
import com.github.nmsilos.cardzbackend.dto.card.CardResponseDTO;
import com.github.nmsilos.cardzbackend.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card toEntity(CardRequestDTO card);

    @Mapping(target = "deckId", source = "deck.id")
    CardResponseDTO toResponse(Card card);

}
