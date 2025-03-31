package pl.edu.pja.s31719tpo04books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.edu.pja.s31719tpo04books.repositories.SpringDataAuthorRepository;
import pl.edu.pja.s31719tpo04books.repositories.SpringDataBookRepository;
import pl.edu.pja.s31719tpo04books.repositories.SpringDataPublisherRepository;
import pl.edu.pja.s31719tpo04books.tables.Author;
import pl.edu.pja.s31719tpo04books.tables.Book;
import pl.edu.pja.s31719tpo04books.tables.Publisher;

@SpringBootApplication
public class S31719Tpo04BooksApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(S31719Tpo04BooksApplication.class, args);

        SpringDataAuthorRepository authorRepository = context.getBean(SpringDataAuthorRepository.class);
        SpringDataBookRepository bookRepository = context.getBean(SpringDataBookRepository.class);
        SpringDataPublisherRepository publisherRepository = context.getBean(SpringDataPublisherRepository.class);

        System.out.println("\nfindAllAuthors()");
        for (Author a : authorRepository.findAllAuthors())
            System.out.println(a);

        System.out.println("\nfindAllBooks()");
        for (Book b : bookRepository.findAllBooks())
            System.out.println(b);

        System.out.println("\nfindAllPublishers()");
        for (Publisher p : publisherRepository.findAll())
            System.out.println(p);
    }
}