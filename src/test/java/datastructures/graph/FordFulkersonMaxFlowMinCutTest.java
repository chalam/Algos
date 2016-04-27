package datastructures.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 4/26/2016.
 */
public class FordFulkersonMaxFlowMinCutTest {
    @Test
    public void doMaxFlow()  {
        int V = 6;
        FlowNetwork G = new FlowNetwork(V);
        G.addEdge(new FlowEdge(0, 2, 3.0));
        G.addEdge(new FlowEdge(0, 1, 2.0));
        G.addEdge(new FlowEdge(1, 3, 3.0));
        G.addEdge(new FlowEdge(1, 4, 1.0));
        G.addEdge(new FlowEdge(2, 3, 1.0));
        G.addEdge(new FlowEdge(2, 4, 1.0));
        G.addEdge(new FlowEdge(3, 5, 2.0));
        G.addEdge(new FlowEdge(4, 5, 3.0));

        int s = 0;
        int t = 5;
        FordFulkersonMaxFlowMinCut ffm = new FordFulkersonMaxFlowMinCut(G, s, t);
        assertEquals("Max flow Value is wrong", 4, ffm.value());
    }

}