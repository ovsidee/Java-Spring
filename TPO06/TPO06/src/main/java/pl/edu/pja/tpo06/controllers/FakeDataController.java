package pl.edu.pja.tpo06.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo06.DTO.PersonDTO;
import pl.edu.pja.tpo06.services.FakeDataService;

import java.util.List;

@Controller
public class FakeDataController {
    public final FakeDataService fakeDataService;

    public FakeDataController(FakeDataService fakeDataService) {
        this.fakeDataService = fakeDataService;
    }

    @GetMapping("/genPersonalData")
    public String showForm() {
        return "genPersonalData";
    }

    @PostMapping("/genPersonalData")
    public String genPersonalData(@RequestParam Integer quantity,
                                  @RequestParam String lang,
                                  @RequestParam(required = false) List<String> fields,
                                  Model model) {
        if (quantity <= 0 ) {
            model.addAttribute("error", "Quantity must be greater than zero.");
            return "genPersonalData";
        } else {
            List<PersonDTO> fakeData = fakeDataService.generateData(quantity, lang, fields);
            model.addAttribute("fakeData", fakeData);
        }

        return "genPersonalData";
    }
}
