package com.ours.the.recommender.model;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class testCsv {

    @Test
    public void testCSV(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("anime.csv")));
        Set<String> genres = reader.lines()
                                   .skip(1)
                                   .map(s -> s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"))
                                   .map(s -> s[2])
                                   .filter(s -> !s.isEmpty())
                                   .map(s -> s.replace("\"",""))
                                   .flatMap(s -> Arrays.stream(s.split(", ")))
                                   .map(s -> s.toUpperCase())
                                   .map(s -> s.replace(" ", "_"))
                                   .collect(Collectors.toSet());

        genres.forEach(s -> System.out.println(s+","));
    }
}
