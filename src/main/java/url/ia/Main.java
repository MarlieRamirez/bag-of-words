package url.ia;
import url.ia.service.FileService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        BagOfWords bow = BagOfWords.getInstance();
        FileService fs = new FileService();
        FrequencyWord frequencyWord = new FrequencyWord();

        fs.readFile();
        String opc = "0";


        while (!opc.equals("3")){
            System.out.println("------- MENU -------");
            System.out.println("1. Agregar frase al BOW");
            System.out.println("2. Inferir una frase");
            System.out.println("3. Salir");
            System.out.print("Seleccione un número: ");
            opc = myObj.nextLine();

            if (opc.equals("1")){
                System.out.println("Ingresar frase en formato: frase | medición");
                String phrase = myObj.nextLine();
                int i = phrase.indexOf("|");
                bow.add(phrase.substring(0,i).toLowerCase(), phrase.substring(i+2).toLowerCase());

            }

            if (opc.equals("2")){
                System.out.print("Ingresar frase: ");
                String phrase = myObj.nextLine();

                frequencyWord.getSequence(phrase);
            }
        }
    }


}