package com.rohit.mml.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rohit.mml.jacksonviews.UserViews;
import com.rohit.mml.model.Movie;
import com.rohit.mml.model.User;
import com.rohit.mml.model.WatchList;
import com.rohit.mml.repository.MovieRepository;
import com.rohit.mml.repository.UserRepository;
import com.rohit.mml.util.NullAwareBeanArrayUtilsBean;
import com.rohit.mml.util.StringUtils;

@RestController
@RequestMapping("/api/users")
public class WatchListController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    // Get watchlist(but return entire user) for current user - needs JWT token
    @GetMapping(value = { "/watchlist", "/watchlist/current" })
    @PreAuthorize("hasRole('USER')")
    public String getCurrentUserWatchList(Principal principal) {
        if (principal == null || principal.getName() == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please pass the correct token or check your credentials");

        Optional<User> opt = userRepository.findByUsername(principal.getName());
        if (opt.isPresent()) {
            User user = opt.get();
            try {
                return StringUtils.pojoToJsonWithView(user, UserViews.ExtendedPublic.class);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to process json");
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    // Get watchlist(but return entire user) for user with id id.
    @GetMapping("/{id}/watchlist/")
    public String getWatchListForCertainUser(@PathVariable String id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            try {
                return StringUtils.pojoToJsonWithView(user, UserViews.ExtendedPublic.class);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to process json");
            }

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
    }

    @PostMapping("/watchlist/add")
    public String addItem(@RequestBody Map<String, Movie> map, Principal principal) throws JsonParseException, JsonMappingException, IOException {
        if (principal == null || principal.getName() == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please pass the correct token or check your credentials");

        Map.Entry<String, Movie> entry = map.entrySet().iterator().next();
        String key = entry.getKey();
        Movie movie = (Movie) map.get(key);

        if (map == null || map.size() != 1 || movie.getImdbID() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please pass only one field and make sure the value for that field is a movie schema");
        Optional<Movie> mOpt = movieRepository.findByImdbID(movie.getImdbID());
        System.out.println(mOpt.isPresent());
        if (!mOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please make sure that imdbID is correct");
        } else {
            movie = mOpt.get();
        }

        String username = principal.getName();
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isPresent()) {
            User user = opt.get();
            boolean result = user.addItem(map);
            if (result == false)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please make sure the field in request body is right. Check the schema for this endpoint");
            User saved = userRepository.save(user);
            try {
                return StringUtils.pojoToJsonWithView(saved, UserViews.ExtendedPublic.class);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to process json");
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Current logged in user not found");
    }

    // Delete or remove entry from watchlist mid represent movie id from mongodb, not imdbID
    @DeleteMapping("/watchlist/{mid}")
    public String deleteItem(Principal principal, @PathVariable String mid) {
        if (principal == null || principal.getName() == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please pass the correct token or check your credentials");

        String username = principal.getName();
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isPresent()) {
            User user = opt.get();
            Optional<Movie> mOpt = movieRepository.findById(mid);
            if (mOpt.isPresent()) {
                user.removeItem(mOpt.get());
                User saved = userRepository.save(user);
                try {
                    return StringUtils.pojoToJsonWithView(saved, UserViews.ExtendedPublic.class);
                } catch (JsonProcessingException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to process json");
                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + mid + " not found.");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Current logged in user not found");
    }

    // Save a new watchlist's item into this list only if user is the current user
    @PostMapping("/{id}/watchlist/save")
    public String saveWatchList(Principal principal, @PathVariable String id, @RequestBody WatchList watchList) {
        // check if user is current user
        if (principal == null || principal.getName() == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please pass the correct token or check your credentials");
        String username = principal.getName();
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            if (!user.getUsername().equals(username))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not allowed to update watchlist for other users");

            // Using this approach we have to send entire watchList object. Change it so that clients can only send a part of it
            BeanUtilsBean notNull = new NullAwareBeanArrayUtilsBean();
            WatchList w = user.getWatchList();
            try {
                notNull.copyProperties(w, watchList);
                user.setWatchList(w);
                User saved = userRepository.save(user);
                return StringUtils.pojoToJsonWithView(saved, UserViews.ExtendedPublic.class);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to copy beans");
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to process json");
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
    }
}
