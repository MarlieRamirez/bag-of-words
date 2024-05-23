package url.ia;

import url.ia.service.FileService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Testing {
    public static void main(String[] args) {


        FileService service = new FileService();

        System.out.println(service.readFile());

        String subs = service.readFile();

        String[] arrays = subs.split("\\|");

        for (String sentece: arrays){
            System.out.println(sentece.trim());
        }




    }


}
