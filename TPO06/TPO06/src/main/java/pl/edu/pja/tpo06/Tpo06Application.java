package pl.edu.pja.tpo06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Tpo06Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Tpo06Application.class, args);
    }
}
