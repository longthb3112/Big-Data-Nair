package lab2.BigData;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.util.Pair;

public class Mapper<T> {
    private List<T> mapperInput;
    private List<Pair<String,Pair<Integer,Integer>>> mapperOutPut;
    private HashMap<String, Pair<Integer,Integer>> combineMap;
    public Mapper(){
        mapperInput = new ArrayList<>();
        mapperOutPut = new ArrayList<>();
        combineMap = new HashMap<>();
    }
    public List<Pair<String,Pair<Integer,Integer>>> map(){
        if(!mapperInput.isEmpty()){
            Pattern p = Pattern.compile("\\b[^\\d\\W_]+\\b");
            for (T item:mapperInput) {
                if(item instanceof String){
                    Matcher m1 = p.matcher((String)item);
                    while (m1.find()) {
                        String key = String.valueOf(m1.group().toLowerCase().charAt(0)) ;
                        int length =  m1.group().toLowerCase().length();

                        if(combineMap.get(key) != null && combineMap.get(key) instanceof Pair ){
                            Pair value = combineMap.get(key);
                            combineMap.put(key, new Pair<Integer, Integer>((int)value.getKey() + length,(int)value.getValue() + 1));
                        }else{
                            combineMap.put(key,new Pair<Integer, Integer>(length,1));
                        }

                    }
                }
            }
            if(combineMap.size() > 0){
                for(String key:combineMap.keySet()){
                    mapperOutPut.add(new Pair(key,combineMap.get(key)));
                }
            }
            Collections.sort(mapperOutPut, Comparator.comparing(o -> o.getKey()));

        }
        return mapperOutPut;

    }
    public void addInput(T value){
        mapperInput.add(value);
    }
    public  List<Pair<String,Pair<Integer,Integer>>> getOutPut(){
        System.out.println("Mapper Output");
        for(Pair item : mapperOutPut) {
            System.out.println("(" + item.getKey() + ", " + item.getValue() + ")");
        }
        System.out.println();
        return mapperOutPut;
    }
}
