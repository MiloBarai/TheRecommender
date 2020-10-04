package com.ours.the.recommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimeLineEntity {
    String id;
    String name;
    String genre;
    String type;
    String episodes;
    String rating;
    String members;

    public boolean isComplete(){
        return !(name.isEmpty() || genre.isEmpty() || type.isEmpty() || rating.isEmpty());
    }
}
