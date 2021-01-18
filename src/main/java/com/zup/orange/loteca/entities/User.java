package com.zup.orange.loteca.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email(message = "A valid email format is required")
    private String email;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "id_player")
    private List<Bet> betList;

    public User(@Email String email, List<Bet> betList) {
        this.email = email;
        this.betList = betList;
    }

    public User(@Email String email) {
        this.email = email;
    }
}
