package lab2.BigData;

import java.util.*;

public class Reducer<T extends Number> {
    private List<GroupByPair<T>> reducerInput;
    private List<Pair> reducerOutput;

    public Reducer(){
        reducerInput = new ArrayList<>();
        reducerOutput = new ArrayList<>();
    }
    public void setReduceInput(List<GroupByPair<T>> reduceInput){
     this.reducerInput = reduceInput;
    }
    public List<Pair> reduce(){
        if(!reducerInput.isEmpty()) {

            if(reducerInput.size() > 0){
                for (GroupByPair<T> group: reducerInput){
                    reducerOutput.add(new Pair(group.getKey(),group.getValue().stream().mapToInt(Number::intValue).sum()));
                }
            }
        }

         Collections.sort(reducerOutput, Comparator.comparing(Pair::getKey));
        return reducerOutput;
    }

    public List<Pair> getReduceOutput(){
        System.out.println("Reducer Output");
        for(Pair item : reducerOutput) {
            System.out.println("(" + item.getKey() + ", " + item.getValue() + ")");
        }
        return reducerOutput;
    }
}
