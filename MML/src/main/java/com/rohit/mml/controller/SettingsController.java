package com.rohit.mml.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rohit.mml.model.Settings;
import com.rohit.mml.model.User;
import com.rohit.mml.repository.UserRepository;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public Settings getSettings(Principal principal) {
        if (principal == null || principal.getName() == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please pass the correct token or check your credentials");

        String username = principal.getName();
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isPresent()) {
            User user = opt.get();
            return user.getSettings();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

}
