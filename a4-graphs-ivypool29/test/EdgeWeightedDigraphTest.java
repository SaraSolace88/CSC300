import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class EdgeWeightedDigraphTest {
    /**
     * The graph contained inside of largeEWD.txt can take a significant amount of time to initially load (~5 minutes)
     * and due to how several of the unit tests work they to can take on the order of minutes (~2-3 minutes) to finish
     * running. To speed up the overall testing process the boolean below can be set to true to skip testing against the
     * large graph until you believe your implementation is mostly complete. Set this to `false` to execute all tests against
     * all sample graphs.
     */
    boolean SKIP_LARGE_GRAPH_TEST = false;

    /** A helper class that lets us compare the contents of the input file with what the graph is returning */
    private class GraphDetails {
        int vertCount;
        int edgeCount;
        Bag<DirectedEdge> edges = new Bag<>();

        public GraphDetails (String file){
            In in = new In(file);
            vertCount = in.readInt();
            edgeCount = in.readInt();
            for(int i = 0; i < edgeCount; i++){
                edges.add(new DirectedEdge(in.readInt(), in.readInt(), in.readDouble()));
            }
        }

        public Bag<DirectedEdge> getAdjacentEdges(int v){
            Bag<DirectedEdge> adjEdges = new Bag<>();
            for(DirectedEdge e : edges){
                if(e.from() == v){
                    adjEdges.add(e);
                }
            }
            return adjEdges;
        }

        public Bag<DirectedEdge> getEdges(){
            return edges;
        }
    }

    final String tinyGraphFile = "tinyEWD.txt";
    final String mediumGraphFile = "mediumEWD.txt";
    final String largeGraphFile = "largeEWD.txt";
    EdgeWeightedDigraph largeGraph = null;
    GraphDetails largeGroundTruth = null;
    EdgeWeightedDigraph graph;
    GraphDetails groundTruth;

    void loadTinyGraph(){
        groundTruth = new GraphDetails(tinyGraphFile);
        graph = new EdgeWeightedDigraph(new In(tinyGraphFile));
    }

    void loadMediumGraph(){
        groundTruth = new GraphDetails(mediumGraphFile);
        graph = new EdgeWeightedDigraph(new In(mediumGraphFile));
    }

    void loadLargeGraph(){
        if(SKIP_LARGE_GRAPH_TEST){
            assertEquals(1, 0, "set to skip large tests, marking as failure. Set SKIP_LARGE_GRAPH_TEST to false to run these tests");
        }
        if(null == largeGraph){
            largeGroundTruth = new GraphDetails(largeGraphFile);
            largeGraph = new EdgeWeightedDigraph(new In(largeGraphFile));
        }
        groundTruth = largeGroundTruth;
        graph = largeGraph;
    }


    /*******************************************************************************************************************
     * Helper methods which are used after a given graph has been loaded
     ******************************************************************************************************************/
    void testGetV(){
        assertEquals(groundTruth.vertCount, graph.getV());
    }

    void testGetE(){
        assertEquals(groundTruth.edgeCount, graph.getE());
    }

    void testAdjEdges(int v){
        SET<DirectedEdge> expectedEdges = new SET<>();
        for(DirectedEdge e : groundTruth.getAdjacentEdges(v)){
            expectedEdges.add(e);
        }

        Bag<DirectedEdge> edges = graph.adjEdges(v);
        assertEquals(expectedEdges.size(), edges.size());

        int matchedEdges = 0;
        for(DirectedEdge e : edges){
            StdOut.println(e);
            assertTrue(expectedEdges.contains(e));
            matchedEdges++;
        }
        assertEquals(expectedEdges.size(), matchedEdges);
    }

    void testAdjVerticies(int v){
        SET<Integer> expectedVertices = new SET<>();
        for(DirectedEdge e : groundTruth.getAdjacentEdges(v)){
            expectedVertices.add(e.to());
        }

        Bag<Integer> vertices = graph.adjVertices(v);
        int matchedVerts = 0;
        for(Integer vert : vertices){
            assertTrue(expectedVertices.contains(vert));
            matchedVerts++;
        }
        assertEquals(matchedVerts, expectedVertices.size());
    }

    void testEdges(){
        SET<DirectedEdge> expectedEdges = new SET<>();
        for(DirectedEdge e : groundTruth.getEdges()){
            expectedEdges.add(e);
        }
        Bag<DirectedEdge> edges = graph.edges();
        assertEquals(groundTruth.edgeCount, edges.size());
    }

    /*******************************************************************************************************************
     * Individual test cases
     ******************************************************************************************************************/

    @Test
    void TinyGraph_getV() {
        loadTinyGraph();
        testGetV();
    }

    @Test
    void TinyGraph_getE() {
        loadTinyGraph();
        testGetE();
    }

    @Test
    void TinyGraph_adjEdges() {
        loadTinyGraph();
        testAdjEdges(4);
    }

    @Test
    void TinyGraph_adjVertices() {
        loadTinyGraph();
        testAdjVerticies(6);
    }

    @Test
    void TinyGraph_addEdge() {
        loadTinyGraph();
        Bag<DirectedEdge> edges = graph.adjEdges(0);
        assertEquals(2, edges.size());

        graph.addEdge(0, 6, 3.00);
        edges = graph.adjEdges(0);
        assertEquals(3, edges.size());

        DirectedEdge newEdge = new DirectedEdge(0, 6, 3.00);
        boolean foundEdge = false;

        for(DirectedEdge e : edges){
            if(e.equals(newEdge)){
                foundEdge = true;
            }
        }
        assertTrue(foundEdge);
    }

    @Test
    void TinyGraph_edges() {
        loadTinyGraph();
        testEdges();
    }

    @Test
    void MediumGraph_getV() {
        loadMediumGraph();
        testGetV();
    }

    @Test
    void MediumGraph_getE() {
        loadMediumGraph();
        testGetE();
    }

    @Test
    void MediumGraph_adjEdges() {
        loadMediumGraph();
        testAdjEdges(4);
    }

    @Test
    void MediumGraph_adjVertices() {
        loadMediumGraph();
        testAdjVerticies(6);
    }

    @Test
    void MediumGraph_addEdge() {
        loadMediumGraph();
        Bag<DirectedEdge> edges = graph.adjEdges(0);
        assertEquals(21, edges.size());

        graph.addEdge(0, 6, 3.00);
        edges = graph.adjEdges(0);
        assertEquals(22, edges.size());

        DirectedEdge newEdge = new DirectedEdge(0, 6, 3.00);
        boolean foundEdge = false;

        for(DirectedEdge e : edges){
            if(e.equals(newEdge)){
                foundEdge = true;
            }
        }
        assertTrue(foundEdge);
    }

    @Test
    void MediumGraph_edges() {
        loadMediumGraph();
        testEdges();
    }

    @Test
    void LargeGraph_getV() {
        loadLargeGraph();
        testGetV();
    }

    @Test
    void LargeGraph_getE() {
        loadLargeGraph();
        testGetE();
    }

    @Test
    void LargeGraph_adjEdges() {
        loadLargeGraph();
        testAdjEdges(4);
    }

    @Test
    void LargeGraph_adjVertices() {
        loadLargeGraph();
        testAdjVerticies(6);
    }

    @Test
    void LargeGraph_addEdge() {
        loadLargeGraph();
        Bag<DirectedEdge> edges = graph.adjEdges(0);
        assertEquals(11, edges.size());

        graph.addEdge(0, 6, 3.00);
        edges = graph.adjEdges(0);
        assertEquals(12, edges.size());

        DirectedEdge newEdge = new DirectedEdge(0, 6, 3.00);
        boolean foundEdge = false;

        for(DirectedEdge e : edges){
            if(e.equals(newEdge)){
                foundEdge = true;
            }
        }
        assertTrue(foundEdge);
    }

    @Test
    void LargeGraph_edges() {
        loadLargeGraph();
        testEdges();
    }

    @Test
    void testAdjEdgesVertexDoesntExist(){
        loadTinyGraph();
        assertThrows(NoSuchElementException.class, ()-> {
           graph.adjEdges(10000);
        });
    }

    @Test
    void testAdjVerticiesVertexDoesntExist(){
        loadTinyGraph();
        assertThrows(NoSuchElementException.class, ()-> {
            graph.adjVertices(10000);
        });
    }

    @Test
    void testAddEdgeDstVertexDoesntExist(){
        loadTinyGraph();
        assertThrows(NoSuchElementException.class, ()-> {
            graph.addEdge(0, 10000, 1.00);
        });
    }

    @Test
    void testAddEdgeSrcVertexDoesntExist(){
        loadTinyGraph();
        assertThrows(NoSuchElementException.class, ()-> {
            graph.addEdge(11111110, 0, 1.00);
        });
    }

    @Test
    void testAddEdgeSrcAndDstVertexDoesntExist(){
        loadTinyGraph();
        assertThrows(NoSuchElementException.class, ()-> {
            graph.addEdge(11111110, 99999999, 1.00);
        });
    }
}
