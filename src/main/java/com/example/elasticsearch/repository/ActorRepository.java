package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor getActorByFirstNameAndLastName(String firstName, String lastName);
}
