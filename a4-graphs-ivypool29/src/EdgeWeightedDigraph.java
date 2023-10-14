import edu.princeton.cs.algs4.Bag;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EdgeWeightedDigraph {
    Bag<DirectedEdge>[] bagArr;
    int nodes;
    int edges;

    /**
     * Creates a new empty graph with the provided number of nodes
     * @param V The number of nodes that the graph will contain
     */
    public EdgeWeightedDigraph(int V){
        bagArr = new Bag[V];
        for(int i = 0; i < V; i++){
            bagArr[i] = new Bag<DirectedEdge>();
        }
        nodes = V;
        edges = 0;
    }

    /**
     * Creates a new graph data structure from the contents of a file. The format
     * of the file is expected to match the format described in the assignment's
     * README file.
     * @param in The file that will be used to construct the graph
     */
    public EdgeWeightedDigraph(In in){
        nodes = in.readInt();
        edges = in.readInt();
        bagArr = new Bag[nodes];

        for(int i = 0; i < nodes; i++) {
            bagArr[i] = new Bag<DirectedEdge>();
        }
        for(int i = 0; i < edges; i++){
            int src = in.readInt();
            int dst = in.readInt();
            double weight = in.readDouble();
            DirectedEdge d = new DirectedEdge(src, dst, weight);
            bagArr[src].add(d);
        }
    }

    /**
     *
     * @return The number of nodes in the graph
     */
    public int getV() {
        return nodes;
    }

    /**
     *
     * @return The number of edges in the graph
     */
    public int getE() {
        return edges;
    }

    /**
     * Returns a Bag containing all of the edges that are adjacent to the provided
     * node ID
     * @param v The id of the node/vertex who's adjacent edges should be returned
     * @return A bag containing all of the edges that are adjacent to V
     * @throws NoSuchElementException when v is not a valid node/vertex ID
     */
    public Bag<DirectedEdge> adjEdges(int v) throws NoSuchElementException {
        if(bagArr.length <= v || v < 0) throw new NoSuchElementException();
        return bagArr[v];
    }

    /**
     * Returns a Bag containing all of the verticies that are adjacent to the provided
     * node/vertex ID
     * @param v The id of the node/vertex who's adjacent vertices should be returned
     * @return A bag containing all of the vertices that are adjacent to V
     * @throws NoSuchElementException when v is not a valid node/vertex ID
     */
    public Bag<Integer> adjVertices(int v) throws NoSuchElementException {
        if(v >= bagArr.length || v < 0){
            throw new NoSuchElementException("The index for the array is invalid");
        }
        Bag<Integer> bag = new Bag<>();
        for(DirectedEdge e : bagArr[v]){
            bag.add(e.to);
        }
        return bag;
    }

    /**
     * Adds a new edge to the graph
     * @param src The source node/vertex ID
     * @param dst The destination node/vertex ID
     * @param weight The weight associated with the edge
     * @throws NoSuchElementException if src or dst are invalid node IDs
     */
    public void addEdge(int src, int dst, double weight) throws NoSuchElementException{
        if(bagArr.length <= src || bagArr.length <= dst || src < 0 || dst < 0){
            throw new NoSuchElementException("The array index is invalid");
        }
        DirectedEdge d = new DirectedEdge(src, dst, weight);
        bagArr[src].add(d);
        bagArr[dst].add(d);
        edges++;
    }

    /**
     *
     * @return A bag containing all edges in the graph
     */
    public Bag<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (Bag<DirectedEdge> directedEdges : bagArr) {
            for (DirectedEdge e : directedEdges) {
                bag.add(e);
            }
        }
        return bag;
    }

    /**
     * Returns a string representation of this edge-weighted digraph. This method is fully
     * implemented. Do not modify.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        final String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(getV()).append(" ").append(getE()).append(NEWLINE);
        for (int v = 0; v < getV(); v++) {
            for (DirectedEdge e : adjEdges(v)) {
                s.append(e.toString());
            }
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }
}
