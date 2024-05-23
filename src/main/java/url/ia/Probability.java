package url.ia;

import java.util.List;
import java.util.Map;

public class Probability {

    public float probabilityOf(int corpus, Map<String, List<String>> otherCorpus){

        int totalSentences = 0;
        float total = 0;

        for (List<String> oCorpus : otherCorpus.values()){
            totalSentences += oCorpus.size();
        }

        total = (float) corpus / totalSentences;
        return total;
    }
}
