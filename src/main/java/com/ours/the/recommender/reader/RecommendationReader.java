package com.ours.the.recommender.reader;

import com.ours.the.recommender.model.AbstractRecommendation;

import java.util.List;

public interface RecommendationReader {
    List<AbstractRecommendation> readRecommendations();
}
