package pl.edu.pja.tpo07.controller;

import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.pja.tpo07.model.CodeToFormat;
import pl.edu.pja.tpo07.service.CodeFormattingService;

@Controller
public class CodeFormattingController {

    public final CodeFormattingService codeFormattingService;

    public CodeFormattingController(CodeFormattingService codeFormattingService) {
        this.codeFormattingService = codeFormattingService;
    }

    @GetMapping("/codeFormatForm")
    public String codeFormat() {
        return "codeFormatForm";
    }

    @PostMapping("/submit")
    public String formatted(
            @RequestParam("id") Long id,
            @RequestParam("code") String codeToBeFormatted,
            @RequestParam("duration") Long duration,
            Model model) {
        System.out.println("ID: " + id);
        System.out.println("Code: " + codeToBeFormatted);
        System.out.println("Duration: " + duration);



        if (duration < 10 || duration > 7776000) {
            model.addAttribute("error", "Duration must be between 10 second and 90 days.");
            return "error";
        }
        CodeToFormat codeToFormat = new CodeToFormat();
        codeToFormat.setId(id);
        codeToFormat.setBeforeFormatCode(codeToBeFormatted);
        codeToFormat.setExpirationTime(duration);
        String formattedCode;
        try {
            formattedCode = codeFormattingService.formatCode(codeToBeFormatted);
        } catch (FormatterException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        codeToFormat.setFormattedCode(formattedCode);
        model.addAttribute("codeToFormat", codeToFormat);
        return "formatted";
    }
}

