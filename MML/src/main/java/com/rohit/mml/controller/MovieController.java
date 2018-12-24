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

import com.rohit.mml.model.Movie;
import com.rohit.mml.repository.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    // Todo: Add more query params
    // Get list of movies
    @GetMapping("/search")
    public List<Movie> getMovies(@RequestParam(value = "page", defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int size) {
        if (pageNumber < 0 || size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Check page and size get param values. Page should not be less than zero while size should not be less than 1");
        }
        return movieRepository.findAll(PageRequest.of(pageNumber, size)).getContent();
    }

    // Get specific movie info
    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable String id) {
        Optional<Movie> opt = movieRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + id + " not found");
    }

    // Todo: Edit and delete movies if users are mods

}
