package datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Undirected Graph impl with Adjacency List
 * space = E + V
 * add Edge = 1
 * edge b/w v - w = degree(v)
 * iterate over adj = degree(v)
 * Created by Lamuel on 4/18/2016.
 */
public class Graph {
    private final int V;
    private int E;
    private ArrayList<Integer>[] adj;

    /**
     * init graph with size V vertices
     * @param V no of vertices
     */
    public Graph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>();
        }
        this.E = 0;
    }

    /**
     * Add two undirected edges from v to w and w to v
     * @param v one vertex
     * @param w another vertex
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * count of Vertices
     * @return count
     */
    public int V() {
        return this.V;
    }

    /**
     * count of Edges
     * @return couunt
     */
    public int E() {
        return this.E;
    }

    /**
     * count of Edges
     * @return couunt
     */
    public Iterable<Integer> adj(int v) {
        return this.adj[v];
    }

    /**
     * Prints out the G
     */
    public  void printGraph() {
        for (int v = 0; v < V(); v++) {
            for(int w : adj(v)) {
                System.out.format("\n%d - %d", v, w);
            }
            System.out.println();
        }
    }

    /**
     * Get the degree of vertex v
     * @param G
     * @param v
     * @return
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) degree++;
        return degree;
    }

    /**
     * Get the max degree of Graph G
     * @param G
     * @return
     */
    public static int maxDegree(Graph G) {
        int maxDegree = Integer.MIN_VALUE;
        for (int v = 0; v < G.V(); v++) {
            int degree = degree(G, v);
            if (degree > maxDegree) maxDegree = degree;
        }
        return maxDegree;
    }

    /**
     * There are twice the edges
     * @param G
     * @return
     */
    public static double averageDegree(Graph G) {
        return 2.0 * G.V() / G.E();
    }

    /**
     *
     * @param G
     * @return
     */
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for(int w : G.adj(v)) {
                if (v == w) count++;
            }
        }
        return count;
    }

    /**
     * Is there a edge between v and w?
     * @param v
     * @param w
     * @return
     */
    public static boolean isConnected(Graph G, int v, int w) {
        for (Integer i : G.adj(v)) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Graph{");
        sb.append("V=").append(V);
        sb.append(", E=").append(E);
        sb.append(", adj=").append(Arrays.toString(adj));
        sb.append('}');
        return sb.toString();
    }
}
