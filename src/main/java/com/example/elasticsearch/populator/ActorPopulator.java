package com.example.elasticsearch.populator;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomPerson;
import com.example.elasticsearch.model.Actor;
import com.example.elasticsearch.model.ActorDocument;
import com.example.elasticsearch.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class ActorPopulator {

    public static final Random random = new Random();

    private ActorPopulator() {
        throw new UnsupportedOperationException();
    }

    public static List<ActorDocument> createRandomActors() {
        List<ActorDocument> actorDocumentList = new ArrayList<>();
        List<Person> people = RandomPerson.get().listOf(1 + random.nextInt(100));
        people.forEach(person -> actorDocumentList.add(new ActorDocument(person.getFirstName(), person.getLastName())));
        return actorDocumentList;
    }

    public static List<Actor> createRandomActors(Movie movie) {
        List<Actor> actorList = new ArrayList<>();
        List<Person> people = RandomPerson.get().listOf(1 + random.nextInt(100));
        people.forEach(person -> {
            Actor actorCreated = Actor.builder()
                    .firstName(person.getFirstName())
                    .lastName(person.getLastName())
                    .movies(Collections.singletonList(movie)).build();
            actorList.add(actorCreated);
        });
        return actorList;
    }

}
