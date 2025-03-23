package pl.edu.pja.tpo03;

import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.Scanner;

@Controller
public class FlashcardsController {
    public EntryRepositoryService entryRepositoryService;
    public WordFormat wordFormat;
    public Random random;
    public Scanner scanner;

    public FlashcardsController(EntryRepositoryService entryRepositoryService, WordFormat wordFormat, Random random, Scanner scanner) {
        this.entryRepositoryService = entryRepositoryService;
        this.wordFormat = wordFormat;
        this.random = random;
        this.scanner = scanner;
    }

    public void printAllEntries(){
        for (Entry entry : entryRepositoryService.getAllEntries()) {
            System.out.println(
                    "ID: \"" + entry.getID() + "\"," +
                            " English: \"" + wordFormat.printFormattedWord(entry.getTranslationEnglish()) + "\"," +
                            " German: \"" + wordFormat.printFormattedWord(entry.getTranslationGerman()) + "\"," +
                            " Polish: \"" + wordFormat.printFormattedWord(entry.getTranslationPolish()) + "\""
            );
        }
    }

    public void userAddEntry(){
        System.out.println("Write the word in English");
        String wordInEnglish = scanner.next();

        System.out.println("Write the word in German");
        String wordInGerman = scanner.next();

        System.out.println("Write the word in Polish");
        String wordInPolish = scanner.next();

        Entry newEntry = new Entry(wordInEnglish, wordInGerman, wordInPolish);
        if (entryRepositoryService.getAllEntries().contains(newEntry)) {
            System.out.println("This flashcard already exists");
        } else {
            entryRepositoryService.addEntry(newEntry);
//          dataBaseService.saveWord(newEntry);
            System.out.println("Added a new flashcard: " + wordFormat.printFormattedWordFromEntry(newEntry));
        }
    }

    public void run() {
        while (true) {
            System.out.println (
                      "\nType \"1\" to get the list of all flashcards.\n" +
                      "Type \"2\" to add a new flashcard.\n" +
                      "Type \"3\" to get a test.\n" +
                      "Type \"4\" to remove flashcard.\n" +
                      "Type \"5\" to exit."
            );
            int command = scanner.nextInt();
            switch (command) {
                case 1 ->  printAllEntries();
                case 2 ->  userAddEntry();
                case 3 -> {
                    System.out.println("Random word test:");
                    Entry randomEntry = entryRepositoryService.getRandomEntry();

                    int randomNumber = random.nextInt(3) + 1;
                    switch (randomNumber) {
                        // 1 - English (user must enter German + Polish)
                        case 1 -> userWriteGermanPolishWords(randomEntry);
                        // 2 - German (user must enter English + Polish)
                        case 2 -> userWriteEnglishPolishWords(randomEntry);
                        // 3 - Polish (user must enter English + German)
                        case 3 -> userWriteEnglishGermanWords(randomEntry);
                    }
                }
                case 4 -> {
                    printAllEntries();
                    System.out.println("Write an ID of the word you want to remove: ");
                    Long idToRemove = scanner.nextLong();
                    entryRepositoryService.deleteById(idToRemove);
                } case 5 -> {
                    System.out.println("Bye bye...");
                    System.exit(1);
                }
            }
        }
    }

    public void userWriteEnglishGermanWords(Entry randomEntry) {
        System.out.println("Translate this word to English: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationPolish()) + "\"(polish)");
        String wordInEnglish = scanner.next();

        if (wordInEnglish.equalsIgnoreCase(randomEntry.getTranslationEnglish())) {
            System.out.println("Correct! Now write it in German:");
        } else {
            System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationEnglish()) + "\"" + ". Now write it in German:");
        }

        String wordInGerman = scanner.next();
        if (wordInGerman.equalsIgnoreCase(randomEntry.getTranslationGerman())) {
            System.out.println("Great job! All Correct!");
        } else {
            System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationGerman()) + "\"");
        }
    }

    public void userWriteGermanPolishWords(Entry randomEntry) {
        System.out.println("Translate this word to German: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationEnglish()) + "\"(english)");
        String wordInGerman = scanner.next();

        if (wordInGerman.equalsIgnoreCase(randomEntry.getTranslationGerman())) {
            System.out.println("Correct! Now write it in Polish:");
        } else {
            System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationGerman()) + "\"" + ". Now write it in Polish:");
        }

        String wordInPolish = scanner.next();
        if (wordInPolish.equalsIgnoreCase(randomEntry.getTranslationPolish())) {
            System.out.println("Great job! All Correct!");
        } else {
            System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationPolish()) + "\"");
        }
    }

    public void userWriteEnglishPolishWords(Entry randomEntry) {
        System.out.println("Translate this word to English: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationGerman()) + "\"(german)");
        String wordInEnglish = scanner.next();

        if (wordInEnglish.equalsIgnoreCase(randomEntry.getTranslationEnglish())) {
            System.out.println("Correct! Now write it in Polish:");
        } else {
            System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationEnglish()) + "\"" + ". Now write it in Polish:");
        }

        String wordInPolish = scanner.next();
        if (wordInPolish.equalsIgnoreCase(randomEntry.getTranslationPolish())) {
            System.out.println("Great job! All Correct!");
        } else {
            System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationPolish()) + "\"");
        }
    }
}