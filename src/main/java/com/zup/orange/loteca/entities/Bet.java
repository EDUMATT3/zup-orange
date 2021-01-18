package com.zup.orange.loteca.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Bet {

    private @Id
    @GeneratedValue
    Long id;

    String numbers;

    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "id_player")
    private User user;

    public Bet(String numbers, User user) {
        this.numbers = numbers;
        this.user = user;
    }
}
