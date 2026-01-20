package com.github.nmsilos.cardzbackend.dto.card;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CardUpdateDTO {

    private UUID id;
    private String front;
    private String back;

}
