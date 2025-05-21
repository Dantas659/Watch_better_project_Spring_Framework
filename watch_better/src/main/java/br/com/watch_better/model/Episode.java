package br.com.watch_better.model;

import java.time.LocalDate;

public class Episode {
    private String season;
    private Integer episodeNumber;
    private String title;
    private Double rating;
    private LocalDate releaseDate;

    public Episode(String seasonNumber, EpisodeData episodeData) {
        this.season = seasonNumber;
        this.episodeNumber = Integer.parseInt(episodeData.episode());
        this.title = episodeData.title();
        try {
            this.rating = Double.parseDouble(episodeData.rating());
        } catch (NumberFormatException e) {
            this.rating = 0.0; 
        }
        try {
            this.releaseDate = LocalDate.parse(episodeData.released());
        } catch (Exception e) {
            this.releaseDate = null;
        }

    }
    public String getSeason(){
        return season;
    }
    public Integer getEpisodeNumber(){
        return episodeNumber;
    }
    public String getTitle(){
        return title;
    }
    public Double getRating(){
        return rating;
    }
    public LocalDate getReleaseDate(){
        return releaseDate;
    }

    public void setSeason(String season) {
        this.season = season;
    }
    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
@Override
    public String toString() {
        return 
                "season='" + season + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate;
    }

}
