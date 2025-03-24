package pl.edu.pja.tpo03.services;

import pl.edu.pja.tpo03.entries.Entry;

public interface WordFormat {
    String printFormattedWordFromEntry(Entry entry);
    String printFormattedWord(String string);
}

