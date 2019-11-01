package lab2.BigData;

import java.util.*;
import javafx.util.Pair;
public class Shuffler<T> {
    private  List<javafx.util.Pair<String, Pair<Integer,Integer>>> mapperOutput;
    private List<GroupByPair<T>> reducerInput;
    public Shuffler(){
        mapperOutput = new ArrayList<>();
        reducerInput = new ArrayList<>();
    }

    public void setMapperOutput( List<Pair<String, Pair<Integer,Integer>>> mapperOutput) {
        this.mapperOutput = mapperOutput;
    }
    public void addMapperOutput(Pair pair){
        this.mapperOutput.add(pair);
    }
    public List<GroupByPair<T>> shuffle(){
        if(!mapperOutput.isEmpty()) {
            HashMap<String,List<T>> maps = new HashMap<>();
            for(Pair pair: mapperOutput){
                String key = pair.getKey().toString();
                if(maps.containsKey(key)){
                    List<T> values = maps.get(key);
                    values.add((T)pair.getValue());
                    maps.put(key,values);
                }else{
                    maps.put(key,new ArrayList<T>() {{
                        add((T)pair.getValue());
                    }});
                }
            }
            if(maps.size() > 0){
                for (String key: maps.keySet()) {
                    reducerInput.add(new GroupByPair<>(key,maps.get(key)));
                }
                Collections.sort(reducerInput, Comparator.comparing(GroupByPair::getKey));
            }

        }

        Collections.sort(reducerInput, Comparator.comparing(GroupByPair::getKey));
        return reducerInput;
    }
    public  List<GroupByPair<T>> getReducerInput(){
        System.out.println("Reducer Input");
        for(GroupByPair item : reducerInput) {
            System.out.println("(" + item.getKey() + ", " + item.getValue() + ")");
        }
        System.out.println();
        return reducerInput;
    }
}
