package lab2.BigData;

import java.util.*;
import javafx.util.Pair;
public class Reducer<T> {
    private List<GroupByPair<T>> reducerInput;
    private List<Pair> reducerOutput;
    private Shuffler<T> shuffler;
    public Reducer(){
        reducerInput = new ArrayList<>();
        reducerOutput = new ArrayList<>();
        shuffler = new Shuffler<>();
    }
    public void setReduceInput(List<GroupByPair<T>> reduceInput){
     this.reducerInput = reduceInput;
    }
    public void addDataToShuffle(javafx.util.Pair pair){
        this.shuffler.addMapperOutput(pair);
    }
    public List<Pair> reduce(){
        shuffler.shuffle();
        this.reducerInput = shuffler.getReducerInput();

        if(!reducerInput.isEmpty()) {

            if(reducerInput.size() > 0){
                for (GroupByPair<T> group: reducerInput){
                    int sum = 0;
                    int count = 0;
                    for(T pair:group.getValue()){
                        Pair<Integer,Integer> item = (Pair)pair;
                        sum+= item.getKey();
                        count+=item.getValue();
                    }
                    reducerOutput.add(new Pair(group.getKey(), ((double)sum/(double)count)));
                }
            }
        }

        Collections.sort(reducerOutput, Comparator.comparing(o -> o.getKey().toString()));
        return reducerOutput;
    }

    public List<Pair> getReduceOutput(){
        System.out.println("Reducer Output");
        for(Pair item : reducerOutput) {
            System.out.println("(" + item.getKey() + ", " + item.getValue() + ")");
        }
        System.out.println();
        return reducerOutput;
    }
}
