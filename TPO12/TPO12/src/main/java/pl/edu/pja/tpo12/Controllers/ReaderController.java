package pl.edu.pja.tpo12.Controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo12.Services.BookService;

@Controller
@RequestMapping("/reader")
@PreAuthorize("hasRole('READER')")
public class ReaderController {

    private final BookService bookService;

    public ReaderController(BookService books){
        this.bookService = books;
    }

    @GetMapping("/search")
    public String searchForm() {
        return "reader-search";
    }

    @GetMapping("/search/result")
    public String searchBooks(@RequestParam String query, Model model) {
        var books = bookService.getBooks();
        var results = StreamSupport.stream(books.spliterator(), false)
                .filter(b -> b.getTitle() != null && b.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        model.addAttribute("results", results);
        return "reader-search-result";
    }

    @GetMapping("/genres")
    public String viewGenres(Model model) {
        var books = bookService.getBooks();
        var genres = StreamSupport.stream(books.spliterator(), false)
                .flatMap(b -> b.getGenres().stream())
                .distinct()
                .sorted()
                .toList();

        model.addAttribute("genres", genres);
        return "reader-genre-list";
    }

    @GetMapping("/genres/{genre}")
    public String filterByGenre(@PathVariable String genre, Model model) {
        var books = bookService.getBooks();
        var filtered = StreamSupport.stream(books.spliterator(), false)
                .filter(b -> b.getGenres().contains(genre))
                .toList();

        model.addAttribute("genre", genre);
        model.addAttribute("books", filtered);
        return "reader-genre-result";
    }

    @GetMapping("/new")
    public String viewNewestBooks(Model model) {
        var books = bookService.getBooks();
        var recentBooks = StreamSupport.stream(books.spliterator(), false)
                .sorted((b1, b2) -> b2.getPublicationYear().compareTo(b1.getPublicationYear()))
                .limit(5)
                .toList();

        model.addAttribute("books", recentBooks);
        return "reader-new-books";
    }
}