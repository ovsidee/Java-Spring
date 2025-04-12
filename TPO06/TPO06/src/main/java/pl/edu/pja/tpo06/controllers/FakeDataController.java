package pl.edu.pja.tpo06.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.pja.tpo06.DTO.PersonDTO;
import pl.edu.pja.tpo06.services.FakeDataService;

import java.util.List;

@Controller
public class FakeDataController {
    public FakeDataService fakeDataService;

    public FakeDataController(FakeDataService fakeDataService) {
        this.fakeDataService = fakeDataService;
    }

    @RequestMapping("/genPersonalData")
    public String genPersonalData(@RequestParam(defaultValue = "0") Integer quantity,
                                  @RequestParam(defaultValue = "eng") String lang,
                                  @RequestParam(required = false) List<String> fields,
                                  Model model) {
        if (quantity != null || lang != null) {
            List<PersonDTO> fakeData = fakeDataService.generateData(quantity, lang, fields);
            model.addAttribute("fakeData", fakeData);
        } else {
            model.addAttribute("error", "You need to specify quantity or language.");
        }

        return "genPersonalData";
    }

}
