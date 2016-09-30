package a1;

/**
 * Created by johanrovala on 30/09/16.
 */
public class TreeNode implements A1TreeNode {

    public TreeNode parent;

    public TreeNode (){
        
    }

    @Override
    public boolean isJsonObject() {

        return false;
    }

    @Override
    public boolean isJsonArray() {
        return false;
    }

    @Override
    public boolean isJsonPrimitive() {
        return false;
    }
}
