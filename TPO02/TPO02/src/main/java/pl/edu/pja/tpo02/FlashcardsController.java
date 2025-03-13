package pl.edu.pja.tpo02;

import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.Scanner;

@Controller
public class FlashcardsController {
    public FileService fileService;
    public EntryRepository entryRepository;
    public WordFormat wordFormat;
    public Random random;

    public FlashcardsController(FileService fileService, EntryRepository entryRepository, WordFormat wordFormat, Random random) {
        this.fileService = fileService;
        this.entryRepository = entryRepository;
        this.wordFormat = wordFormat;
        this.random = random;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        fileService.loadWords();
        while (true) {
            System.out.println (
                    "Type \"1\" to get the list of all flashcards.\n" +
                    "Type \"2\" to add a new flashcard.\n" +
                    "Type \"3\" to get a test.\n" +
                    "Type \"4\" to exit."
            );
            int command = scanner.nextInt();
            switch (command) {
                case 1 -> {
                    for (Entry entry : entryRepository.getAllEntries()) {
                        //TODO - normal formatting
                        System.out.println(wordFormat.printFormattedWordFromEntry(entry));
                    }
                }
                case 2 -> {
                    System.out.println("Write the word in English");
                    String wordInEnglish = scanner.next();

                    System.out.println("Write the word in German");
                    String wordInGerman = scanner.next();

                    System.out.println("Write the word in Polish");
                    String wordInPolish = scanner.next();

                    Entry newEntry = new Entry(wordInEnglish, wordInGerman, wordInPolish);
                    if (entryRepository.getAllEntries().contains(newEntry)) {
                        System.out.println("This flashcard already exists");
                    } else {
                        entryRepository.addEntry(newEntry);
                        fileService.saveWordToFile(newEntry);
                        System.out.println("Added a new flashcard: " + wordFormat.printFormattedWordFromEntry(newEntry));
                    }
                }
                case 3 -> {
                    System.out.println("Random word test:");

                    // Get a random word only once
                    Entry randomEntry = entryRepository.getRandomEntry();

                    int randomNumber = random.nextInt(3) + 1;
                    switch (randomNumber) {
                        // 1 - English (user must enter German + Polish)
                        case 1 -> {
                            System.out.println("Translate this word to German: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationEnglish()) + "\"(english)");
                            String wordInGerman = scanner.next();

                            if (wordInGerman.equalsIgnoreCase(randomEntry.getTranslationGerman())) {
                                System.out.println("Correct! Now write it in Polish:");
                            } else {
                                System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationGerman()) + "\"");
                            }

                            String wordInPolish = scanner.next();
                            if (wordInPolish.equalsIgnoreCase(randomEntry.getTranslationPolish())) {
                                System.out.println("Great job! All Correct!");
                            } else {
                                System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationPolish()) + "\"");
                            }
                        }

                        // 2 - German (user must enter English + Polish)
                        case 2 -> {
                            System.out.println("Translate this word to English: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationGerman()) + "\"(german)");
                            String wordInEnglish = scanner.next();

                            if (wordInEnglish.equalsIgnoreCase(randomEntry.getTranslationEnglish())) {
                                System.out.println("Correct! Now write it in Polish:");
                            } else {
                                System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationEnglish()) + "\"");
                            }

                            String wordInPolish = scanner.next();
                            if (wordInPolish.equalsIgnoreCase(randomEntry.getTranslationPolish())) {
                                System.out.println("Great job! All Correct!");
                            } else {
                                System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationPolish()) + "\"");
                            }
                        }

                        // 3 - Polish (user must enter English + German)
                        case 3 -> {
                            System.out.println("Translate this word to English: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationPolish()) + "\"(polish)");
                            String wordInEnglish = scanner.next();

                            if (wordInEnglish.equalsIgnoreCase(randomEntry.getTranslationEnglish())) {
                                System.out.println("Correct! Now write it in German:");
                            } else {
                                System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationEnglish()) + "\"");
                            }

                            String wordInGerman = scanner.next();
                            if (wordInGerman.equalsIgnoreCase(randomEntry.getTranslationGerman())) {
                                System.out.println("Great job! All Correct!");
                            } else {
                                System.out.println("Incorrect! The correct answer was: \"" + wordFormat.printFormattedWord(randomEntry.getTranslationGerman()) + "\"");
                            }
                        }
                    }
                }

                case 4 -> {
                    System.out.println("Bye bye...");
                    System.exit(1);
                }
            }
        }
    }
}
