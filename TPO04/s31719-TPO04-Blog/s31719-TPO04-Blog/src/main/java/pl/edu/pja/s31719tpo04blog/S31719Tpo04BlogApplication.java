package pl.edu.pja.s31719tpo04blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.edu.pja.s31719tpo04blog.controller.AppController;

@SpringBootApplication
public class S31719Tpo04BlogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(S31719Tpo04BlogApplication.class, args);

        AppController controller = context.getBean(AppController.class);
        controller.runContoller();
    }

}
