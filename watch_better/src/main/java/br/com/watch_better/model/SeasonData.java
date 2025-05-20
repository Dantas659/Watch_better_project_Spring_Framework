package br.com.watch_better.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(@JsonAlias("Season") String season,
                         @JsonAlias("Episodes") List<EpisodeData> episodes) {

}
