package url.ia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyWord {

    public Map<String,Integer> createFrequencyTableV2(List<String> corpus,  Map<String, List<String>> bagOfWords){
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




}
