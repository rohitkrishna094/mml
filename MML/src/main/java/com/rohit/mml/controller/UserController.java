package com.rohit.mml.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return userRepository.findAll(PageRequest.of(0, 10)).getContent();
    }

    // Todo add more query params
    @GetMapping("/search")
    public List<User> getUsers(@RequestParam(value = "page", defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int size) {
        if (pageNumber < 0 || size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Check page and size get param values. Page should not be less than zero while size should not be less than 1");
        }
        return userRepository.findAll(PageRequest.of(pageNumber, size)).getContent();
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
