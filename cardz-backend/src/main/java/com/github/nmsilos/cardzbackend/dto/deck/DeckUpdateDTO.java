package com.github.nmsilos.cardzbackend.dto.deck;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeckUpdateDTO {

    private UUID id;
    private String name;

}
