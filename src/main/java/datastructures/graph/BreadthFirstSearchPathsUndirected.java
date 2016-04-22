package datastructures.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Breadth First Search Paths
 * Created by Lamuel on 4/18/2016.
 */
public class BreadthFirstSearchPathsUndirected {
    private int s; //source vertex
    private int[] edgeTo;   //edgeTo[w] = v means that edge v-w taken to visit w from v
    private boolean[] visited;
    Deque<Integer> queue = new ArrayDeque<>();

    public BreadthFirstSearchPathsUndirected(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = i;
        }
        visited = new boolean[G.V()];
        bfs(G, s);
    }

    /**
     * put s onto to FIFO queue and mark as visited
     * repeat until empty queue
     *   remove the least recently added vertex
     *   add each of v's unvisited adj to queue
     *   and mark them as visited
     * @p1aram G
     * @param s
     */
    private void bfs(Graph G, int s){
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.removeFirst();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    edgeTo[w] = v;
                }
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
