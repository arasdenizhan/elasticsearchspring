package com.example.elasticsearch.service;

import com.example.elasticsearch.model.Movie;

public interface MovieService {
    Movie addMovie(String name);

    Movie getMovieByName(String name);

    boolean deleteAllMovies();
}
