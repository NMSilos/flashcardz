package com.github.nmsilos.cardzbackend.dto.card;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CardRequestDTO {

    private String front;
    private String back;
    private UUID deckId;

}
