package com.rohit.mml.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // Get list of movies
    @GetMapping("/search")
    public List<Movie> getMovies(@RequestParam(value = "page") int pageNumber, @RequestParam int size) {
        if (pageNumber < 0 || size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Check page and size get param values. Page should not be less than zero while size should not be less than 1");
        }
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<Movie> page = movieRepository.findAll(pageable);
        return page.getContent();
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

}
