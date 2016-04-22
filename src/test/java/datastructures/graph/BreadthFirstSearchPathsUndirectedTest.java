package datastructures.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 4/21/2016.
 */
public class BreadthFirstSearchPathsUndirectedTest {
    @Test
    public void doBFS()  {
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

        BreadthFirstSearchPathsUndirected bfs = new BreadthFirstSearchPathsUndirected(G, 0);
        assertTrue("DFS func is wrong", bfs.hasPathTo(3));
        assertFalse("DFS func is wrong", bfs.hasPathTo(7));
        System.out.println(bfs.pathTo(3));
        System.out.println(bfs.pathTo(4));

        bfs = new BreadthFirstSearchPathsUndirected(G, 10);
        assertTrue("DFS func is wrong", bfs.hasPathTo(12));
        assertFalse("DFS func is wrong", bfs.hasPathTo(7));
        System.out.println(bfs.pathTo(12));
    }
}