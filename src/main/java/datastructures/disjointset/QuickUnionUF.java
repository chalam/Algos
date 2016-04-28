package datastructures.disjointset;

import java.util.Arrays;

/**
 * Two components are connected if they share the same parent
 * init = N
 * union = N
 * find = N
 * total = M N (M - connections on N nodes)
 * Created by Lamuel on 4/27/2016.
 */
public class QuickUnionUF implements UnionFind {

    protected int[] parent;   //parent[i] = parent of i
    protected int count;      //number of components

    QuickUnionUF() {}

    /**
     * MakeSet x in its own set
     * @param N
     */
    public QuickUnionUF(int N) {
        parent = new int[N];
        count = N;

        // MakeSet x
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }
    /**
     * Add connection b/w p and q
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        // p and q are already in the same component
        if (rootP == rootQ) return;

        parent[rootP] = rootQ;

        count--;
    }

    /**
     * component id for p (0 to N-1)
     *
     * @param p
     * @return
     */
    @Override
    public int find(int p) {
        validate(p);
        int root = root(p);
        return root;
    }

    /**
     * find the root and compress path
     * @param i
     * @return
     */
    protected int root(int i) {
        while (i != parent[i])
            i = parent[i];

        return i;
    }

    /**
     * returns tree of p and q are in the same component
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * number of component
     *
     * @return
     */
    @Override
    public int count() {
        return count;
    }


    // validate that p is a valid index
    protected void validate(int p) {
        int N = parent.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuickUnionUF{");
        sb.append("parent=").append(Arrays.toString(parent));
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
