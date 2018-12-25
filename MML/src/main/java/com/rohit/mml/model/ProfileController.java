package com.rohit.mml.model;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rohit.mml.jacksonviews.UserViews;
import com.rohit.mml.repository.UserRepository;
import com.rohit.mml.util.StringUtils;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String getProfile(Principal principal) {
        // Remove principal and replace with hasRole
        if (principal == null || principal.getName() == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please pass the correct token or check your credentials");

        String username = principal.getName();
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isPresent()) {
            User user = opt.get();
            try {
                return StringUtils.pojoToJsonWithView(user, UserViews.ExtendedPublic.class);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to process json");
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Currently logged in User not found");
    }

    @GetMapping("/{id}")
    public String getProfileById(@PathVariable String id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            try {
                return StringUtils.pojoToJsonWithView(user, UserViews.ExtendedPublic.class);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to process json");
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Currently logged in User not found");
    }

}
