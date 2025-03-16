package pl.edu.pja.tpo02.serviceImplementation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo02.entries.Entry;
import pl.edu.pja.tpo02.services.WordFormat;

@Service
@Profile("original")
public class NormalFormat implements WordFormat {
    @Override
    public String printFormattedWord(String string) {
        return string;
    }

    @Override
    public String printFormattedWordFromEntry(Entry entry) {
        return entry.toString();
    }
}