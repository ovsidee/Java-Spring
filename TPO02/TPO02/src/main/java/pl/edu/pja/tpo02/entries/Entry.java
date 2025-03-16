package pl.edu.pja.tpo02.entries;

import java.util.Objects;

public class Entry {
    String translationEnglish;
    String translationGerman;
    String translationPolish;

    public Entry(String translationEnglish, String translationGerman, String translationPolish) {
        this.translationEnglish = translationEnglish;
        this.translationGerman = translationGerman;
        this.translationPolish = translationPolish;
    }

    public String getTranslationGerman() {
        return translationGerman;
    }

    public String getTranslationPolish() {
        return translationPolish;
    }

    public String getTranslationEnglish() {
        return translationEnglish;
    }

    @Override
    public String toString() {
        return translationEnglish + " - " + translationGerman + ", " + translationPolish;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entry entry = (Entry) obj;
        return  translationEnglish.equalsIgnoreCase(entry.translationEnglish) &&
                translationGerman.equalsIgnoreCase(entry.translationGerman) &&
                translationPolish.equalsIgnoreCase(entry.translationPolish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translationEnglish.toLowerCase(), translationGerman.toLowerCase(), translationPolish.toLowerCase());
    }
}
