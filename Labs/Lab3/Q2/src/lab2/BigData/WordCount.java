package lab2.BigData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCount {
    private int reducerNum;
    private int mapperNum;
    private List<Mapper> mappers;
    private List<Reducer> reducers;
    public WordCount(int reducerNum,int mapperNum){
        this.reducerNum = reducerNum;
        this.mapperNum = mapperNum;
        initialize();
    }
    private void initialize(){
        // mappers
        if(mappers == null) mappers = new ArrayList<>();
        for(int i = 0;i<this.mapperNum;i++){
            mappers.add(new Mapper());
        }
        // reducers
        if(reducers == null) reducers = new ArrayList<>();
        for(int j=0;j<this.reducerNum;j++){
            reducers.add(new Reducer());
        }
    }
    public void count(String pathFile){
        File text = new File(pathFile);

        try{

            addInputToMappers(text);

            //Map
            for(int j=0;j<this.mapperNum;j++){
                Mapper mapper = this.mappers.get(j);
                mapper.map();
                System.out.println("Mapper index: " + j);
                List<javafx.util.Pair> output = mapper.getOutPut();
                for(javafx.util.Pair pair:output){
                    int partition = getPartition(pair.getKey().toString());
                    this.reducers.get(partition).addDataToShuffle(pair);
                }
            }

            //Reduce
            for(int i =0;i<this.reducerNum; i++){
                Reducer reducer = reducers.get(i);
                reducer.reduce();
                System.out.println("Reducer index " + i);
                reducer.getReduceOutput();
            }
        }catch(Exception ex) {
            throw new Error(ex.getMessage());
        }
    }

    private void addInputToMappers(File text) throws FileNotFoundException {
        Scanner scnr = new Scanner(text);

        int mapperCount = 0;
        //Reading each line of file using Scanner class
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            if(mapperCount > this.mapperNum - 1){
                mapperCount = 0;
            }
            //Add input to mapper
            mappers.get(mapperCount).addInput(line);
            mapperCount++;
        }
    }

    public int getPartition(String key){
        return Math.abs((int)key.hashCode() % this.reducerNum) ;
    }

}
