import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraSPTest {
    final String tinyGraphFile = "tinyEWD.txt";
    final String mediumGraphFile = "mediumEWD.txt";
    final String largeGraphFile = "largeEWD.txt";

    boolean SKIP_LARGE_GRAPH_TEST;
    EdgeWeightedDigraph graph;
    EdgeWeightedDigraph largeGraph;
    DijkstraSP sp;
    DijkstraSP largeSp;

    public DijkstraSPTest(){
        EdgeWeightedDigraphTest t = new EdgeWeightedDigraphTest();
        SKIP_LARGE_GRAPH_TEST = t.SKIP_LARGE_GRAPH_TEST;
    }

    void loadTinyGraph(){
        graph = new EdgeWeightedDigraph(new In(tinyGraphFile));
        sp = new DijkstraSP(graph, 0);
    }

    void loadMediumGraph(){
        graph = new EdgeWeightedDigraph(new In(mediumGraphFile));
        sp = new DijkstraSP(graph, 0);
    }

    void loadLargeGraph(){
        if(SKIP_LARGE_GRAPH_TEST){
            assertEquals(1, 0, "set to skip large tests, marking as failure. Set SKIP_LARGE_GRAPH_TEST to false to run these tests");
        }
        if(null == largeGraph){
            largeGraph = new EdgeWeightedDigraph(new In(largeGraphFile));
            largeSp = new DijkstraSP(largeGraph, 0);
        }
        graph = largeGraph;
        sp = largeSp;
    }

    @Test
    void tinyGraph_distTo() {
        loadTinyGraph();
        assertEquals(1.05, sp.distTo(1));
    }

    @Test
    void tinyGraph_distToDstDoesntExist() {
        loadTinyGraph();
        assertThrows(IllegalArgumentException.class, ()->{
           sp.distTo(9999);
        });
    }

    @Test
    void tinyGraph_hasPathToTrue() {
        loadTinyGraph();
        assertTrue(sp.hasPathTo(1));
    }

    @Test
    void tinyGraph_hasPathToDstDoesntExist() {
        loadTinyGraph();
        assertThrows(IllegalArgumentException.class, ()->{
            sp.hasPathTo(9999);
        });
    }

    @Test
    void tinyGraph_validatePathTo(){
        loadTinyGraph();
        DirectedEdge[] truePath = new DirectedEdge[3];
        truePath[0] = new DirectedEdge(0, 4, 0.38);
        truePath[1] = new DirectedEdge(4, 5, 0.35);
        truePath[2] = new DirectedEdge(5, 1, 0.32);

        int i = 0;
        for (DirectedEdge e : sp.pathTo(1)) {
            assertEquals(truePath[i++], e);
        }
    }

    @Test
    void mediumGraph_distTo() {
        loadMediumGraph();
        assertEquals(0.8152500000000001, sp.distTo(100));
    }

    @Test
    void mediumGraph_distToDstDoesntExist() {
        loadMediumGraph();
        assertThrows(IllegalArgumentException.class, ()->{
            sp.distTo(9999);
        });
    }

    @Test
    void mediumGraph_hasPathToTrue() {
        loadMediumGraph();
        assertTrue(sp.hasPathTo(249));
    }

    @Test
    void mediumGraph_hasPathToDstDoesntExist() {
        loadMediumGraph();
        assertThrows(IllegalArgumentException.class, ()->{
            sp.hasPathTo(250);
        });
    }

    @Test
    void mediumGraph_validatePathTo(){
        loadMediumGraph();
        DirectedEdge[] truePath = new DirectedEdge[6];
        truePath[0] = new DirectedEdge(0, 44, 0.06471);
        truePath[1] = new DirectedEdge(44, 93, 0.06793);
        truePath[2] = new DirectedEdge(93, 187, 0.0764);
        truePath[3] = new DirectedEdge(187, 77, 0.10655);
        truePath[4] = new DirectedEdge(77, 78, 0.10966);
        truePath[5] = new DirectedEdge(78, 239, 0.02065);

        int i = 0;
        for (DirectedEdge e : sp.pathTo(239)) {
            assertEquals(truePath[i++], e);
        }
    }

    @Test
    void largeGraph_distTo() {
        loadLargeGraph();
        assertEquals(0.8683810399999995, sp.distTo(81870));
    }

    @Test
    void largeGraph_distToDstDoesntExist() {
        loadLargeGraph();
        assertThrows(IllegalArgumentException.class, ()->{
            sp.distTo(-1);
        });
    }

    @Test
    void largeGraph_hasPathToTrue() {
        loadLargeGraph();
        assertTrue(sp.hasPathTo(81869));
    }

    @Test
    void largeGraph_hasPathToDstDoesntExist() {
        loadLargeGraph();
        assertThrows(IllegalArgumentException.class, ()->{
            sp.hasPathTo(9999999);
        });
    }
}
