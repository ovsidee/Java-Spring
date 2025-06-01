package pl.edu.pja.tpo12.Authentification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo12.Services.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService users;

    public RegistrationController(UserService users) { this.users = users; }

    public record ReaderForm(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @Email String email,
            @NotBlank String password) { }

    public record PublisherForm(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @Email String email,
            @NotBlank String password) { }

    public record LibrarianForm(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @Email String email,
            @NotBlank String password) { }

    @GetMapping("/librarian")
    public String librarianForm(Model m) {
        m.addAttribute("form", new LibrarianForm(null,null,null,null));
        return "register-librarian";
    }

    @PostMapping("/librarian")
    public String registerLibrarian(@Valid @ModelAttribute("form") LibrarianForm f,
                                    BindingResult br) {
        if (br.hasErrors()) return "register-librarian";

        users.registerLibrarian(f.email(), f.password(), f.firstName(), f.lastName());
        return "redirect:/";
    }


    @GetMapping("/reader")
    public String readerForm(Model m) {
        m.addAttribute("form", new ReaderForm(null,null,null,null));
        return "register-reader";
    }

    @PostMapping("/reader")
    public String registerReader(@Valid @ModelAttribute("form") ReaderForm f,
                          BindingResult br) {

        if (br.hasErrors()) return "register-reader";

        users.registerReader(f.email(), f.password(), f.firstName(), f.lastName());
        return "redirect:/";
    }

    @GetMapping("/publisher")
    public String publisherForm(Model m) {
        m.addAttribute("form", new PublisherForm(null,null,null,null));
        return "register-publisher";
    }

    @PostMapping("/publisher")
    public String registerPublisher(@Valid @ModelAttribute("form") PublisherForm f,
                             BindingResult br) {

        if (br.hasErrors()) return "register-publisher";

        users.registerPublisher(f.email(), f.password(), f.firstName(), f.lastName());
        return "redirect:/";
    }
}
