package br.com.watch_better.principal;

import java.util.Scanner;

import br.com.watch_better.service.ApiConsumer;

public class Menu {
    private ApiConsumer apiConsumer = new ApiConsumer();
    private Scanner scanner = new Scanner(System.in);
    private static final String ENDPOINT = "http://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=14c1e91b";



    public void showMenu() {
        System.out.println("Welcome to Watch Better!");
        System.out.println("What do you want to see?");
        String nameSerie = scanner.nextLine();
        String json = apiConsumer.getData(ENDPOINT + nameSerie.replace("", "+") + API_KEY);   
        System.out.println(json); 
    }
    
}
