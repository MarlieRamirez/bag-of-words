package url.ia;

import url.ia.service.FileService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class result {
    public static void main(String[] args) {

        // Instancia de la clase que agrega las palabras a su etiqueta correspondiente
        BagOfWords bow = BagOfWords.getInstance();
        //Instancia de la clase que permite la lectura del archivo correspondiente
        FileService fs = new FileService();
        // Leer el archivo y obtener las etiquetas y oraciones
        fs.readFile();

        // Almacenamos lo devuelto de la lectura del archivo
        Map<String, List<String>> bagOfWords = bow.map;

        /* Instancia de la clase que tiene el método de contar todas las palabras por
        *  categoria, la creación de la tabla de frecuencia y la secuencia de probabilidad */
        FrequencyWord frequencyWord = new FrequencyWord();

        // La frase a analizar
        String phrase = "La comida es rica";

        // transforma la frase a una lista
        List<String> tempList =  Arrays.asList(phrase);

        // creamos la tabla de frecuencia de la frase
        Map<String, Integer> frequencyPhrase = frequencyWord.createFrequencyTable(tempList);
        System.out.println("Tabla de frecuencia: \n" + frequencyPhrase + "\n");

        // Obtenemos la cantidad total de palabra por etiqueta
        Map<String, Integer> countWords = new HashMap<>();
        for (Map.Entry<String,List<String>> words: bagOfWords.entrySet()){
            countWords.put(words.getKey(), frequencyWord.countWord(words.getValue()));
        }

        // Creamos la secuencia de probabilidad de la frase para cada etiqueta
        Map<String, Map<String, Float>> sequenceProbabilityPhrase = new HashMap<>();
        for (Map.Entry<String, Integer> map : countWords.entrySet()){
            sequenceProbabilityPhrase.put(map.getKey(), frequencyWord.transformSequenceProbability(frequencyPhrase, map.getValue()));
        }

        System.out.println("Secuencia de probabilidad:");
        for (Map.Entry<String, Map<String, Float>> value: sequenceProbabilityPhrase.entrySet()){
            System.out.println(value);
        }



    }
}
