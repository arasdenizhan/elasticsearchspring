package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.model.Movie;
import com.example.elasticsearch.populator.ActorPopulator;
import com.example.elasticsearch.repository.MovieRepository;
import com.example.elasticsearch.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie addMovie(String name) {
        if (Objects.isNull(getMovieByName(name))) {
            Movie movie = Movie.builder().name(name).build();
            movie.setActors(ActorPopulator.createRandomActors(movie));
            return movieRepository.save(movie);
        }
        return null;
    }

    @Override
    public Movie getMovieByName(String name) {
        return movieRepository.findMovieByName(name);
    }

    @Override
    public boolean deleteAllMovies() {
        if (movieRepository.count() > 0) {
            movieRepository.deleteAll();
            return true;
        }
        return false;
    }
}
