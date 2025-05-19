package br.com.watch_better;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import br.com.watch_better.model.SerieData;
import br.com.watch_better.service.ConsumoApi;
import br.com.watch_better.service.dataConverter.ConversionService;

public class Principal implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }
    

    @Override
    public void run(String... args) throws Exception {
        ConsumoApi consumoApi = new ConsumoApi();
        String json = consumoApi.getData("http://www.omdbapi.com/?t=gilmore+girls&apikey=14c1e91b");
        ConversionService conversionService = new ConversionService();
        SerieData data = conversionService.dataFetcher(json, SerieData.class);
        System.out.println(data);
        
    }
}