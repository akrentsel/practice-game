package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private static HashSet<String> words = new HashSet<>();
    private static String startWord;
    private static String currentWord;
    private static String endWord;
    private static int stepCount = 0;

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("four_words.txt"));
            while (scan.hasNext()) {
                String[] line = scan.nextLine().split(" ");
                for (String s : line) {
                    words.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter the first word:");
        startWord = reader.nextLine();
        System.out.println("Please enter the second word:");
        endWord = reader.nextLine();

        System.out.println("Both words contained: " + (words.contains(startWord) && words.contains(endWord)));

        currentWord = startWord;

        naiveImplementation();

    }

    // All replacing
    public static void naiveImplementation() {
        for (int i = 0; i < endWord.length(); i++) {
            if (endWord.charAt(i) != currentWord.charAt(i)) {
                char charWanted = endWord.charAt(i);
                int index = i;
                String checkingString = createWord(currentWord, charWanted, index);
                if (words.contains(checkingString)) {    //We're golden!
                    stepCount += 1;
                    System.out.println("Step " + stepCount + ": Swapped " + currentWord + " for " + checkingString);
                    currentWord = checkingString;
                } else {
                    //lets see if we have that character later in the string
                    int indexOfNeededCharLater = currentWord.substring(i).indexOf(String.valueOf(charWanted));
                    int indexOfNeededCharEverywhere = currentWord.indexOf(String.valueOf(charWanted));
                    if (indexOfNeededCharLater != -1) {
                        stepCount += 1;
                        System.out.print("Step " + stepCount + ": Swapped " + currentWord + " for ");
                        swap(indexOfNeededCharLater, i);
                        System.out.println(currentWord);
                    } else if (indexOfNeededCharEverywhere != -1) {
                        stepCount += 1;
                        System.out.print("Step " + stepCount + ": Swapped " + currentWord + " for ");
                        swap(indexOfNeededCharLater, i);
                        System.out.println(currentWord);
                        break;
                    } else {
                        //do nothing
                        for(int k = index; k < endWord.length(); k++) {
                            if(words.contains(createWord(currentWord, charWanted, k))) {
                                stepCount += 1;
                                System.out.print("Step " + stepCount + ": Swapped" + currentWord + "for ");
                                currentWord = createWord(currentWord, charWanted, k);
                                System.out.println(currentWord);

                                stepCount += 1;
                                System.out.print("Step " + stepCount + ": Swapped" + currentWord + "for ");
                                swap(k,i);
                                System.out.println(currentWord);
                                break;
                            }
                        }
                    }
                }
            }


        }

        System.out.println("Done!");
    }

    public static void swap(int index1, int index2) {
        char char1 = currentWord.charAt(index1);
        char char2 = currentWord.charAt(index2);
        currentWord = currentWord.substring(0, index1) + String.valueOf(char1) + currentWord.substring(index1 + 1, index2) + String.valueOf(char2) + currentWord.substring(index2 + 1);

    }

    public static String createWord(String s, char k, int index) {
        return s.substring(0, index) + String.valueOf(k) + s.substring(index + 1);
    }
}
