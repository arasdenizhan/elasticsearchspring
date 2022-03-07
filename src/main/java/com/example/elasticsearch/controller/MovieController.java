package com.example.elasticsearch.controller;

import com.example.elasticsearch.dto.ActorDto;
import com.example.elasticsearch.dto.MovieDto;
import com.example.elasticsearch.model.Movie;
import com.example.elasticsearch.model.MovieDocument;
import com.example.elasticsearch.populator.ActorPopulator;
import com.example.elasticsearch.populator.MovieDtoPopulator;
import com.example.elasticsearch.service.MovieElasticService;
import com.example.elasticsearch.service.MovieService;
import joptsimple.internal.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final MovieElasticService movieElasticService;

    @Autowired
    public MovieController(MovieService movieService, MovieElasticService movieElasticService) {
        this.movieService = movieService;
        this.movieElasticService = movieElasticService;
    }

    @PostMapping
    public ResponseEntity<Object> addNewMovie(@RequestParam("name") String movieName) {
        if (Strings.isNullOrEmpty(movieName)) {
            return ResponseEntity.badRequest().body("You can't create a movie with null or empty name.");
        }
        Movie movie = movieService.addMovie(movieName);
        if (Objects.isNull(movie)) {
            return ResponseEntity.badRequest().body("Movie with that name already exists.");
        }
        movieElasticService.addNewMovie(MovieDocument.builder().name(movieName).actorDocuments(ActorPopulator.createRandomActors()).build());
        return ResponseEntity.ok(MovieDtoPopulator.populate(movie));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllMovies() {
        if (movieService.deleteAllMovies() && movieElasticService.deleteAllMovies()) {
            return ResponseEntity.ok("Delete All Movies completed");
        }
        return ResponseEntity.badRequest().body("Delete not completed.");
    }
}
