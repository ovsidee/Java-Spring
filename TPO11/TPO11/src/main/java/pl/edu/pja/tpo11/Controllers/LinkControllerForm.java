package pl.edu.pja.tpo11.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.pja.tpo11.Model.DTO.LinkDTO;
import pl.edu.pja.tpo11.Model.DTO.LinkUpdateDTO;
import pl.edu.pja.tpo11.Model.Link;
import pl.edu.pja.tpo11.Services.LinkService;

import java.util.Optional;

@Controller
public class LinkControllerForm {
    private final LinkService linkService;

    public LinkControllerForm(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(new LinkDTO());
        return "index";
    }

    @PostMapping("/create")
    public String submitLink(@Valid @ModelAttribute("linkDTO") LinkDTO linkDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors())
            return "index";

        Link link = linkService.create(linkDTO);
        if (link == null)
            return "redirect:/error";


        model.addAttribute("link", link);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/links")
    public String listLinks(Model model) {
        model.addAttribute("links", linkService.findAll());
        return "links";
    }

    @GetMapping("/links/view")
    public String viewForm() {
        return "view-form";
    }

    @GetMapping("links/edit/{name}")
    public String showEditForm(@PathVariable String name, Model model) {
        Optional<Link> linkOptional = linkService.findByName(name);
        if (linkOptional.isEmpty()) {
            return "redirect:/links";
        }
        Link link = linkOptional.get();

        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setName(link.getName());
        linkDTO.setTargetUrl(link.getTargetUrl());
        linkDTO.setPassword(link.getPassword());

        model.addAttribute("linkDTO", linkDTO);
        model.addAttribute("originalName", name);

        return "edit";
    }

    @PostMapping("links/edit/{name}")
    public String updateLink(@PathVariable String name,
                             @Valid @ModelAttribute("linkDTO") LinkUpdateDTO linkDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("originalName", name);
            return "edit";
        }

        boolean updated = linkService.update(name, linkDTO);
        if (!updated) {
            model.addAttribute("error", "Update failed");
            return "edit";
        }

        return "redirect:/links";
    }

    @GetMapping("links/delete/{name}")
    public String deleteLink(@PathVariable String name,
                             @RequestParam(name = "pass", required = false) String password,
                             RedirectAttributes redirectAttributes) {
        Optional<Link> linkOptional = linkService.findByName(name);
        if (linkOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Link not found");
        }
        else {
            Link link = linkOptional.get();
            if (link.getPassword() != null && !link.getPassword().isBlank())
                if (password == null || !link.getPassword().equals(password)) {
                    redirectAttributes.addFlashAttribute("error", "Wrong password");
                }
                else {
                    linkService.delete(name);
                    redirectAttributes.addFlashAttribute("success", "Link deleted");
                }
                else {
                    linkService.delete(name);
                }
                return "redirect:/links";
        }
        return "redirect:/links";
    }
}