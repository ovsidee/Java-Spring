package pl.edu.pja.tpo07.service;
import pl.edu.pja.tpo07.model.CodeToFormat;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;

@Service
public class SerializeCodeService {
    public String directoryToStoreCode = "allCodes";
    public File directoryToStoreCodes;

    public SerializeCodeService() {
        directoryToStoreCodes = new File(directoryToStoreCode);
        if (!directoryToStoreCodes.exists()) {
            directoryToStoreCodes.mkdir();
        }
    }

    public void saveCode(CodeToFormat codeToFormat) {
        File file = new File(directoryToStoreCode + "/" + codeToFormat.getId() + ".ser");

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(codeToFormat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.setLastModified(System.currentTimeMillis());
    }

    public CodeToFormat loadCode(String id)  {
        File file = new File(directoryToStoreCode + "/" + id + ".ser");
        if (!file.exists()) return null;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (CodeToFormat) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        new Thread(() -> {
            try {
                while (true) {
                    File[] serializedFiles = directoryToStoreCodes.listFiles((dir, name) -> name.endsWith(".ser"));
                    if (serializedFiles != null) {
                        for (File file : serializedFiles) {
                            boolean shouldDelete = false;
                            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                                CodeToFormat code = (CodeToFormat) objectInputStream.readObject();

                                long expirationTimeToDeleteFile = code.getExpirationTime() * 1000;
                                long fileAgeTime = System.currentTimeMillis() - file.lastModified();

                                if (fileAgeTime > expirationTimeToDeleteFile) shouldDelete = true;
                            } catch (IOException | ClassNotFoundException e) {
                                System.out.println("Failed to read file: " + file.getName() + " " + e.getMessage());
                            }
                            if (shouldDelete) Files.deleteIfExists(file.toPath());
                        }
                    }
                    else System.out.println("there is no files in the folder");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | IOException e) {
               throw new RuntimeException(e);
            }
        }).start();
    }
}