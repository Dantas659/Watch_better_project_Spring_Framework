package br.com.watch_better.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
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



    /**
     * 
     */
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

        
        System.out.println("Since which year do you want to see the episodes?");
        String yearInput = scanner.nextLine();
        LocalDate userDate = LocalDate.of(Integer.parseInt(yearInput), 1, 1);

       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd", Locale.ENGLISH);

        episodes.stream()
            .filter(e -> {
                if (e.released() == null) {
                    return false;
                }
                try {
                    LocalDate episodeDate = LocalDate.parse(e.released(), formatter);
                    return episodeDate.isAfter(userDate);
                } catch (Exception ex) {
                     System.out.println("Data inválida para o episódio: " + e.title());
                    return false;
                }
            })
            .forEach(System.out::println);


    }

    public void getBetterEpisodes() {
        
    }
}