package url.ia;

import url.ia.service.FileService;

import java.util.List;
import java.util.Map;

public class Testing {
    public static void main(String[] args) {


        FileService service = new FileService();
        FrequencyWord frequencyWord = new FrequencyWord();


        for (Map.Entry<List<String>, String> item : service.readFile().entrySet()){
            frequencyWord.createFrequencyTable(item.getKey());
            System.out.println(item.getKey().get(0) + " "+ item.getValue());
        }



        BagOfWords bagOfWords = new BagOfWords();


        frequencyWord.countWord(bagOfWords.init().get("meh"));

        Probability probability = new Probability();

        int meh =  bagOfWords.init().get("meh").size();
        int positive =  bagOfWords.init().get("positivo").size();
        int negative =  bagOfWords.init().get("negativo").size();
        int irrelevant =  bagOfWords.init().get("irrelevante").size();

        float pMeh = probability.probabilityOf(meh, bagOfWords.init());
        float pPositive = probability.probabilityOf(positive, bagOfWords.init());
        float pNegative = probability.probabilityOf(negative, bagOfWords.init());
        float pIrrelevant = probability.probabilityOf(irrelevant, bagOfWords.init());

        System.out.println(pMeh);
    }


}
