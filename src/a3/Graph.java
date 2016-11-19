package a3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by johanrovala on 25/10/16.
 */
public class Graph implements A3Graph{

    // Variables

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private boolean[] visited;

    // Check if dotlang file specifies digraph or ordinary graph.

    private final String digraph = "digraph";
    private final String graph = "graph";

    // Regex to extract Node creation pattern.

    String regex = "\\s+\\d+\\s+->\\s+\\d+\\s+\\[weight=\\d+\\];";
    Pattern pattern = Pattern.compile(regex);


    // Constructor
    //
    // Takes the filename and parses the dot file into actual Node Java Objects

    public Graph (String fileName) throws FileNotFoundException {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        parseDotFile(fileName);
    }

    private void parseDotFile(String fileName) throws FileNotFoundException {
        Scanner n = new Scanner(new File(fileName));
        ArrayList<String> linesInFile = new ArrayList<>();

        while (n.hasNext()){
            linesInFile.add(n.nextLine());
        }
        if(linesInFile.get(0).contains(digraph)){

            for (int i = 1; i < linesInFile.size(); i++){

                // Check if line in file matches the regex, if so create Nodes and edges according to the specification
                // in the dotlang line.

                Matcher matcher = pattern.matcher(linesInFile.get(i));
                if(matcher.matches()){

                    // Remove unnecessary characters from the line, only send values that are relevant to Nodes and Edges.

                    String nodesAndEdges = matcher.group(0).replaceAll("[^0-9]","");
                    createNodesFromDotLine(nodesAndEdges);
                }
            }

        }
        else if (linesInFile.get(0).contains(graph)){

            // Future implementation for undirected graph goes here.

            return;
        }
        else {

            // Invalid dotlang file.

            return;
        }
    }

    // Private helper method. Helps to find any Node in regards to its ID.

    private Node getNodeFromList(int id){
        for (Node n : nodes){
            if (n.data == id){
                return n;
            }
        }
        return null;
    }

    private void createNodesFromDotLine(String s) {

        int first = Integer.parseInt(String.valueOf(s.charAt(0)));
        int second = Integer.parseInt(String.valueOf(s.charAt(1)));
        int edge = Integer.parseInt(String.valueOf(s.charAt(2)));

        addNode(first);
        addNode(second);
        addEdge(first, edge, second);
    }

    @Override
    public void addNode(int nodeItem) {
        if (getNodeFromList(nodeItem) == null){
            Node node = new Node(nodes.size(), nodeItem);
            nodes.add(node);
        }
    }

    @Override
    public void addEdge(int srcNodeItem, int weigth, int tgtNodeItem) {
        Edge e = new Edge(weigth, getNodeFromList(srcNodeItem), getNodeFromList(tgtNodeItem));
        getNodeFromList(srcNodeItem).addOutDegreeNode(getNodeFromList(tgtNodeItem));
        getNodeFromList(srcNodeItem).addEdge(e);
        getNodeFromList(tgtNodeItem).addInDegreeNode(getNodeFromList(srcNodeItem));
        edges.add(e);
    }

    @Override
    public boolean hasNode(int nodeItem) {
        return getNodeFromList(nodeItem) != null;
    }

    @Override
    public boolean hasEdge(int srcNodeItem, int weigth, int tgtNodeItem) {
        Node n = getNodeFromList(srcNodeItem);
        for (Edge e : n.getListOfEdges()){
            if (e.from.data == srcNodeItem && e.weight == weigth && e.to.data == tgtNodeItem)
                return true;
        }
        return false;
    }

    @Override
    public void printAllNodes() {
        for (Node n : nodes){
            System.out.print("[ " + n.data + " ]");
        }
    }

    @Override
    public void printAllEdges() {
        for (Node n : nodes){
            for (Edge e : n.getListOfEdges()){
                try{
                    System.out.println("[" + e.from.data + "] -> " + e.weight + " -> [" + e.to.data + "]");
                }catch (NullPointerException exception ){

                }
            }
        }
    }

    @Override
    public boolean isConnected() {
        visited = new boolean[nodes.size()];
        for (Node n : nodes){
            depthFirstSearch(n.id);
            for (boolean b : visited){
                if (!b){
                    return false;
                }
            }
            resetVisited();
        }

        return true;
    }

    private void resetVisited(){
        for (int i = 0; i < visited.length; i++){
            visited[i] = false;
        }
    }

    private void depthFirstSearch(int n){
        visited[n] = true;
        for (Node node : nodes.get(n).getOutDegreeNodes()){
            if(!visited[node.id]){
                depthFirstSearch(node.id);
            }
        }

    }

    @Override
    public boolean isAcyclic() {
        return topSort() != null;
    }


    private LinkedList<Node> topSort(){

        LinkedList<Node> sortedTopList = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();

        for (Node n : nodes){
            if(n.getInDegreeNodes().size() == 0){
                queue.add(n);
            }
        }

        while(!queue.isEmpty()){

            Node n = queue.remove();
            sortedTopList.add(n);
            Queue<Node> outDegreeNodes = n.getOutDegreeNodes();

            while (!outDegreeNodes.isEmpty()){
                Node m = outDegreeNodes.remove();
                Edge edge = null;
                for(Edge e : edges){
                    if(e.from.data == n.data && e.to.data == m.data){
                        edge = e;
                    }
                }
                if (edge != null){
                    edges.remove(edge);
                    edge.extraRemove();
                }


                if (m.getInDegreeNodes().size() == 0){
                    queue.add(m);
                }
            }
        }

        if (edges.isEmpty()){
            return sortedTopList;
        }else{
            return null;
        }
    }



    @Override
    public Map<Integer, Integer> shortestPath(int nodeItem, ArrayList<Edge> edgeSubSet) {

        // Check if node exists

        if (!hasNode(nodeItem)){
            System.err.println("Node is not part of graph");
        }

        // Check if inputed node has indegree nodes.

        if (getNodeFromList(nodeItem).getInDegreeNodes().size() != 0){
            System.err.println("Input node is not a 0-indegree node");
            return null;
        }

        // Find all 0 outdegree nodes and add to list
        LinkedList<Node> nodesToReach = new LinkedList<>();
        for (int i = 0; i < nodes.size(); i++){
            if (nodes.get(i).getOutDegreeNodes().size() == 0){
                nodesToReach.add(nodes.get(i));
            }
        }

        for (Node n : nodesToReach){
            int computePath(getNodeFromList(nodeItem), n, edgeSubSet);
        }




        return null;
    }

    public static void main (String[] args) throws FileNotFoundException {
        String fileName = "dotlang.txt";
        Graph g = new Graph(fileName);

        System.out.println(g.hasNode(1));

        System.out.println("Nodes");
        g.printAllNodes();
        System.out.println();

        System.out.println("Edges");
        g.printAllEdges();

        System.out.println(g.isConnected());
        System.out.println(g.isAcyclic());
    }
}
