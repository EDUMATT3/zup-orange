package com.zup.orange.loteca.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Bet {

    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "numbers")
    String numbers;


    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "id_player")
    private User user;

    public Bet(){}

    public Bet(String numbers, User user) {
        this.numbers = numbers;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getNumbers() {
        return numbers;
    }

    @JsonIgnore
    public User getPlayer() {
        return user;
    }
}
