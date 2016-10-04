package a1;


/**
 * Created by johanrovala on 30/09/16.
 *
 * TreeNode
 */
public class TreeNode implements A1TreeNode {

    /*
     * Variables
     *
     * parent might be unnecessary
     */

    public Object type;
    public TreeNode parent;
    public QueueADT<TreeNode> children;

    /*
     * Constructor
     */

    public TreeNode (Object type){
        this.type = type;
        children = new QueueADT<>();
    }

    /*
     * getChildren() - Returns this Nodes list of children.
     */

    public QueueADT<TreeNode> getChildren(){
        return this.children;
    }

    /*
     * hasChildren() - Returns true if this Node has children.
     */

    public boolean hasChildren(){
        return !(this.getChildren().isEmpty());
    }

    /*
     * isJsonObject() - Checks if this Node is of type jsonObject
     */

    @Override
    public boolean isJsonObject() {
        return this.type.equals("{");
    }

    /*
     * isJsonArray() - Checks if this Node is of type jsonArray
     */


    @Override
    public boolean isJsonArray() {
        return this.type.equals("[");
    }

    /*
     * isJsonPrimitive() - Checks if this Node is of type jsonPrimitive
     *
     * this method is never used.
     */


    @Override
    public boolean isJsonPrimitive() {
        return this.type == "";
    }
}
