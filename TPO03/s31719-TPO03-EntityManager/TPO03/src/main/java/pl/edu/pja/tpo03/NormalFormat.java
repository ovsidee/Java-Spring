package pl.edu.pja.tpo03;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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
