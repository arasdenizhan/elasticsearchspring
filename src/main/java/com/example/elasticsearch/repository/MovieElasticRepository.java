package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.MovieDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieElasticRepository extends ElasticsearchRepository<MovieDocument, String> {
    Page<MovieDocument> findMoviesByName(String name, Pageable pageable);
}
