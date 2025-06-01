package pl.edu.pja.tpo12.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo12.Services.BookService;
import pl.edu.pja.tpo12.Services.UserService;

@Controller
@RequestMapping("/librarian")
@PreAuthorize("hasRole('LIBRARIAN')")
public class LibrarianController {

    private final BookService bookService;
    private final UserService userService;

    public LibrarianController(BookService bookService, UserService userService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "librarian-users";
    }

    @GetMapping("/books")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "librarian-books";
    }

    @GetMapping("/book-count")
    public String viewBookCount(Model model) {
        model.addAttribute("count", bookService.getBooks().spliterator().getExactSizeIfKnown());
        return "librarian-book-count";
    }

}