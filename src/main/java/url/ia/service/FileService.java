package url.ia.service;

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

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String row;

            while ((row = reader.readLine()) != null) {
                sentece = row;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);


        }

        return sentece;
    }
}
