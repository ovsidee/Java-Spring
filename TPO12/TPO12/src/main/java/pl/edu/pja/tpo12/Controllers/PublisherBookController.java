package pl.edu.pja.tpo12.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo12.Models.DTO.BookDTO;
import pl.edu.pja.tpo12.Services.BookService;

@Controller
@RequestMapping("/publisher/books")
@PreAuthorize("hasRole('PUBLISHER')")
public class PublisherBookController {

    private final BookService bookService;

    public PublisherBookController(BookService books) {
        this.bookService = books;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "publisher-book-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "publisher-book-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute BookDTO dto) {
        bookService.addBook(dto);
        return "redirect:/publisher/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookByID(id).orElseThrow());
        return "publisher-book-edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute BookDTO dto) {
        bookService.editBook(id, dto);
        return "redirect:/publisher/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/publisher/books";
    }
}