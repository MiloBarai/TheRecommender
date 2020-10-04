package com.ours.the.recommender.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
abstract public class AbstractRecommendation {
    private String id;
    private String name;
    private RecommendationType type;
    private Set<RecommendationGenre> genres;
    private Double rating;
}

