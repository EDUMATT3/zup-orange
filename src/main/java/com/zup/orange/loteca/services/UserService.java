package com.zup.orange.loteca.services;

import com.zup.orange.loteca.exceptions.UserNotFoundException;
import com.zup.orange.loteca.entities.Bet;
import com.zup.orange.loteca.entities.User;
import com.zup.orange.loteca.repositories.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(User reqUser){
        Optional<User> user = userRepository.findOne(Example.of(reqUser));

        if(!user.isPresent()) return userRepository.save(reqUser);

        return user.get();
    }

    public List<Bet> getBets(User reqUser){
        User user = userRepository.findOne(Example.of(reqUser))
                .orElseThrow(() -> new UserNotFoundException("Email not found"));

        return user.getBetList();
    }

}
