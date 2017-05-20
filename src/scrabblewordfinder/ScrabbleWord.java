/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabblewordfinder;

/**
 * In scrabble game, each word has a score related to it based on the letters 
 * Each letter has a score beteen 1 and 10 
 * @author Rian De Rous
 */
public class ScrabbleWord {

    private final String word;
    private final int score;

    private static final int[] SCORETABLE = {1, 4, 4, 2, 1, 4, 3, 3, 1, 10, 5, 2, 4, 2, 1, 4, 10, 1, 1, 1, 2, 5, 4, 8, 3, 10};

    public ScrabbleWord(String word) {
        this.word = word;
        score = scrabbleScore(word);
    }

    public String getWord() {
        return word;
    }

    public int getScore() {
        return score;
    }

   
    public static int scrabbleScore(String word) {
        word = word.toLowerCase();
        int tempScore = 0;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (letter >= 'a' && letter <= 'z') {
                tempScore += SCORETABLE[letter-97];
            }
        }
        return tempScore;
    }
}
