package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.MovieDocument;
import com.example.elasticsearch.service.MovieElasticService;
import joptsimple.internal.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final MovieElasticService movieElasticService;

    @Autowired
    public SearchController(MovieElasticService movieElasticService) {
        this.movieElasticService = movieElasticService;
    }

    @GetMapping
    public ResponseEntity<Object> searchMovieByName(@RequestParam(value = "name") String movieName) {
        if (Strings.isNullOrEmpty(movieName)) {
            return ResponseEntity.badRequest().body("You can't search a movie with empty or null name");
        }
        List<MovieDocument> movieDocuments = movieElasticService.searchMovieByName(movieName);
        if (movieDocuments.isEmpty()) {
            return ResponseEntity.ok("No movie found with that name.");
        }
        return ResponseEntity.ok(movieDocuments);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllMovies() {
        List<MovieDocument> allMovies = movieElasticService.getAllMovies();
        if (allMovies.isEmpty()) {
            return ResponseEntity.ok("No movie found.");
        }
        return ResponseEntity.ok(allMovies);
    }
}
