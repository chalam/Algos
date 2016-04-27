package datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lamuel on 4/26/2016.
 */
public class FlowNetwork {
    private final int V;
    private ArrayList<FlowEdge>[] adj;

    /**
     * Init Graph
     * @param V
     */
    public FlowNetwork(int V) {
        this.V = V;
        adj = (ArrayList<FlowEdge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<FlowEdge>();
        }
    }

    public int V() {
        return V;
    }

    /**
     * add a flow edge to flow network
     * @param e
     */
    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        adj[w].add(e);
    }

    /**
     * Get the adjacency list
     * @param v
     * @return
     */
    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlowNetwork{");
        sb.append("V=").append(V);
        sb.append(", adj=").append(Arrays.toString(adj));
        sb.append('}');
        return sb.toString();
    }
}
