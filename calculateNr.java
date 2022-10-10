/**
 * This class contains the main method, declared with the usual method header: public static void main(String[] args).
 *
 * @author Zeshan Ahmed <zahme2@uwo.ca>
 */

import java.util.Iterator;

public class calculateNr {

    public static void main(String[] args) {
        if (args.length > 0) {
            FileWordRead words = new FileWordRead(args[0]);
            Iterator<String> it = words.getIterator(); // grab the iterator into variable it
            AVLTree<String, Integer> t1 = new AVLTree();
            while (it.hasNext()) { // Check if anything is left in the iterator
                String next = it.next(); // get the next item in the iterator
                t1.insert(next);
            }
            t1.inorder();
            AVLTree<Integer, Integer> t2 = new AVLTree();
            AVLTree<String, Integer> duplicate = new AVLTree();
            it = words.getIterator();
             while (it.hasNext()) { 
                String next = it.next();
                if(duplicate.search(next)==null)
                {
                     t2.insert(t1.search(next).element().getValue());
                     duplicate.insert(next);
                }
            }
            System.out.println();
            t2.inorder(); 
        }
    }
}
