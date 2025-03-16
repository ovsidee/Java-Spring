package pl.edu.pja.tpo02.services;

import pl.edu.pja.tpo02.entries.Entry;

public interface WordFormat {
    String printFormattedWordFromEntry(Entry entry);
    String printFormattedWord(String string);
}
