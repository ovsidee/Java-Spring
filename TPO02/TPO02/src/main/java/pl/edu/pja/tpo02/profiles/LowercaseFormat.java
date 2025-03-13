package pl.edu.pja.tpo02.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo02.Entry;
import pl.edu.pja.tpo02.WordFormat;

@Service
@Profile("lowerCase")
public class LowercaseFormat implements WordFormat {
    @Override
    public String printFormattedWord(Entry entry) {
        return entry.toString().toLowerCase();
    }
}