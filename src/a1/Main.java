package a1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by johanrovala on 30/09/16.
 *
 * Main - creates a QueueADT which is in turn used to construct a TreeADT.
 * The queue reads a json file, the children and parents are then read into the tree accordingly.
 */
public class Main implements A1Main {

    /*
     * Variables
     */

    private Scanner n;
    private QueueADT<String> queue;

    /*
     * constructQueue() - Reads the filename input and constructs a Queue where every new line is read as a Node.
     */

    @Override
    public A1Queue<String> constructQueue(String jsonFilename) throws FileNotFoundException {
        File file = new File(jsonFilename);
        n = new Scanner(file);
        queue = new QueueADT();
        while (n.hasNextLine()){
            queue.enqueue(n.nextLine().toString());
        }
        return queue;
    }

    /*
     * constructTree() - Creates a tree, a TreeNode root, a TreeNode child and a StackADT parents in order to keep
     * track of the current parents.
     *
     * As a bracket of jsonObject or jsonArray gets encountered they are pushed into the stack as the current parent.
     * Should a corresponding closing bracket arrive the current parent is popped from the stack and can no longer
     * recieve any new children. The first object is viewed as the root no matter what as long it is of the jsonObject type.
     */

    @Override
    public A1Tree<String> constructTree(A1Queue<String> jsonQueue) throws NullPointerException{

        // Setup tree, root, child and Stack for keeping track of parents.

        TreeADT tree = new TreeADT();
        TreeNode root = new TreeNode(jsonQueue.dequeue());
        TreeNode child;
        StackADT<TreeNode> parents = new StackADT<>();

        // If Root is a json object (starts with '{') then add it as the root and confirm that it (at least at the start) is a
        // valid json object.

        if(root.isJsonObject()){
            parents.push(root);
            tree.addChild(null, (TreeNode) parents.peek());
        }else{
            System.out.println("Syntax error in json file (?)");
        }

        // Traverse the whole queue

        while(!(jsonQueue.length() == 0)){

            // If a json object or array is encountered, add it as a new parent and add it as a child to the old parent.

            TreeNode temp = new TreeNode(jsonQueue.peek());


            if(temp.isJsonObject() || temp.isJsonArray()){
                child = new TreeNode((jsonQueue.dequeue()));
                tree.addChild((TreeNode) parents.peek(), child);
                parents.push(child);
            }

            // If a closing tag to a json object or array appears, remove the latest parent from the stack.

            else if(temp.type.equals("}") || temp.type.equals("]")){
                child = new TreeNode(jsonQueue.dequeue());
                tree.addChild((TreeNode) parents.pop(), child);
            }

            // If it is not a beginning of a new Parent or a closing tag, it is a primitive type or some form of content.
            // At it as a child to the latest parent.

            else {
                child = new TreeNode(jsonQueue.dequeue());
                tree.addChild((TreeNode) parents.peek(), child);
            }
        }

        return tree;
    }

    /*
     * printTree() - calls TreeADT.printTree()
     */

    @Override
    public void printTree(A1Tree<String> jsonTree) {
        jsonTree.printTree();
    }
}
