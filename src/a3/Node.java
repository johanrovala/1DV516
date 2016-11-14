package a3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by johanrovala on 25/10/16.
 */
public class Node {

    public int id;
    public int data;
    private ArrayList<Edge> listOfEdges;
    private LinkedList<Node> outDegreeNodes;
    private LinkedList<Node> inDegreeNodes;

    public Node(int id, int data){
        this.id = id;
        this.data = data;
        listOfEdges = new ArrayList<>();
        outDegreeNodes = new LinkedList<>();
        inDegreeNodes = new LinkedList<>();
    }

    public void addEdge(Edge e) { listOfEdges.add(e); }
    public void addOutDegreeNode(Node n) { outDegreeNodes.add(n); }
    public void addInDegreeNode(Node n) {inDegreeNodes.add(n);}

    public ArrayList<Edge> getListOfEdges(){
        return this.listOfEdges;
    }

    public LinkedList<Node> getOutDegreeNodes(){
        return this.outDegreeNodes;
    }

    public LinkedList<Node> getInDegreeNodes(){
        return this.inDegreeNodes;
    }
}
