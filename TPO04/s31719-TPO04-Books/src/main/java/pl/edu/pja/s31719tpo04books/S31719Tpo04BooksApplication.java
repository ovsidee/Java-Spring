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

        SpringDataAuthorRepository authorRepository = context.getBean(SpringDataAuthorRepository.class);
        SpringDataBookRepository bookRepository = context.getBean(SpringDataBookRepository.class);
        SpringDataPublisherRepository publisherRepository = context.getBean(SpringDataPublisherRepository.class);

        authorRepository.findAll().forEach(System.out::println);

        bookRepository.findAll().forEach(System.out::println);

        publisherRepository.findAll().forEach(System.out::println);
    }

}
