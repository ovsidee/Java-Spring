package pl.edu.pja.tpo11.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo11.Model.DTO.LinkDTO;
import pl.edu.pja.tpo11.Model.Link;
import pl.edu.pja.tpo11.Services.LinkService;


@Controller
@RequestMapping("/links")
public class LinkControllerForm {

    private final LinkService linkService;

    public LinkControllerForm(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping({"", "/"})
    public String home(Model model) {
        model.addAttribute("linkDTO", new LinkDTO());
        model.addAttribute("allLinks", linkService.findAll());
        return "link-form";
    }

    @PostMapping("/submit")
    public String createLink(@Valid @ModelAttribute("linkDTO") LinkDTO linkDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "link-form";
        }

        Link created = linkService.create(linkDTO);
        if (created == null) {
            model.addAttribute("error", "Could not create link.");
            model.addAttribute("allLinks", linkService.findAll());
            return "link-form";
        }
        if (created.getPassword() == null || created.getPassword().isBlank()) {
            linkDTO.setPassword(null);
        }

        model.addAttribute("success", "Link created successfully!");
        model.addAttribute("linkDTO", new LinkDTO());
        model.addAttribute("allLinks", linkService.findAll());
        return "link-form";
    }
}