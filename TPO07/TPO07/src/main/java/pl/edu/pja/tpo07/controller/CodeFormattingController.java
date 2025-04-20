package pl.edu.pja.tpo07.controller;

import com.google.googlejavaformat.java.FormatterException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.pja.tpo07.model.CodeToFormat;
import pl.edu.pja.tpo07.service.CodeFormattingService;
import pl.edu.pja.tpo07.service.SerializeCodeService;

@Controller
public class CodeFormattingController {

    public final CodeFormattingService codeFormattingService;
    public final SerializeCodeService serializeCodeService;

    public CodeFormattingController(CodeFormattingService codeFormattingService, SerializeCodeService serializeCodeService) {
        this.codeFormattingService = codeFormattingService;
        this.serializeCodeService = serializeCodeService;
    }

    @GetMapping("/codeFormatForm")
    public String codeFormat() {
        return "codeFormatForm";
    }

    @PostMapping("/submit")
    public String formatted(
            @RequestParam("id") String id,
            @RequestParam("code") String codeToBeFormatted,
            @RequestParam("duration") Long duration,
            Model model) {
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
            codeToFormat.setFormattedCode(formattedCode);
            serializeCodeService.saveCode(codeToFormat);
        } catch (FormatterException e) {
            model.addAttribute("error", "Formatting failed: " + e.getMessage());
            return "error";
        }
        model.addAttribute("codeToFormat", codeToFormat);
        return "codeFormatForm";
    }

    @GetMapping("/load")
    public String loadCode(@RequestParam("idToLoadCode") String id,
                           Model model) {
        try {
            CodeToFormat loaded = serializeCodeService.loadCode(id);
            if (loaded == null) {
                model.addAttribute("error", "Code with ID '" + id + "' not found.");
                return "error";
            }
            model.addAttribute("codeToFormat", loaded);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "codeFormatForm";
    }
}

