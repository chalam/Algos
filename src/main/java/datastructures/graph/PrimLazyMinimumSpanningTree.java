package datastructures.graph;

import datastructures.heap.PriorityQueueMin;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Start with vertex 0 and greedily grow MST T
 * Add to T the min weight edge with exactly one endpoint in T
 * Repeat until v-1 edges in MST
 * Space = E
 * TIme Complexity = O((|V| + |E|) log |V|) = O(|E| log |V|)
 * Created by Lamuel on 4/22/2016.
 */
public class PrimLazyMinimumSpanningTree {
    private boolean[] marked; //MST vertices
    private Deque<Edge> mst;  //MST Edges
    private PriorityQueueMin<Edge> pq;

    public PrimLazyMinimumSpanningTree(EdgeWeightedGraph G) {
        pq = new PriorityQueueMin<>(G.E());
        marked = new boolean[G.V()];
        mst = new ArrayDeque<>();

        visit(G, 0);    // G is connected

        // log V times
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {

            // log V times
            Edge e = pq.deleteMin();

            // Ignore if both ends are in T
            if(marked[e.from()] && marked[e.to()]) {
                continue;
            }

            // Add to T the min weight edge with exactly one endpoint in T
            mst.add(e);
            if(!marked[e.from()]) visit(G, e.from());
            if(!marked[e.to()])   visit(G, e.to());
        }
    }

    /**
     *
     * @param G
     * @param v
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;   //add v to T
        for(Edge e : G.adj(v)) {
            if(!marked[e.other(v)])
                // E #ops * log E times
                pq.insert(e);   // add adj edge of v if its not in T
        }
    }

    /**
     * Get all the edges in MST
     * @return
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * Get the weight of MST
     * @return
     */
    public double weight() {
        double w = 0;
        for (Edge e : edges()) {
            w += e.weight();
        }
        return w;
    }
}
