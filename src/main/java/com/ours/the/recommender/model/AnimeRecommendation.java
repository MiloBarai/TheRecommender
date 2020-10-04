package com.ours.the.recommender.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class AnimeRecommendation extends AbstractRecommendation {

    @Override
    public String toString(){
        return String.format("[name: %s, rating: %s, genres: %s]", getName(), getRating(), getGenres());
    }
}
