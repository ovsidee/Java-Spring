package pl.edu.pja.tpo12.Authentification;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo12.Authentification.Forms.LibrarianForm;
import pl.edu.pja.tpo12.Authentification.Forms.PublisherForm;
import pl.edu.pja.tpo12.Authentification.Forms.ReaderForm;
import pl.edu.pja.tpo12.Services.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService users;

    public RegistrationController(UserService users) {
        this.users = users;
    }

    @GetMapping("/librarian")
    public String librarianForm(Model model) {
        model.addAttribute("form", new LibrarianForm());
        return "register-librarian";
    }

    @PostMapping("/librarian")
    public String registerLibrarian(@Valid @ModelAttribute("form") LibrarianForm form,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register-librarian";

        users.registerLibrarian(form.getEmail(), form.getPassword(), form.getFirstName(), form.getLastName());
        return "redirect:/";
    }

    @GetMapping("/reader")
    public String readerForm(Model model) {
        model.addAttribute("form", new ReaderForm());
        return "register-reader";
    }

    @PostMapping("/reader")
    public String registerReader(@Valid @ModelAttribute("form") ReaderForm form,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register-reader";

        users.registerReader(form.getEmail(), form.getPassword(), form.getFirstName(), form.getLastName());
        return "redirect:/";
    }

    @GetMapping("/publisher")
    public String publisherForm(Model model) {
        model.addAttribute("form", new PublisherForm());
        return "register-publisher";
    }

    @PostMapping("/publisher")
    public String registerPublisher(@Valid @ModelAttribute("form") PublisherForm form,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register-publisher";

        users.registerPublisher(form.getEmail(), form.getPassword(), form.getFirstName(), form.getLastName());
        return "redirect:/";
    }
}
