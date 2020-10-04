package com.ours.the.recommender;

import com.google.common.collect.Lists;
import com.ours.the.recommender.model.AbstractRecommendation;
import com.ours.the.recommender.model.AnimeRecommendation;
import com.ours.the.recommender.model.RecommendationGenre;
import com.ours.the.recommender.reader.AnimeRecommendationReader;
import com.ours.the.recommender.reader.RecommendationReader;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    public static void main(String [] args){
        List<AbstractRecommendation> recommendations = getAnimeRecommendations();
        List<AbstractRecommendation> genre = getByGenre(recommendations, RecommendationGenre.ACTION, RecommendationGenre.COMEDY);

        genre.forEach(System.out::println);
    }

    public static List<AbstractRecommendation> getAnimeRecommendations(){
        List<AbstractRecommendation> animes = new AnimeRecommendationReader("anime.csv").readRecommendations();
        animes.sort(Comparator.comparing(AbstractRecommendation::getRating));
        Collections.reverse(animes);
        return animes;
    }

    public static List<AbstractRecommendation> getByGenre(List<AbstractRecommendation> recommendations, RecommendationGenre ...genre){
        List<RecommendationGenre> genres = Lists.newArrayList(genre);
        return recommendations.stream()
                              .filter(abstractRecommendation -> abstractRecommendation.getGenres()
                                                                                      .containsAll(genres))
                              .collect(Collectors.toList());
    }
}
