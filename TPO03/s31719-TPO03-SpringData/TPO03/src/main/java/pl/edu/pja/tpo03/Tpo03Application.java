package pl.edu.pja.tpo03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Tpo03Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Tpo03Application.class, args);

//        FlashcardsController flashcardsController = context.getBean(FlashcardsController.class);
//        flashcardsController.run();
        SpringDataEntryRepository entryRepository = context.getBean(SpringDataEntryRepository.class);

        Entry entry1 = new Entry("asd", "zxc", "cvb");
        Entry entry2 = new Entry("asd", "zxc", "cvb");

        entryRepository.save(entry1);
        entryRepository.save(entry2);

        System.out.println(entryRepository.findById(1L));
    }

}