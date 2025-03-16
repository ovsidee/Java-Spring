package pl.edu.pja.tpo02.format;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo02.Entry;

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