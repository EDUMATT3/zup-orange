package com.zup.orange.loteca.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @NotNull(message = "A email is required")
    @Email(message = "A valid email format is required")

    private String email;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "id_player")
    private List<Bet> betList;

    public User() {}

    public User(@Email String email, List<Bet> betList) {
        this.email = email;
        this.betList = betList;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Bet> getBetList() {
        return betList;
    }
}
