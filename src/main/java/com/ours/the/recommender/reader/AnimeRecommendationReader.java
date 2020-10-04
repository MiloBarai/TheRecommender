package com.ours.the.recommender.reader;

import com.google.common.collect.Lists;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.ours.the.recommender.entity.AnimeLineEntity;
import com.ours.the.recommender.model.AbstractRecommendation;

import com.ours.the.recommender.model.AnimeRecommendation;
import com.ours.the.recommender.model.RecommendationGenre;
import com.ours.the.recommender.model.RecommendationType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AnimeRecommendationReader implements RecommendationReader {
    private String resourceName;

    public static Map<String, String> mapping = Map.of("anime_id", "id",
            "name", "name",
            "genre", "genre",
            "type", "type",
            "episodes", "episodes",
            "rating", "rating",
            "members", "members");

    @Override
    @SneakyThrows
    public List<AbstractRecommendation> readRecommendations() {
        File file = new File(ClassLoader.getSystemResource(resourceName).getPath());

        HeaderColumnNameTranslateMappingStrategy<AnimeLineEntity> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();

        strategy.setType(AnimeLineEntity.class);
        strategy.setColumnMapping(mapping);

        CSVReader reader = new CSVReader(new FileReader(file));
        CsvToBean csvToBean = new CsvToBean();
        csvToBean.setCsvReader(reader);
        csvToBean.setMappingStrategy(strategy);
        List<AnimeLineEntity> entities = csvToBean.parse();

        return entities.stream()
                       .filter(AnimeLineEntity::isComplete)
                       .map(this::mapToRecommendation)
                       .collect(Collectors.toList());
    }

    public AbstractRecommendation mapToRecommendation(AnimeLineEntity entity){
        return AnimeRecommendation.builder()
                                  .id(entity.getId())
                                  .genres(getRecommendationGenre(entity.getGenre()))
                                  .name(entity.getName())
                                  .rating(Double.parseDouble(entity.getRating()))
                                  .type(RecommendationType.valueOf(entity.getType().toUpperCase()))
                                  .build();
    }

    public Set<RecommendationGenre> getRecommendationGenre(String stringGenreList){
        return Arrays.stream(stringGenreList.split(", "))
                     .map(String::toUpperCase)
                     .map(s -> s.replaceAll("\\-|\\s", "_"))
                     .map(RecommendationGenre::valueOf)
                     .collect(Collectors.toSet());
    }
}
