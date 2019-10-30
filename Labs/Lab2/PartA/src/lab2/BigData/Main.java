package lab2.BigData;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //creating File instance to reference text file in Java
        String  filePath = ".\\TestData.txt";
        File text = new File(filePath);

        try{
            //Initialize Mapper
            Mapper<String> mapper = new Mapper<>();

            Scanner scnr = new Scanner(text);

            //Reading each line of file using Scanner class
            while(scnr.hasNextLine()){
                String line = scnr.nextLine();
                //Add input to mapper
                mapper.addInput(line);

            }
            //Do mapping
            mapper.map();

            //Shuffle
            Shuffler shuffler = new Shuffler();
            shuffler.setMapperOutput(mapper.getOutPut());
            shuffler.shuffle();
            System.out.println();

            //Print output
            Reducer<Integer> reducer = new Reducer<>();
            reducer.setReduceInput(shuffler.getReducerInput());
            reducer.reduce();

            System.out.println();
            reducer.getReduceOutput();
        }catch(Exception ex) {
            throw new Error(ex.getMessage());
        }

    }
}
