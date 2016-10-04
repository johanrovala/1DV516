package a1;

import java.io.FileNotFoundException;

/**
 * Created by johanrovala on 03/10/16.
 *
 * Test - test class for the Main class.
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        QueueADT queue = (QueueADT) main.constructQueue("json.txt");
        TreeADT tree = (TreeADT) main.constructTree(queue);
        main.printTree(tree);
    }
}
