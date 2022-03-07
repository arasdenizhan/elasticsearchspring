package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.model.MovieDocument;
import com.example.elasticsearch.repository.MovieElasticRepository;
import com.example.elasticsearch.service.MovieElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MovieElasticServiceImpl implements MovieElasticService {

    private final MovieElasticRepository movieElasticRepository;

    @Autowired
    public MovieElasticServiceImpl(MovieElasticRepository movieElasticRepository) {
        this.movieElasticRepository = movieElasticRepository;
    }

    @Override
    public MovieDocument addNewMovie(MovieDocument movieDocument) {
        return movieElasticRepository.save(movieDocument);
    }

    @Override
    public List<MovieDocument> searchMovieByName(String movieName) {
        return movieElasticRepository.findMoviesByName(movieName, Pageable.unpaged()).getContent();
    }

    @Override
    public List<MovieDocument> getAllMovies() {
        List<MovieDocument> allMovieDocuments = new LinkedList<>();
        movieElasticRepository.findAll().forEach(allMovieDocuments::add);
        return allMovieDocuments;
    }

    @Override
    public boolean deleteAllMovies() {
        if (movieElasticRepository.count() > 0) {
            movieElasticRepository.deleteAll();
            return true;
        }
        return false;
    }
}
