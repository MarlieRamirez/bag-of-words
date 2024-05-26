package url.ia;

import url.ia.service.FileService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workshop {

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

        Probability probability = new Probability();

        // La frase a analizar
        String phrase = "A veces las nubes son de color azul";

        Map<String, Map<String, Integer>> frequencies = new  HashMap<>();
        for (Map.Entry<String,List<String>> words: bagOfWords.entrySet()){
            frequencies.put(words.getKey(), frequencyWord.createFrequencyTable(words.getValue()));
        }

        Map<String, Map<String, Integer>> frequenciesPhrase = new HashMap<>(frequencyWord.createFrequencyTable(phrase, frequencies));

        Map<String, Integer> countWords = new HashMap<>();
        for (Map.Entry<String,List<String>> words: bagOfWords.entrySet()){
            countWords.put(words.getKey(), frequencyWord.countWord(words.getValue()));
        }

        Map<String,Integer> frequenciesPhraseValues = new HashMap<>();
        for (var frequency: frequenciesPhrase.values()) {
            frequenciesPhraseValues.putAll(frequency);
        }

        Map<String, Map<String, Float>> sequenceProbabilityPhrase = new HashMap<>();
        for (Map.Entry<String, Integer> map : countWords.entrySet()){
            sequenceProbabilityPhrase.put(map.getKey(), frequencyWord.transformSequenceProbability(frequenciesPhraseValues, map.getValue()));
        }

        Map<String, Float> totalInference = new HashMap<>();
        for (var sequence : sequenceProbabilityPhrase.entrySet()) {
            int sizes = bagOfWords.get(sequence.getKey()).size();
            float probabilities = probability.probabilityOf(sizes,bagOfWords);
            Float totalProbabilityPhrase = sequenceProbabilityPhrase.get(sequence.getKey()).values().stream()
                    .reduce(1.0f, (num1, num2) -> num1 * num2);

            Float inference = totalProbabilityPhrase * probabilities;

           totalInference.put(sequence.getKey(), inference) ;
        }

        System.out.println("\n");
        for (var inference: totalInference.entrySet()) {
            System.out.println(inference);
        }







    }
}
