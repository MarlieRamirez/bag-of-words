package url.ia;

import url.ia.service.FileService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Testing {
    public static void main(String[] args) {

        BagOfWords bow = BagOfWords.getInstance();
        FileService fs = new FileService();
        fs.readFile();

        Map<String, List<String>> bagOfWords = bow.map;

        FrequencyWord frequencyWord = new FrequencyWord();

        Map<String, Map<String, Integer>> frequencies = new  HashMap<>();
        for (Map.Entry<String,List<String>> words: bagOfWords.entrySet()){
            frequencies.put(words.getKey(), frequencyWord.createFrequencyTable(words.getValue()));
        }

//        for (Map.Entry<String, Map<String, Integer>> value: frequencies.entrySet()) {
//            System.out.println(value);
//        }

        Map<String, Integer> countWords = new HashMap<>();
        for (Map.Entry<String,List<String>> words: bagOfWords.entrySet()){
            countWords.put(words.getKey(), frequencyWord.countWord(words.getValue()));
        }

        Map<String, Map<String, Float>> sequenceProbability = new HashMap<>();
        for (Map.Entry<String, Integer> value: countWords.entrySet()) {
            if (frequencies.containsKey(value.getKey())) {
                sequenceProbability.put(value.getKey(),frequencyWord.transformSequenceProbability(frequencies.get(value.getKey()),value.getValue()));
            }

        }


        for (Map.Entry<String, Map<String, Float>> value: sequenceProbability.entrySet()) {
            System.out.println(value);
        }



        String phrase = "La comida es rica";

        List<String> tempList =  Arrays.asList(phrase);

        Map<String, Integer> frequencyPhrase = frequencyWord.createFrequencyTable(tempList);
        System.out.println("Tabla de frecuencia: \n" + frequencyPhrase + "\n");

        Map<String, Map<String, Float>> sequenceProbabilityPhrase = new HashMap<>();
        for (Map.Entry<String, Integer> map : countWords.entrySet()){
            sequenceProbabilityPhrase.put(map.getKey(), frequencyWord.transformSequenceProbability(frequencyPhrase, map.getValue()));
        }

        System.out.println("Secuencia de probabilidad:");
        for (Map.Entry<String, Map<String, Float>> value: sequenceProbabilityPhrase.entrySet()){
            System.out.println(value);
        }

        Probability probability = new Probability();

        int meh =  bagOfWords.get("meh").size();
        int positive =  bagOfWords.get("positivo").size();

        float pMeh = probability.probabilityOf(meh,bagOfWords);
        float pPositive = probability.probabilityOf(positive,bagOfWords);

        Float totalPhraseP = sequenceProbabilityPhrase.get("positivo").values().stream()
                .reduce(1.0f, (num1, num2) -> num1 * num2);

        Float totalPhraseM = sequenceProbabilityPhrase.get("meh").values().stream()
                .reduce(1.0f, (num1, num2) -> num1 * num2);

        Float inference =(totalPhraseP * pPositive) / (totalPhraseM);

        System.out.println("Probabilidad de positivo"+positive);
        System.out.println(inference);


















    }

}
