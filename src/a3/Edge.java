package a3;


/**
 * Created by johanrovala on 25/10/16.
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public void extraRemove(){
        this.from.getOutDegreeNodes().remove(this.to);
        this.to.getInDegreeNodes().remove(this.from);
        this.from = null;
        this.to = null;
    }
}
