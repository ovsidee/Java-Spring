package pl.edu.pja.tpo12.Services;

import org.springframework.stereotype.Service;
import pl.edu.pja.tpo12.Models.Book;
import pl.edu.pja.tpo12.Models.DTO.BookDTO;
import pl.edu.pja.tpo12.Models.Genre;

import java.util.stream.Collectors;

@Service
public class BookDtoMapper {
    public BookDTO map(Book book) {
        if (book == null)
            return null;
        BookDTO dto = new BookDTO();
        dto.setBookID(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationYear(book.getPublicationYear());
        String authors = book.getAuthors()
                .stream()
                .map(a -> a.getFirstName() + " " + a.getLastName())
                .collect(Collectors.joining(", "));
        dto.setAuthor(authors);
        if (book.getPublisher() != null) {
            dto.setPublisher(
                    String.join(", ",
                            book.getPublisher().getName(),
                            book.getPublisher().getAddress(),
                            book.getPublisher().getCountry())
            );
        }
        dto.setGenres(
                book.getGenres()
                        .stream()
                        .map(Genre::getGenreName)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}