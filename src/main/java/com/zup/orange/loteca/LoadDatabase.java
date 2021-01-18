package com.zup.orange.loteca;

import com.zup.orange.loteca.entities.Bet;
import com.zup.orange.loteca.entities.User;
import com.zup.orange.loteca.repositories.BetRepository;
import com.zup.orange.loteca.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, BetRepository betRepository) {
        return args -> {
            User preloadedUser = userRepository.save(new User("edu@gmail.com"));
            betRepository.save(new Bet("01, 02, 03, 04, 05, 06", preloadedUser));
        };
    }
}
