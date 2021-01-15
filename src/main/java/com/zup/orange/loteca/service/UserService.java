package com.zup.orange.loteca.service;

import com.zup.orange.loteca.exception.UserNotFoundException;
import com.zup.orange.loteca.model.Bet;
import com.zup.orange.loteca.model.User;
import com.zup.orange.loteca.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(User reqUser){
        User user = userRepository.findByEmail(reqUser.getEmail());
        if(Objects.isNull(user))
            return userRepository.save(reqUser);
        return user;
    }

    public List<Bet> getBets(User reqUser){
        User user = userRepository.findByEmail(reqUser.getEmail());
        if(Objects.isNull(user))
            throw new UserNotFoundException("User with this email not found");
        return user.getBetList();
    }

}
