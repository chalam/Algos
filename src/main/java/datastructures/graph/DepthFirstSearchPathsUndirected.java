package datastructures.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Depth First Search Paths
 * Created by Lamuel on 4/18/2016.
 */
public class DepthFirstSearchPathsUndirected {
    private int s; //source vertex
    private int[] edgeTo;   //edgeTo[w] = v means that edge v-w taken to visit w from v
    private boolean[] visited;

    public DepthFirstSearchPathsUndirected(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = i;
        }
        visited = new boolean[G.V()];
        dfs(G, s);
    }

    /**
     * visit a vertex.
     * recursively visit all its adjacent vertices
     * @param G
     * @param s
     */
    private void dfs(Graph G, int s){
        visited[s] = true;
        for(int w: G.adj(s)) {
            if(!visited[w]) {
                dfs(G, w);
                edgeTo[w] = s;
            }
        }
    }

    /**
     * is there a path from s to v
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return visited[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Deque<Integer> path = new ArrayDeque<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
