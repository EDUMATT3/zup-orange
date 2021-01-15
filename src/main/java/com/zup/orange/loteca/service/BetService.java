package com.zup.orange.loteca.service;

import com.zup.orange.loteca.model.Bet;
import com.zup.orange.loteca.model.User;
import com.zup.orange.loteca.repository.BetRepository;
import com.zup.orange.loteca.repository.UserRepository;
import com.zup.orange.loteca.util.Lotto;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BetService {
    private BetRepository betRepository;

    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }


    public Bet saveNewBet(User user){
        String betNumbers = generaValidNumbers(user);
        return betRepository.save(new Bet(betNumbers, user));
    }

    private String generaValidNumbers(User user){
        String betNumbers = Lotto.randomBet();

        Bet betSaved = betRepository.getBetByUserAndNumbers(user, betNumbers);

        while (!Objects.isNull(betSaved)) {
            betNumbers = Lotto.randomBet();
            betSaved = betRepository.getBetByUserAndNumbers(user, betNumbers);
        }

        return betNumbers;
    }
}
