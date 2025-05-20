package br.com.watch_better.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Season") String season,
                          @JsonAlias("Episode") String episode,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("Released") String released) {
    // Constructor, getters, and other methods can be added if needed

}