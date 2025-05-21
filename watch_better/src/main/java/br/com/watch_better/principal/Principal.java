package br.com.watch_better.principal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class Principal implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }
    

    @Override
    public void run(String... args) throws Exception {
        Menu menu = new Menu();
        menu.showMenu();
  
    }


}