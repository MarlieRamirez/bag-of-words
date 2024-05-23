package url.ia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BagOfWords {
    private static BagOfWords instance;
    Map<String, List<String>> map = new HashMap<>();

    private BagOfWords(){}

    public static BagOfWords getInstance() {
        if (instance == null) {
            instance = new BagOfWords();
        }
        return instance;
    }

    //Agregar con un key existente o uno nuevo
    public void add(String phrase, String key){
        if (map.containsKey(key)){
            map.put(key, Stream.concat(map.get(key).stream(), Stream.of(phrase)).collect(Collectors.toList()));
        }else{
            map.put(key,List.of(phrase));
            //System.out.println("NEW: "+key + " - " + map.get(key));
        }
        //System.out.println(map.toString());
    }


}
