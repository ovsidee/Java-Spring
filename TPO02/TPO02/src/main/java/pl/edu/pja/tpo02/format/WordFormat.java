package pl.edu.pja.tpo02.format;

import pl.edu.pja.tpo02.Entry;

public interface WordFormat {
    String printFormattedWordFromEntry(Entry entry);
    String printFormattedWord(String string);
}
