package pl.edu.pja.s31719tpo04blog.AppConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;
import java.util.Scanner;

@Configuration
public class AppConfig {
    @Bean
    public Scanner getScanner(){
        return new Scanner(System.in);
    }
}
