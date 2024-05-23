package url.ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BagOfWords {
    Map<String, List<String>> map = new HashMap<String, List<String>>();

    //Datos base del PDF con 1 extra en cada
    public Map<String, List<String>> init(){
        map.put("meh", List.of("El sol nace para todos", "Todos tienen pulmones"));
        map.put("positivo", List.of("Hoy es un buen día", "Hoy tome un buen cafe"));
        map.put("negativo", List.of("Cuando despertó, el dinosaurio todavía estaba allí", "Y todavía falta despertar mañana"));
        map.put("irrelevante", List.of("Pienso, luego como doritos raptor", "Tengo hambre"));
        return map;
    }

    //Agregar con un key existente o uno nuevo
    public void add(String phrase, String key){
        if (map.containsKey(key)){
            map.put(key, Stream.concat(map.get(key).stream(), Stream.of(phrase)).collect(Collectors.toList()));

        }else{
            map.put(key,List.of(phrase));
            System.out.println("NEW: "+key + " - " + map.get(key));
        }
        System.out.println(map.toString());
    }


}
