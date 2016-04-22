package datastructures.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 4/21/2016.
 */
public class ConnectedComponentsTest {
    @Test
    public void doCC()  {
        Graph G = new Graph(13);
        G.addEdge(0, 5);
        G.addEdge(4, 3);
        G.addEdge(0, 1);
        G.addEdge(9, 12);
        G.addEdge(6, 4);
        G.addEdge(5, 4);
        G.addEdge(0, 2);
        G.addEdge(11, 12);
        G.addEdge(9, 10);
        G.addEdge(0, 6);
        G.addEdge(7, 8);
        G.addEdge(9, 11);
        G.addEdge(5, 3);
        G.printGraph();

        ConnectedComponents cc = new ConnectedComponents(G);
        assertTrue("CC func is wrong", cc.isConnected(0, 4));
        assertTrue("CC func is wrong", cc.isConnected(5, 6));
        assertTrue("CC func is wrong", cc.isConnected(10, 12));
        assertTrue("CC func is wrong", cc.isConnected(7, 8));
        assertFalse("CC func is wrong", cc.isConnected(0, 8));
        assertFalse("CC func is wrong", cc.isConnected(11, 8));

        assertEquals("CC func is wrong", 3, cc.count());
        assertEquals("CC func is wrong", 0, cc.id(4));
        assertEquals("CC func is wrong", 1, cc.id(7));
        assertEquals("CC func is wrong", 2, cc.id(10));

    }
}