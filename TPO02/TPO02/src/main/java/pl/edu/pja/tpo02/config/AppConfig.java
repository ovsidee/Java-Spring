package pl.edu.pja.tpo02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pja.tpo02.entries.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public List<Entry> getEntries() {
        return new ArrayList<>();
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Bean
    public Scanner getScanner(){
        return new Scanner(System.in);
    }

}