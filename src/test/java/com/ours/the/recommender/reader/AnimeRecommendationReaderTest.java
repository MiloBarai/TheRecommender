package com.ours.the.recommender.reader;

import com.ours.the.recommender.model.AbstractRecommendation;
import com.ours.the.recommender.model.AnimeRecommendation;
import com.ours.the.recommender.model.RecommendationGenre;
import com.ours.the.recommender.model.RecommendationType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AnimeRecommendationReaderTest {

    @Test
    void readRecommendationsTestHaikyuu() {
        AnimeRecommendationReader reader = new AnimeRecommendationReader("anime.csv");
        List<AbstractRecommendation> recommendations = reader.readRecommendations();

        //32935,Haikyuu!!: Karasuno Koukou VS Shiratorizawa Gakuen Koukou,"Comedy, Drama, School, Shounen, Sports",TV,10,9.15,93351
        assertThat(recommendations).contains(
                AnimeRecommendation.builder()
                                   .id("32935")
                                   .name("Haikyuu!!: Karasuno Koukou VS Shiratorizawa Gakuen Koukou")
                                   .genres(Set.of(RecommendationGenre.COMEDY, RecommendationGenre.DRAMA, RecommendationGenre.SCHOOL, RecommendationGenre.SHOUNEN, RecommendationGenre.SPORTS))
                                   .type(RecommendationType.TV)
                                   .rating(9.15)
                                   .build()
        );
    }
}