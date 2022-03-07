package com.example.elasticsearch.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {
    private Long id;
    private String firstName;
    private String lastName;
}
