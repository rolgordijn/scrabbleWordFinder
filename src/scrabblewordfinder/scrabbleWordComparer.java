/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabblewordfinder;

import java.util.Comparator;

/**
 * Class implementing Comparator interface to sort ScrabbleWord based on the score related to that word
 * @author Rian De Rous
 */
public class scrabbleWordComparer implements Comparator<ScrabbleWord> {

    
    @Override
    public int compare(ScrabbleWord s1, ScrabbleWord s2) {
        int score1 = s1.getScore();
        int score2 = s2.getScore();
        if (score1 > score2) {
            return -1;
        } else {
            if (score1 < score2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
