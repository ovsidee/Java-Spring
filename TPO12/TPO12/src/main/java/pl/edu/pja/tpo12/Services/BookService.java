package pl.edu.pja.tpo12.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo12.Models.Book;
import pl.edu.pja.tpo12.Models.DTO.BookDTO;
import pl.edu.pja.tpo12.Repositories.BookRepository;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;

    public BookService(BookRepository bookRepository, BookDtoMapper bookDtoMapper) {
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
        // Set author, publisher, genres if needed
        bookRepository.save(book);
    }

    @Transactional
    public void editBook(Long id, BookDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        // Update author, publisher, genres if needed
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
