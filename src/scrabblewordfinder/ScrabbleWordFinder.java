/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabblewordfinder;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Rian De Rous
 */
public class ScrabbleWordFinder {

    AnagramFinder af;

    ScrabbleWordFinder(Caps caps) throws IOException {
        af = new AnagramFinder(caps);
    }

    public ArrayList<ScrabbleWord> getAnagrams(String text) {
        long now = System.currentTimeMillis();
        System.out.println("searching possible combinations");
        ArrayList<ScrabbleWord> list = af.getWord(text);
        System.out.println(list.size() + " word(s) found in " + (System.currentTimeMillis() - now) + " ms");
        return list;
    }
    
    
    
    
}
