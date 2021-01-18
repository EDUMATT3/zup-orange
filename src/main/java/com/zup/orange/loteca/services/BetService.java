package com.zup.orange.loteca.services;

import com.zup.orange.loteca.entities.Bet;
import com.zup.orange.loteca.entities.User;
import com.zup.orange.loteca.repositories.BetRepository;
import com.zup.orange.loteca.util.Lotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;

    public Bet saveNewBet(User user){
        String betNumbers = generaValidNumbers(user);
        return betRepository.save(new Bet(betNumbers, user));
    }

    private String generaValidNumbers(User user){
        String betNumbers = Lotto.randomBet();

        Optional<Bet> betSaved = betRepository.findOne(Example.of(new Bet(betNumbers, user)));

        while (betSaved.isPresent()) {
            betNumbers = Lotto.randomBet();
            betSaved = betRepository.findOne(Example.of(new Bet(betNumbers, user)));
        }

        return betNumbers;
    }
}
