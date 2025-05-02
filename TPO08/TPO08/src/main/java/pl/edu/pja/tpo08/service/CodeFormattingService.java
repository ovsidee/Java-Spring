package pl.edu.pja.tpo08.service;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Service;

@Service
public class CodeFormattingService {
    public String formatCode(String code) throws FormatterException {
        Formatter formatter = new Formatter();
        return formatter.formatSource(code);
    }
}
