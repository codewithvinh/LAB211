/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0008;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author PC
 */
public class J1SP0008 {

    /**
     * @param args the command line arguments
     */
    private static String input() {
        Scanner sr = new Scanner(System.in);

        String inputString = sr.nextLine().trim();
        return inputString;
    }
    
    public static String cleanCode(String str){
        String cleanString = str.replaceAll("[^\\w']+", " ");
        
        return cleanString;
    }
    private static void countWord(String inputString) {
        String cleanString = inputString.replaceAll("[^\\w']+", " "); 
        StringTokenizer words = new StringTokenizer(cleanCode(inputString));
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
        while (words.hasMoreElements()) {
            String word = words.nextToken();
            if (wordMap.containsKey(word)) {
                wordMap.replace(word, wordMap.get(word) + 1);
                continue;
            }
            wordMap.put(word, 1);
        }
        System.out.println(wordMap);
    }

    private static void countLetter(String inputString) {
        String cleanString = inputString.replaceAll("[^\\w']+", " "); 
        HashMap<String, Integer> wordLetter = new HashMap<String, Integer>();
        //String letters = cleanCode(inputString);
        //System.out.println(letters);
        
        for (String word : letters) {
            if (word != " ") {
                if (wordLetter.containsKey(word)) {
                    wordLetter.replace(word, wordLetter.get(word) + 1);
                    continue;
                }
                wordLetter.put(word, 1);
            }
        }
        
        System.out.println(wordLetter);
    }

    public static void main(String[] args) {
        String inputString = input();
        countWord(inputString);
        countLetter(inputString);
    }
}
