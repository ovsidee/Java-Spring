package pl.edu.pja.tpo02;

public class Entry {
    String word;
    String translationOne;
    String translationTwo;

    public Entry(String word, String translationOne, String translationTwo) {
        this.word = word;
        this.translationOne = translationOne;
        this.translationTwo = translationTwo;
    }

    public String getTranslationOne() {
        return translationOne;
    }

    public String getTranslationTwo() {
        return translationTwo;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word + " - " + translationOne + ", " + translationTwo;
    }
}
