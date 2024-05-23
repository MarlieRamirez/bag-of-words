package url.ia.service;

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

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String row;
            while ((row = reader.readLine()) != null) {
                data = row.split("\\|");
                dataMap.put( Arrays.asList(data[0].trim()), data[1]);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);


        }

        return dataMap;
    }
}
