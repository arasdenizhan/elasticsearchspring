package com.example.elasticsearch.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "movies")
public class MovieDocument {
    @Id
    private String id;

    @Setter
    private String name;

    @Setter
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<ActorDocument> actorDocuments;
}
