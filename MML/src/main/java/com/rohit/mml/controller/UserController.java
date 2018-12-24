package com.rohit.mml.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rohit.mml.model.User;
import com.rohit.mml.repository.UserRepository;

@RestController()
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> getAllUsers() {
        Pageable pageable = PageRequest.of(0, 10);
        return userRepository.findAll(pageable).getContent();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found");
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username: " + username + " not found");
    }

}
