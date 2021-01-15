package com.zup.orange.loteca.controller;

import com.zup.orange.loteca.model.User;
import com.zup.orange.loteca.model.Bet;
import com.zup.orange.loteca.repository.UserRepository;
import com.zup.orange.loteca.repository.BetRepository;
import com.zup.orange.loteca.service.BetService;
import com.zup.orange.loteca.service.UserService;
import com.zup.orange.loteca.util.Lotto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
public class LotecaController {

    private BetService betService;
    private UserService userService;

    public LotecaController(BetService betService, UserService userService) {
        this.betService = betService;
        this.userService = userService;
    }

    @PostMapping("/bet")
    Bet getABet(@Valid @RequestBody User reqUser) {
        User user = userService.getOrCreateUser(reqUser);
        return betService.saveNewBet(user);
    }

    @PostMapping("/all-bets")
    List<Bet> getAllBets(@Valid @RequestBody User reqUser) {
        return userService.getBets(reqUser);
    }
}
