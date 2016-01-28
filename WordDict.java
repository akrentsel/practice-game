package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Alex on 1/28/16.
 */
public class WordDict {

    HashSet<String> words = new HashSet<>();

    public WordDict(String pathToFile) {
        try {
            Scanner scan = new Scanner(new File(pathToFile));
            while(scan.hasNext()) {
                String[] line = scan.nextLine().split(" ");
                for(String s : line) {
                    System.out.println(s);
                    words.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean contains(String s) {
        return words.contains(s);
    }
}
