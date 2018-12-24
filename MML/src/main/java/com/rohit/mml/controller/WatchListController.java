package com.rohit.mml.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohit.mml.jacksonviews.UserViews;
import com.rohit.mml.model.User;
import com.rohit.mml.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class WatchListController {

    @Autowired
    private UserRepository userRepository;

    // Get watchlist(but return entire user) for current user - needs JWT token
    @GetMapping(value = { "/watchlist", "/watchlist/current" })
    @PreAuthorize("hasRole('USER')")
    public String getCurrentUserWatchList(Principal principal) {
        if (principal == null || principal.getName() == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please pass the correct token or check your credentials");

        Optional<User> opt = userRepository.findByUsername(principal.getName());
        if (opt.isPresent()) {
            User user = opt.get();
            ObjectMapper mapper = new ObjectMapper();

            try {
                return mapper.writerWithView(UserViews.ExtendedPublic.class).writeValueAsString(user);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to process json");
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

}
