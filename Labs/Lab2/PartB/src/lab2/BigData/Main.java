package lab2.BigData;

import java.io.File;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        //creating File instance to reference text file in Java
        String  filePath = ".\\TestData.txt";
        WordCount wordCount = new WordCount(4,2);
        wordCount.count(filePath);

    }
}
