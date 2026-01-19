package com.github.nmsilos.cardzbackend.dto.deck;

import com.github.nmsilos.cardzbackend.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeckRequestDTO {

    private String name;
    private User user;

}
