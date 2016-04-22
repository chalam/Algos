package datastructures.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * find connected ocmponents in Graph
 * Created by Lamuel on 4/18/2016.
 */
public class ConnectedComponents {
    private int[] id;
    private int count;
    private boolean[] visited;

    public ConnectedComponents(Graph G) {
        visited = new boolean[G.V()];
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if(!visited[v]) {
                dfs(G, v);
                count++;    // run DFS for each component
            }
        }

    }

    /**
     * run dfs form s
     * @param G
     * @param s
     */
    private void dfs(Graph G, int s) {
        visited[s] = true;
        id[s] = count;  // keep the component count
        for (int w : G.adj(s)) {
            if (!visited[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * is v and w in the same component
     * @param v
     * @param w
     * @return
     */
    public boolean isConnected(int v, int w) {
        return (id[v] == id[w]);
    }

    /**
     * count of components
     * @return
     */
    public int count() {
        return count;
    }

    /**
     * component index
     * @param v
     * @return
     */
    public int id(int v) {
        return id[v];
    }
}
