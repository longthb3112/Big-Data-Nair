package lab1.BigData;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {
        //creating File instance to reference text file in Java

        File text = new File(".\\TestData.txt");
        Pattern p = Pattern.compile("\\b[^\\d\\W_]+\\b");
        List<Pair> result = new ArrayList<>();
        try{
            //Creating Scanner instnace to read File in Java
            Scanner scnr = new Scanner(text);

            //Reading each line of file using Scanner class
            while(scnr.hasNextLine()){
                String line = scnr.nextLine();

                Matcher m1 = p.matcher(line);
                while (m1.find()) {
                    String key = m1.group().toLowerCase();
                    result.add(new Pair(key,1));
                }
            }
            Collections.sort(result, Comparator.comparing(o -> o.key));
            for(Pair item : result) {
                System.out.println("(" + item.getKey() + ", " + item.getValue() + ")");
            }
        }catch(Exception ex){
            throw new Error(ex.getMessage());
        }

    }

    static class Pair{
        public String getKey() {
            return key;
        }

        public Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        private String key;
        private int value;

    }
}

