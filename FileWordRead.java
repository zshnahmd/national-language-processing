/**
 * This class is a program for reading words from file. It contains a constructor which takes the name of the file to
 * read tokens from and the getIterator function which returns iterator over words read from the input file.
 *
 * @author Zeshan Ahmed <zahme2@uwo.ca>
 */

import java.util.Scanner;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWordRead {

    private LinkedList list = new LinkedList();

    /**
     * A constructor which takes the name of the file to read tokens from
     */
    FileWordRead(String name) {
        try {
            File file = new File("words.txt");
            Scanner input = new Scanner(file);
            int count = 0;
            while (input.hasNext()) {
                
                list.addLast(input.next().replace(".", "")
                        .replace(",", "").replace("!", "").toLowerCase());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWordRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns interator over words read from the input file
     */
    public Iterator<String> getIterator()
    {
        return list.iterator();
    }
}
