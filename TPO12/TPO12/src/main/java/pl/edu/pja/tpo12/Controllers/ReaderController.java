package pl.edu.pja.tpo12.Controllers;

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

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "reader-catalogue";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookByID(id).orElseThrow());
        return "reader-book-details";
    }

    @GetMapping("/contact-publisher")
    public String contactForm() {
        return "reader-contact-publisher";
    }
}