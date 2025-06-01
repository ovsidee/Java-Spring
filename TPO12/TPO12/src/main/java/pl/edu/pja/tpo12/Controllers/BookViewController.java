package pl.edu.pja.tpo12.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.pja.tpo12.Services.BookService;

@Controller
public class BookViewController {

    private final BookService books;

    public BookViewController(BookService books) {
        this.books = books;
    }

    @GetMapping("/books/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("book",
                books.getBookByID(id).orElseThrow(() -> new IllegalArgumentException("Book not found")));
        return "book";
    }
}
