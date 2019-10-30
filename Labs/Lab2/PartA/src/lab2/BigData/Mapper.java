package lab2.BigData;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mapper<T> {
    private List<T> mapperInput;
    private List<Pair> mapperOutPut;
    public Mapper(){
        mapperInput = new ArrayList<>();
        mapperOutPut = new ArrayList<>();
    }
    public List<Pair> map(){
        if(!mapperInput.isEmpty()){
            Pattern p = Pattern.compile("\\b[^\\d\\W_]+\\b");
            for (T item:mapperInput) {
                if(item instanceof String){
                    Matcher m1 = p.matcher((String)item);
                    while (m1.find()) {
                        String key = m1.group().toLowerCase();
                        mapperOutPut.add(new Pair(key,1));
                    }
                }
            }
            Collections.sort(mapperOutPut, Comparator.comparing(o -> o.getKey()));

        }
        return mapperOutPut;

    }
    public void addInput(T value){
        mapperInput.add(value);
    }
    public List<Pair> getOutPut(){
        System.out.println("Mapper Output");
        for(Pair item : mapperOutPut) {
            System.out.println("(" + item.getKey() + ", " + item.getValue() + ")");
        }
        return mapperOutPut;
    }
}
