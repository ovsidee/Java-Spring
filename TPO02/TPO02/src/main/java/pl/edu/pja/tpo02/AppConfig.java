package pl.edu.pja.tpo02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

}
