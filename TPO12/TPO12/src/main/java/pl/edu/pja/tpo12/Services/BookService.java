package pl.edu.pja.tpo12.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo12.Models.Author;
import pl.edu.pja.tpo12.Models.Book;
import pl.edu.pja.tpo12.Models.DTO.BookDTO;
import pl.edu.pja.tpo12.Models.Genre;
import pl.edu.pja.tpo12.Models.Publisher;
import pl.edu.pja.tpo12.Repositories.AuthorRepository;
import pl.edu.pja.tpo12.Repositories.BookRepository;
import pl.edu.pja.tpo12.Repositories.GenreRepository;
import pl.edu.pja.tpo12.Repositories.PublisherRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final BookDtoMapper bookDtoMapper;

    public BookService(BookRepository bookRepository, BookDtoMapper bookDtoMapper, AuthorRepository authorRepository, PublisherRepository publisherRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
        this.bookDtoMapper = bookDtoMapper;
    }

    public Iterable<BookDTO> getBooks(){
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false )
                .map(bookDtoMapper::map)
                .toList();
    }

    public Optional<BookDTO> getBookByID(Long id) {
        return bookRepository.findById(id)
                .map(bookDtoMapper::map);
    }

    @Transactional
    public void addBook(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());

        Set<Author> authors = dto.getAuthor().lines()
                .map(String::trim)
                .filter(name -> !name.isBlank())
                .map(fullName -> {
                    String[] parts = fullName.trim().split("\\s+", 2);
                    String firstName = parts[0];
                    String lastName = parts.length > 1 ? parts[1] : "";
                    return authorRepository.findByFirstNameAndLastName(firstName, lastName)
                            .orElseGet(() -> {
                                Author a = new Author();
                                a.setFirstName(firstName);
                                a.setLastName(lastName);
                                return authorRepository.save(a);
                            });
                })
                .collect(Collectors.toSet());
        book.setAuthors(authors);

        if (dto.getPublisher() != null && !dto.getPublisher().isBlank()) {
            Publisher publisher = publisherRepository.findByName(dto.getPublisher())
                    .orElseGet(() -> {
                        Publisher p = new Publisher();
                        p.setName(dto.getPublisher());
                        return publisherRepository.save(p);
                    });
            book.setPublisher(publisher);
        }

        Set<Genre> genres = dto.getGenres().stream()
                .map(String::trim)
                .filter(name -> !name.isBlank())
                .map(name -> genreRepository.findByGenreName(name)
                        .orElseGet(() -> {
                            Genre g = new Genre();
                            g.setGenreName(name);
                            return genreRepository.save(g);
                        }))
                .collect(Collectors.toSet());
        book.setGenres(genres);

        bookRepository.save(book);
    }

    @Transactional
    public void editBook(Long id, BookDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());

        Set<Author> authors = dto.getAuthor().lines()
                .map(String::trim)
                .filter(name -> !name.isBlank())
                .map(fullName -> {
                    String[] parts = fullName.trim().split("\\s+", 2);
                    String firstName = parts[0];
                    String lastName = parts.length > 1 ? parts[1] : "";
                    return authorRepository.findByFirstNameAndLastName(firstName, lastName)
                            .orElseGet(() -> {
                                Author a = new Author();
                                a.setFirstName(firstName);
                                a.setLastName(lastName);
                                return authorRepository.save(a);
                            });
                })
                .collect(Collectors.toSet());
        book.setAuthors(authors);

        if (dto.getPublisher() != null && !dto.getPublisher().isBlank()) {
            Publisher publisher = publisherRepository.findByName(dto.getPublisher())
                    .orElseGet(() -> {
                        Publisher p = new Publisher();
                        p.setName(dto.getPublisher());
                        return publisherRepository.save(p);
                    });
            book.setPublisher(publisher);
        } else {
            book.setPublisher(null);
        }

        Set<Genre> genres = dto.getGenres().stream()
                .map(String::trim)
                .filter(name -> !name.isBlank())
                .map(name -> genreRepository.findByGenreName(name)
                        .orElseGet(() -> {
                            Genre g = new Genre();
                            g.setGenreName(name);
                            return genreRepository.save(g);
                        }))
                .collect(Collectors.toSet());
        book.setGenres(genres);

        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}