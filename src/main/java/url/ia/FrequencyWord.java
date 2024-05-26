package url.ia;

import java.util.*;

public class FrequencyWord {
    BagOfWords bow = BagOfWords.getInstance();
    Probability probability = new Probability();

    public Map<String, Map<String, Integer>> createFrequencyTable(String corpus,  Map<String, Map<String, Integer>> frequencies){
        Map<String, Map<String, Integer>> corpusFrequencies = new  HashMap<>();
        for (Map.Entry<String,Map<String, Integer>> item : frequencies.entrySet()){
            Map<String,Integer> frequencyPerCorpus = new HashMap<>();
            String[] tokens = corpus.split(" ");
            for (String token : tokens) {
                if (item.getValue().containsKey(token)) {
                    frequencyPerCorpus.put(token, item.getValue().values().stream().findFirst().get() + 1);
                    corpusFrequencies.put(item.getKey(),frequencyPerCorpus);
                } else {
                    frequencyPerCorpus.put(token, 1);
                    corpusFrequencies.put(item.getKey(),frequencyPerCorpus);
                }
            }
        }

        return  corpusFrequencies;
    }

    public Map<String,Integer> createFrequencyTable(List<String> corpus){
        Map<String,Integer> frequencies = new HashMap<>();
        for (String sentence: corpus){
            String[] tokens = sentence.split(" ");
            for (String token : tokens) {
                if (!(frequencies.containsKey(token))) {
                    frequencies.put(token, 1);
                } else {
                    int currentValue = frequencies.getOrDefault(token, 0);
                    frequencies.put(token, currentValue + 1);
                }
            }
        }

        return frequencies;
    }
    public int countWord(List<String> corpus) {
       int frequency = 0;
        for (String sentence: corpus) {
            frequency += sentence.split(" ").length;
        }
        return frequency;
    }

    public Map<String,Float> transformSequenceProbability(Map<String,Integer> frequency ,int totalWords){
        Map<String,Float> cpt = new HashMap<>();
        for (Map.Entry<String,Integer> item :frequency.entrySet()){
            float probability = (float) item.getValue() / totalWords;
            cpt.put(item.getKey(), probability);
        }
        return cpt;
    }

    public Map<String, Map<String, Integer>> getFrequencies(String phrase){
        // frecuencia general
        Map<String, Map<String, Integer>> frequencies = new  HashMap<>();

        //Crea tabla general
        for (Map.Entry<String,List<String>> words: bow.map.entrySet()){
            frequencies.put(words.getKey(), createFrequencyTable(words.getValue()));
        }

        //Crea tabla tomando en cuenta frase
        Map<String, Map<String, Integer>> frequenciesPhrase = new HashMap<>(createFrequencyTable(phrase, frequencies));

        return  frequenciesPhrase;
    }

    public void getSequence(String phrase){
        Map<String,Integer> frequenciesPhraseValues = new HashMap<>();
        Map<String, Integer> countWords = new HashMap<>();

        for (Map.Entry<String,List<String>> words: bow.map.entrySet()){
            countWords.put(words.getKey(), countWord(words.getValue()));
        }

        // Manipular tipos de datos
        for (var frequency: getFrequencies(phrase.toLowerCase()).values()) {
            frequenciesPhraseValues.putAll(frequency);
        }
        System.out.println(frequenciesPhraseValues);
        // Crear secuencia de probabilidades
        Map<String, Map<String, Float>> sequenceProbabilityPhrase = new HashMap<>();
        for (Map.Entry<String, Integer> map : countWords.entrySet()){
            sequenceProbabilityPhrase.put(map.getKey(), transformSequenceProbability(frequenciesPhraseValues, map.getValue()));
        }

        //Imprimir secuencia
        System.out.println("Secuencia de probabilidad:");
        for (Map.Entry<String, Map<String, Float>> value: sequenceProbabilityPhrase.entrySet()){
            System.out.println(value);
        }

        makeInference(sequenceProbabilityPhrase);
    }

    private void makeInference(Map<String, Map<String, Float>> sequenceProbabilityPhrase) {
        Map<String, Float> totalInference = new HashMap<>();
        for (var sequence : sequenceProbabilityPhrase.entrySet()) {
            int sizes = bow.map.get(sequence.getKey()).size();
            float probabilities = probability.probabilityOf(sizes,bow.map);
            Float totalProbabilityPhrase = sequenceProbabilityPhrase.get(sequence.getKey()).values().stream()
                    .reduce(1.0f, (num1, num2) -> num1 * num2);

            Float inference = totalProbabilityPhrase * probabilities;


            totalInference.put(sequence.getKey(),inference) ;
        }

        System.out.println("\n");
        for (var inference: totalInference.entrySet()) {
            System.out.println(inference);
        }
    }

}
