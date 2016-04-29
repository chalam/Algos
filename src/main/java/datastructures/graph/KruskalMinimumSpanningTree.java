package datastructures.graph;

import datastructures.disjointset.UnionFind;
import datastructures.disjointset.WeightedQuickUnionWithPathCompressionUF;
import datastructures.heap.PriorityQueueMin;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Consider edges in ascending order of weight
 *   Add next edge to MST unless doing so would recreate a cycle
 *
 * Complexity = E log E
 * E is atmost V^2 and log V^2 = 2 log V
 * so Complexity = 2 E log V
 *
 * Created by Lamuel on 4/22/2016.
 */
public class KruskalMinimumSpanningTree {
    private Deque<Edge> mst;
    public KruskalMinimumSpanningTree(EdgeWeightedGraph G) {
        PriorityQueueMin<Edge> pq = new PriorityQueueMin(G.E());
        mst =  new ArrayDeque<>();

        // 1 op * E log E time/ops
        for(Edge e : G.edges())
            pq.insert(e);

        UnionFind uf = new WeightedQuickUnionWithPathCompressionUF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            // E op * log E time/ops
            Edge e = pq.deleteMin();
            // E op * log* V time/ops
            if (!uf.isConnected(e.from(), e.to())) {
                // V op * log* V time/ops
                uf.union(e.from(), e.to());
                mst.add(e);
            }
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
        double w = 0.0;
        for (Edge e : edges()) {
            w += e.weight();
        }
        return w;
    }
}
