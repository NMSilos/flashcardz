package com.github.nmsilos.cardzbackend.dto.user;

import com.github.nmsilos.cardzbackend.dto.deck.DeckResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserResponseDTO {

    private UUID id;
    private String username;
    private String email;
    private List<DeckResponseDTO> decks;

}
