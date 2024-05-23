package url.ia.service;

import url.ia.BagOfWords;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    public String readFile() {
        String route = "src/main/java/url/ia/files/text_file.txt";
        Path path = Paths.get(route);
        String complete_route = path.toAbsolutePath().toString();
        StringBuilder sb = new StringBuilder();
        String sentece = "";
        File file = new File(complete_route);
        BagOfWords bow = BagOfWords.getInstance();


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String row;

            while ((row = reader.readLine()) != null) {
                sentece = row;
                int i = row.indexOf("|");
                bow.add(row.substring(0,i).toLowerCase(), row.substring(i+2).toLowerCase());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);


        }

        return sentece;
    }
}
