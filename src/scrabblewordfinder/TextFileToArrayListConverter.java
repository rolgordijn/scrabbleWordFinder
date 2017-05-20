/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabblewordfinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rian De Rous
 */
public class TextFileToArrayListConverter extends ArrayList<String> {

    private String path;

    public TextFileToArrayListConverter(String path) throws IOException {
        System.out.println(path);
        this.path = path;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String word;

        long time = System.currentTimeMillis();
        while ((word = br.readLine()) != null) {
            this.add(word);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("parsed file in " + time + " ms");
    }

    /**
     *
     * @param path: textfile containing dictionarry (one word per line);
     * @param c: uppercase: all uppercase letters, lowercase: converts all to
     * lowercase, unnaffected: doesn't affect
     * @throws IOException
     */
    public TextFileToArrayListConverter(String path, Caps c) throws IOException {
        System.out.println(path);
        this.path = path;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String word;
        long time = System.currentTimeMillis();
        addWords(c,br);
        time = System.currentTimeMillis() - time;
        System.out.println("parsed file in " + time + " ms");
    }
    
    
    private void addWords(Caps c, BufferedReader br) throws IOException{
        String word;
        while ((word = br.readLine()) != null) {
            switch (c) {
                case UPPERCASE:
                    this.add(word.toUpperCase());
                    break;
                case LOWERCASE:
                    this.add(word.toLowerCase());
                    break;
                case UNAFFECTED:
                default:
                    this.add(word);
                    break;
            }
        }
    }


    public TextFileToArrayListConverter(Caps c) throws IOException {
        JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fc.setFileFilter(filter);
        fc.setCurrentDirectory(new File(System.getProperty("java.class.path")));

        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           this.path = fc.getSelectedFile().toString();
            System.out.println(path);
        }

        BufferedReader br = new BufferedReader(new FileReader(path));
        String word;
        long time = System.currentTimeMillis();
        addWords(c,br);
        time = System.currentTimeMillis() - time;
        System.out.println("parsed file in " + time + " ms");
    }
}


