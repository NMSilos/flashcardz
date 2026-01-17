package com.github.nmsilos.cardzbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "decks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Deck {

    @Id
    @GeneratedValue
    @Column(
            name = "id",
            columnDefinition = "BINARY(16)",
            nullable = false,
            updatable = false
    )
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
