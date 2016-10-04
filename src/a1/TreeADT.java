package a1;


/**
 * Created by johanrovala on 29/09/16.
 *
 * TreeADT
 */
public class TreeADT implements A1Tree {

    /*
     * Variables
     */

    private TreeNode root;
    private int size;

    /*
     * Constructor
     */

    public TreeADT(){
        this.root = null;
        this.size = 0;
    }

    /*
     * root() - Returns root.
     */

    @Override
    public A1TreeNode root() {
        return root;
    }

    /*
     * addChild() - If a 'child' is added with a null parent, this Node is declared as the root.
     * If not the child is added to the list of children to the parent, and the child is declared as a child of the parent.
     *
     *
     *
     * The last part is probably unnecessary, but it's to close to the deadline for me to dare changing anything.
     */

    @Override
    public void addChild(TreeNode parent, TreeNode child) {
        if (parent == null){
            this.root =  child;
        }
        else{
            parent.children.enqueue(child);
            child.parent = parent;
        }
        size++;
    }

    /*
     * size() - Returns size.
     */

    @Override
    public int size() {
        return size;
    }

    /*
     * printTree() - If the root has children, call printChildren().
     * Otherwise just print the root.
     */

    @Override
    public void printTree() {

        // The first print is an ugly little cheat, but it will have to do.

        if(root.hasChildren()){
            System.out.println(root.type);
            printChildren(root, 0);
        }else if(!(root.hasChildren())){
            System.out.println(root.type);
        }else{
            System.out.println("Tree is empty");
        }
    }

    /*
     * printChildren() - For each Node, check if it has children, if so, call the method again in order to print that Nodes children.
     * For each new call of printChildren() add a new '\t' in order to help further visualize that they are children
     * of the previous Node.
     */

    private void printChildren(TreeNode t, int indent){
        String indentation = "";
        for (int j = 0; j < indent; j++){
            indentation += "\t";
        }
        int length = t.getChildren().length();
        for(int i = 0; i < length; i++){
            TreeNode tmp = (TreeNode) t.getChildren().dequeue();
            if(tmp.hasChildren()){
                System.out.println("\t" + indentation + tmp.type);
                printChildren(tmp, indent += 1);
            }else{
                System.out.println(indentation + tmp.type);
            }
        }
    }
}
