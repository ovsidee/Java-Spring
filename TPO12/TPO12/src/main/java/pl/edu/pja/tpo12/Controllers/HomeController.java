package pl.edu.pja.tpo12.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.pja.tpo12.Services.BookService;

@Controller
public class HomeController {

    private final BookService books;

    public HomeController(BookService books) {
        this.books = books;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", books.getBooks());
        return "index";
    }
}
