package url.ia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.regex.Pattern;

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
        //Eliminar caracteres especiales, solo espacios, letras y sus acentos
        phrase = phrase.replaceAll("[^a-záéíóúüñ ]", "");

        if (map.containsKey(key)){
            map.put(key, Stream.concat(map.get(key).stream(), Stream.of(phrase)).collect(Collectors.toList()));

        }else{
            map.put(key,List.of(phrase));
        }
    }


}
