package com.github.nmsilos.cardzbackend.dto.deck;

import com.github.nmsilos.cardzbackend.dto.card.CardResponseDTO;
import com.github.nmsilos.cardzbackend.dto.user.UserResponseDTO;
import com.github.nmsilos.cardzbackend.model.Card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DeckResponseDTO {

    private UUID id;
    private String name;
    private Date creationDate;
    private UUID userId;
    private List<CardResponseDTO> cards;

}
