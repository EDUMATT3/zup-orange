package com.zup.orange.loteca.resources;

import com.zup.orange.loteca.entities.User;
import com.zup.orange.loteca.entities.Bet;
import com.zup.orange.loteca.services.BetService;
import com.zup.orange.loteca.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "api/")
public class LotecaController {

    @Autowired
    private BetService betService;
    @Autowired
    private UserService userService;

    @PostMapping("/bets")
    ResponseEntity<Bet> generateABet(@RequestBody @Valid User reqUser) {
        User user = userService.getOrCreateUser(reqUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(betService.saveNewBet(user));
    }

    @GetMapping("/bets")
    List<Bet> getAllBets(@Email(message = "Valid email format required") @RequestParam("email")  String  email) {
        return userService.getBets(new User(email));
    }
}
