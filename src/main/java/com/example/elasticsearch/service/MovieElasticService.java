package com.example.elasticsearch.service;

import com.example.elasticsearch.model.MovieDocument;

import java.util.List;

public interface MovieElasticService {
    MovieDocument addNewMovie(MovieDocument movieDocument);

    List<MovieDocument> searchMovieByName(String movieName);

    List<MovieDocument> getAllMovies();

    boolean deleteAllMovies();
}
