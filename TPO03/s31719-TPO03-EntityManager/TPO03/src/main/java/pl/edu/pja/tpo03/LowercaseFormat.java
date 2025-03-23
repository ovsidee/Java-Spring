package pl.edu.pja.tpo03;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("lowerCase")
public class LowercaseFormat implements WordFormat {
    @Override
    public String printFormattedWord(String string) {
        return string.toLowerCase();
    }

    @Override
    public String printFormattedWordFromEntry(Entry entry) {
        return entry.toString().toLowerCase();
    }
}