package com.zup.orange.loteca.resources;

import com.zup.orange.loteca.entities.User;
import com.zup.orange.loteca.entities.Bet;
import com.zup.orange.loteca.services.BetService;
import com.zup.orange.loteca.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LotecaController {

    @Autowired
    private BetService betService;
    @Autowired
    private UserService userService;

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
