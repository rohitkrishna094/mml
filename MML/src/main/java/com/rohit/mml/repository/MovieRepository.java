package com.rohit.mml.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rohit.mml.model.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, String> {
    Optional<Movie> findByImdbID(String imdbID);
}
