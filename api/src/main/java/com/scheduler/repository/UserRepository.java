package com.scheduler.repository;

import com.scheduler.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    // Spring Data builds the query from the method name
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
