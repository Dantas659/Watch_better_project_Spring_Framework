package br.com.watch_better.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieData(@JsonAlias("Title") String title, @JsonAlias("totalSeasons") int totalSeasons, @JsonAlias("imdbRating") String rating) {

}
