package com.zup.orange.loteca.repository;

import com.zup.orange.loteca.model.Bet;
import com.zup.orange.loteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> getByUser(User user);
    Bet getBetByUserAndNumbers(User user, String numbers);
}
