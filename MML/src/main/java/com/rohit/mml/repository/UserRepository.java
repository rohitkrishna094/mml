package com.rohit.mml.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rohit.mml.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
