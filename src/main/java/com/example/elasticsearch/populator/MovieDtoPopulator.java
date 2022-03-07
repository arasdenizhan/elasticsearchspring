package com.example.elasticsearch.populator;

import com.example.elasticsearch.dto.ActorDto;
import com.example.elasticsearch.dto.MovieDto;
import com.example.elasticsearch.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public final class MovieDtoPopulator {
    private MovieDtoPopulator() {
        throw new UnsupportedOperationException();
    }

    public static MovieDto populate(Movie movie) {
        List<ActorDto> actorDtos = movie.getActors().stream().map(actor ->
                        ActorDto.builder()
                                .id(actor.getId())
                                .firstName(actor.getFirstName())
                                .lastName(actor.getLastName()).build())
                .collect(Collectors.toList());
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .actorDto(actorDtos).build();
    }
}
