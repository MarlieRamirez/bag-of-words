package url.ia.service;

import url.ia.BagOfWords;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileService {

    private final String ROUTE = "src/main/java/url/ia/files/text_file.txt";

    public  Map<List<String>, String> readFile() {

        Path path = Paths.get(ROUTE);
        String complete_route = path.toAbsolutePath().toString();
        Map<List<String>, String> dataMap = new HashMap<>();
        String[] data = new String[0];
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

        return dataMap;
    }
}
