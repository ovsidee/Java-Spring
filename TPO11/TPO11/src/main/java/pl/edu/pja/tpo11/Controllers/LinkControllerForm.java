package pl.edu.pja.tpo11.Controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pja.tpo11.Model.DTO.LinkDTO;
import pl.edu.pja.tpo11.Model.DTO.LinkUpdateDTO;
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
        return "linkCreateForm";
    }

    @GetMapping("/view")
    public String viewForm() {
        return "linkViewForm";
    }

    @PostMapping("/submit")
    public String createLink(@Valid @ModelAttribute("linkDTO") LinkDTO linkDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "linkCreateForm";
        }

        Link created = linkService.create(linkDTO);
        if (created == null) {
            model.addAttribute("error", "could not create link.");
            return "linkCreateForm";
        }
        if (created.getPassword() == null || created.getPassword().isBlank()) {
            linkDTO.setPassword(null);
        }

        model.addAttribute("success", "Link created successfully!");
        model.addAttribute("linkDTO", new LinkDTO());
        return "linkCreateForm";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        return linkService.findById(id)
                .map(link -> {
                    LinkUpdateDTO updateDTO = new LinkUpdateDTO();
                    updateDTO.setName(link.getName());
                    updateDTO.setTargetUrl(link.getTargetUrl());
                    updateDTO.setPassword("");
                    model.addAttribute("updateDTO", updateDTO);
                    model.addAttribute("linkId", id);
                    return "linkEditForm";
                })
                .orElse("redirect:/links?error=notfound");
    }

    @PostMapping("/edit/{id}")
    public String updateLink(@PathVariable String id,
                             @Valid @ModelAttribute("updateDTO") LinkUpdateDTO updateDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("linkId", id);
            return "linkEditForm";
        }

        try {
            boolean updated = linkService.update(id, updateDTO);
            if (!updated) return "redirect:/links?error=notfound";
            return "redirect:/links?success=updated";
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                model.addAttribute("error", "wrong password");
                model.addAttribute("linkId", id);
                return "linkEditForm";
            }
            return "redirect:/links?error=unexpected";
        }
    }

    @PostMapping("/view")
    public String viewLink(@RequestParam String name,
                           @RequestParam(required = false) String password,
                           Model model) {
        return linkService.findByName(name)
                .map(link -> {
                    String realPassword = link.getPassword();
                    boolean protectedLink = realPassword != null && !realPassword.equalsIgnoreCase("null") && !realPassword.isBlank();

                    if (protectedLink && (password == null || !realPassword.equals(password))) {
                        model.addAttribute("error", "Wrong password");
                        return "linkViewForm";
                    }

                    model.addAttribute("link", link);
                    model.addAttribute("enteredPassword", password);
                    return "linkDetails";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Link not found");
                    return "linkViewForm";
                });
    }


    @PostMapping("/delete/{id}")
    public String deleteLink(@PathVariable String id,
                             @RequestParam(required = false) String password,
                             Model model) {
        return linkService.findById(id)
                .map(link -> {
                    String realPassword = link.getPassword();
                    boolean protectedLink = realPassword != null && !realPassword.equals("null") && !realPassword.isBlank();
                    if (protectedLink && (password == null || !realPassword.equals(password))) {
                        model.addAttribute("error", "Wrong password");
                        return "redirect:/links/view";
                    }

                    linkService.delete(id);
                    return "redirect:/links?success=deleted";
                })
                .orElse("redirect:/links?error=notfound");
    }

}