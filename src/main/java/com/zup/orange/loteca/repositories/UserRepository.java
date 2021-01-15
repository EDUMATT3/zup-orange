package com.zup.orange.loteca.repositories;

import com.zup.orange.loteca.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
