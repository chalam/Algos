package datastructures.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 4/21/2016.
 */
public class GraphTest {

    @Test
    public void makeGraph()  {
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
        System.out.println(G.toString());
        assertEquals("Count of vertices is wrong", 13, G.V());
        assertEquals("Count of edges is wrong", 13, G.E());
        assertEquals("Degree of vertex 0 is wrong", 4, Graph.degree(G, 0));
        assertEquals("MAX degree is wrong", 4, Graph.maxDegree(G));
        assertEquals("Avg degree is wrong", 2.0, Graph.averageDegree(G), 0.0001d);
        assertEquals("Count of self loops is wrong", 0, Graph.numberOfSelfLoops(G));
        assertFalse("Connected func is wrong", Graph.isConnected(G, 10, 12));
        assertFalse("Connected func is wrong", Graph.isConnected(G, 0, 12));
        assertTrue("Connected func is wrong", Graph.isConnected(G, 3, 4));
        assertTrue("Connected func is wrong", Graph.isConnected(G, 9, 12));
    }
}