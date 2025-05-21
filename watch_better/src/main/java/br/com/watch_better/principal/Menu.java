package br.com.watch_better.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.watch_better.model.Episode;
import br.com.watch_better.model.EpisodeData;
import br.com.watch_better.model.SeasonData;
import br.com.watch_better.model.SerieData;
import br.com.watch_better.service.ApiConsumer;
import br.com.watch_better.service.dataConverter.ConversionService;

public class Menu {
    private ApiConsumer apiConsumer = new ApiConsumer();
    private ConversionService conversionService = new ConversionService();
    private Scanner scanner = new Scanner(System.in);
    private static final String ENDPOINT = "http://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=14c1e91b";



    public void showMenu() {
        System.out.println("Welcome to Watch Better!");
        System.out.println("What do you want to see?");
        String nameSerie = scanner.nextLine();
        String json = apiConsumer.getData(ENDPOINT + nameSerie.replace(" ", "+") + API_KEY);
        SerieData data = conversionService.dataFetcher(json, SerieData.class);   
        System.out.println(data); 

        List<SeasonData> seasons = new ArrayList<>();
        for(int i = 1; i <= data.totalSeasons(); i++) {
            json = apiConsumer.getData(ENDPOINT + nameSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            SeasonData seasonData = conversionService.dataFetcher(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

        List<EpisodeData> episodes = (List<EpisodeData>) seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());
        episodes.stream()
                .filter(e -> e.rating() != null && !e.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::rating).reversed())
                .limit(5)
                .forEach(System.out::println);

            List<Episode> episode = seasons.stream()
            .flatMap(s -> s.episodes().stream()
                .map(e -> new Episode(s.season(), e)))
            .collect(Collectors.toList());   
            episode.forEach(System.out::println);      
    } 

    public void getBetterEpisodes() {
        
    }
}