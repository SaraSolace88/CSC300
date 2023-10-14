import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    private EdgeWeightedDigraph graph;
    int s;



    /**
     * Computes a shortest-paths tree from the source vertex to every other
     * vertex in the edge-weighted digraph G.
     *
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        graph = G;
        edgeTo = new DirectedEdge[G.nodes];
        distTo = new double[G.nodes];
        pq = new IndexMinPQ<>(G.nodes);
        this.s = s;

        for (int i = 0; i < G.nodes; i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty())
        {
            int i = pq.delMin();
            for (DirectedEdge e : G.bagArr[i])
                relax(e);
        }
    }

    private void relax(DirectedEdge e)
    {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight())
        {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else pq.insert (w, distTo[w]);
        }
    }

    /**
     * Returns the length of a shortest path from the source vertex s to vertex v.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex s to vertex v;
     *         Double.POSITIVE_INFINITY if no such path
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public double distTo(int v) {
        if(graph.nodes < v || v < 0) throw new IllegalArgumentException("The weight is negative");
        return distTo[v];
    }

    /**
     * Returns true if there is a path from the source vertex s to vertex v.
     *
     * @param  v the destination vertex
     * @return true if there is a path from the source vertex
     *         s to vertex v; false otherwise
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public boolean hasPathTo(int v) {
        if(v < 0 || v >= distTo.length) throw new IllegalArgumentException("Invalid array index");
        return !(distTo[v] == Double.POSITIVE_INFINITY);
    }

    /**
     * Returns a shortest path from the source vertex s to vertex v.
     *
     * @param  v the destination vertex
     * @return a shortest path from the source vertex s to vertex v
     *         as an iterable of edges, and null if no such path
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        if(v < 0 || v >= distTo.length) throw new IllegalArgumentException("Invalid array index");
        Stack<DirectedEdge> pathTo = new Stack<>();
        while(v != s){
            pathTo.push(edgeTo[v]);
            v = edgeTo[v].from;
        }
        return pathTo;
    }

    /**
     * Unit tests the DijkstraSP data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        int s = Integer.parseInt(args[1]);

        // compute shortest paths
        DijkstraSP sp = new DijkstraSP(G, s);


        // print shortest path
        for (int t = 0; t < G.getV(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f):\n", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print("\t"+e);
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d no path\n", s, t);
            }
        }
    }

}
