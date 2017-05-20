/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabblewordfinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class give the possibility to check if word can be found with a
 * combination of letters Useful for word games like scrabble. Uses a .txt file
 * containing all words (one on each line) to check if the combination "exists".
 *
 * @author Rian De Rous
 */
public class AnagramFinder {

    HashMap<String, ArrayList<ScrabbleWord>> map = new HashMap<>();

    /**
     * Creating an object wil load the dictionary in memory
     *
     * @param Path absolute path to the text file containing a list of words
     * (one on each line)
     * @throws IOException
     */
    public AnagramFinder(String Path) throws IOException {
        TextFileToArrayListConverter words = new TextFileToArrayListConverter(Path);
        for (String word : words) {
            addWord(word);

        }
    }

    /**
     * Creating an object will load the dictionary in memory. Uses a filedialog
     * to choose the dictionary.
     *
     * @param c Caps.UPPERCASE, CapsLOWERCASE or Caps.UNAFFECTED (converts words
     * to uppercase, lowercase or leave them unaffected)
     * @throws IOException
     */
    public AnagramFinder(Caps c) throws IOException {

        TextFileToArrayListConverter words = new TextFileToArrayListConverter(c);
        words.forEach((word) -> {
            addWord(word);
        });
    }

    /**
     * Creating an object will load the dictionary in memory.
     *
     * @param path absolute path to the dictionary file (one word on each line)
     * @param c Caps.UPPERCASE, CapsLOWERCASE or Caps.UNAFFECTED (converts words
     * to uppercase, lowercase or leave them unaffected)
     * @throws IOException
     */
    public AnagramFinder(String path, Caps c) throws IOException {

        TextFileToArrayListConverter words;
        words = new TextFileToArrayListConverter(path, c);
        words.forEach((word) -> {
            addWord(word);
        });
    }

    /**
     * (should) Generate all possible combinations from 0 out of n, to n out of
     * n. with the letters in a word. (n = word.length) The word "airport" can
     * be written with the letters 'aioprrt', but with only five characters the
     * word "prior" is possible too. Doesn't need to generate all permutations
     * because of the way this class works (with the sortstring method);
     * 0.5*n^2+1.5*n
     * @param word
     * @return an arraylist containing all possible permutations (from 0 out of
     * n, to n out of n);
     */
    private ArrayList<String> combinations(String word) {
        //not correct implemented yet. Should generate al combinations (order not important)!
         
        List<String> list = new ArrayList<>();
         System.out.println("generating combinations");
         int counter = 0;
       
         for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j + i <= word.length(); j++) {
                list.add(word.substring(i, i + j));
                list.add(word.substring(word.length()-i-j,word.length()-j));
               
                counter++;
            }
        }
        System.out.println("generated " + counter + "combinations");
        list.add(word);
        Set<String> hs = new HashSet<>();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);
        ArrayList<String> l = new ArrayList<>();
        
        l.addAll(list);
        Collections.sort(l);
        
          l.forEach((string) -> {
            System.out.println("" + string);
        });
        
        return l;
        

    }

    public ArrayList<ScrabbleWord> getWord(String key) {
        ArrayList<ScrabbleWord> list = new ArrayList<>();
        ArrayList<String> combinations = combinations(key);
        combinations.stream().map((combination) -> sortString(combination)).filter((sortedString) -> (map.containsKey(sortedString))).forEachOrdered((sortedString) -> {
            list.addAll(map.get(sortedString));
        });
        list.sort(new scrabbleWordComparer());
        return list;
    }

    /**
     * @param key
     * @param combination
     * @return
     */
    public ArrayList<ScrabbleWord> getWord(String key, boolean combination) {
        ArrayList<ScrabbleWord> list = new ArrayList<>();
        String sortedString = sortString(key);

        if (combination) {
            ArrayList<String> combinations = combinations(key);
            combinations.stream().filter((_item) -> (map.containsKey(sortedString))).forEachOrdered((_item) -> {
                list.addAll(map.get(sortedString));
            });
        } else {
            if (map.containsKey(sortedString)) {
                return map.get(sortedString);
            }
        }

        return list;
    }

    /**
     * sorts the letters in a word/string alphabetically example: "bridge"
     * becomes "bdegir"
     *
     * @param s word to be sorted
     * @return the letters of the word in al
     */
    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        // System.out.println("key=" + sorted);
        return sorted;
    }

    /**
     * Adds a word to the data structure
     *
     * @param word word to be added to the dictionary
     */
    private void addWord(String word) {
        String sortedString = sortString(word);
        ScrabbleWord scrabbleWord = new ScrabbleWord(word);
        if (map.containsKey(sortedString)) {
            map.get(sortedString).add(scrabbleWord);
        } else {
            ArrayList<ScrabbleWord> list = new ArrayList<>();
            list.add(scrabbleWord);
            map.put(sortedString, list);
        }
    }

}
