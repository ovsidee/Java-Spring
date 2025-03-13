package pl.edu.pja.tpo02;

import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class FlashcardsController {
    public FileService fileService;
    public EntryRepository entryRepository;
    public WordFormat wordFormat;

    public FlashcardsController(FileService fileService, EntryRepository entryRepository, WordFormat wordFormat) {
        this.fileService = fileService;
        this.entryRepository = entryRepository;
        this.wordFormat = wordFormat;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        fileService.loadWords();
        while (true) {
            System.out.println(
                    "Type \"1\" to get the list of all flashcards.\n" +
                    "Type \"2\" to add a new flashcard.\n" +
                    "Type \"3\" to get a test.\n" +
                    "Type \"4\" to exit.\n"
            );
            int command = scanner.nextInt();
            switch (command) {
                case 1 -> {
                    for (Entry entry : entryRepository.getAllEntries()) {
                        System.out.println(entry);
                    }
                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {
                    System.out.println("Bye bye...");
                    System.exit(1);
                }
            }
        }
    }
}
