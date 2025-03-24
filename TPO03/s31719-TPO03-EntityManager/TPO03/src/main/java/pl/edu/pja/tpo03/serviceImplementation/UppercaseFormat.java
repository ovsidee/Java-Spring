package pl.edu.pja.tpo03.serviceImplementation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo03.entries.Entry;
import pl.edu.pja.tpo03.services.WordFormat;

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
