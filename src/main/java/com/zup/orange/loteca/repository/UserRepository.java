package com.zup.orange.loteca.repository;

import com.zup.orange.loteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

}
