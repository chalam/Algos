package datastructures.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * computes the maximum flow in a flow network.
 * https://en.wikipedia.org/wiki/Ford%E2%80%93Fulkerson_algorithm
 * "Ford–Fulkerson" is often also used for the Edmonds–Karp algorithm,
 * which is a specialization of Ford–Fulkerson.
 * Created by Lamuel on 4/26/2016.
 */
public class FordFulkersonMaxFlowMinCut {
    private boolean[] marked;   // true if s->v path in residual network
    private FlowEdge[] edgeTo;  // last edge on s->v path
    private double value;       // value of flow


    public FordFulkersonMaxFlowMinCut(FlowNetwork G, int s, int t) {
        value = 0.0;
        while (hasAugmentingPath(G, s, t)) {
            double bottleneck = Double.POSITIVE_INFINITY;

            // move up the augmenting path to calc bottleneck
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottleneck = Math.min(bottleneck, edgeTo[v].residualCapacityTo(v));
            }

            // move up the augmenting path and add bottleneck to the residual flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottleneck);
            }

            value += bottleneck;
        }
    }

    /**
     * Find the shortest path using BFS
     * @param G
     * @param s
     * @param t
     * @return
     */
    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        Deque<Integer> queue = new ArrayDeque<Integer>();
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.removeFirst();

            for(FlowEdge e : G.adj(v)) {
                int w = e.other(v);

                // found path from s to w in the residual nw
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
        return marked[t];
    }

    /**
     * Get the max flow or min cut value
     * @return
     */
    public double value() {
        return value;
    }

    /**
     * is v reachable from s in the residual network
     * @param v
     * @return
     */
    public boolean inCut(int v) {
        return marked[v];
    }


}
