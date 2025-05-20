package br.com.watch_better;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import br.com.watch_better.model.EpisodeData;
import br.com.watch_better.model.SeasonData;
import br.com.watch_better.model.SerieData;
import br.com.watch_better.service.ApiConsumer;
import br.com.watch_better.service.dataConverter.ConversionService;

public class Principal implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }
    

    @Override
    public void run(String... args) throws Exception {
        ApiConsumer apiConsumer = new ApiConsumer();
        String json = apiConsumer.getData("http://www.omdbapi.com/?t=gilmore+girls&apikey=14c1e91b");
        ConversionService conversionService = new ConversionService();
        SerieData data = conversionService.dataFetcher(json, SerieData.class);
        System.out.println(data);

        json = apiConsumer.getData("http://www.omdbapi.com/?t=gilmore+girls&Season=1&Episode=1&apikey=14c1e91b");
        EpisodeData episodeData = conversionService.dataFetcher(json, EpisodeData.class);
        System.out.println(episodeData);

        List<SeasonData> seasons = new ArrayList<>();

        for(int i = 1; i <= data.totalSeasons(); i++) {
            json = apiConsumer.getData("http://www.omdbapi.com/?t=gilmore+girls&Season=" + i + "&apikey=14c1e91b");
            SeasonData seasonData = conversionService.dataFetcher(json, SeasonData.class);
            seasons.add(seasonData);
            System.out.println(seasonData);

        }

    }
}