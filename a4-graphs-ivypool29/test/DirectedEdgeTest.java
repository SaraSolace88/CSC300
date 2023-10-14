import edu.princeton.cs.algs4.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectedEdgeTest {
    DirectedEdge de;

    @BeforeEach
    void setup(){
        de = new DirectedEdge(1, 2, 1.00);
    }

    @Test
    void from() {
        assertEquals(1, de.from());
    }

    @Test
    void to() {
        assertEquals(2, de.to());
    }

    @Test
    void weight() {
        assertEquals(1.00, de.weight());
    }

}
