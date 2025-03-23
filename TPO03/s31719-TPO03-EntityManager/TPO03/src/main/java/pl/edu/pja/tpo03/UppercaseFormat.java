package pl.edu.pja.tpo03;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("upperCase")
public class UppercaseFormat implements WordFormat {
    @Override
    public String printFormattedWord(String string) {
        return string.toUpperCase();
    }

    @Override
    public String printFormattedWordFromEntry(Entry entry) {
        return entry.toString().toUpperCase();
    }
}
