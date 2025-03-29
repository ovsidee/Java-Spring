package pl.edu.pja.s31719tpo04books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.edu.pja.s31719tpo04books.repositories.SpringDataAuthorRepository;
import pl.edu.pja.s31719tpo04books.repositories.SpringDataBookRepository;
import pl.edu.pja.s31719tpo04books.repositories.SpringDataPublisherRepository;

@SpringBootApplication
public class S31719Tpo04BooksApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(S31719Tpo04BooksApplication.class, args);

        SpringDataAuthorRepository authorRepo = context.getBean(SpringDataAuthorRepository.class);
        SpringDataBookRepository bookRepo = context.getBean(SpringDataBookRepository.class);
        SpringDataPublisherRepository publisherRepo = context.getBean(SpringDataPublisherRepository.class);

        authorRepo.findAll().forEach(System.out::println);

        bookRepo.findAll().forEach(System.out::println);

        publisherRepo.findAll().forEach(System.out::println);
    }

}
