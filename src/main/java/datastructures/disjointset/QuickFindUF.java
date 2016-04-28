package datastructures.disjointset;

import java.util.Arrays;

/**
 * maintains the invariant that p and q are connected if and only if id[p] is equal to id[q].
 * In other words, all sites in a component must have the same value in id[].
 * Mark all id of p to q
 * init = N
 * union = N
 * find = 1
 * total = M N (M - connections on N nodes)
 * Created by Lamuel on 4/27/2016.
 */
public class QuickFindUF implements UnionFind {
    private int[] id;
    private int count;

    /**
     * MakeSet x
     * @param N
     */
    public QuickFindUF(int N) {
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * Add connection b/w p and q
     * make a connection by changing pid to qid
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        // p and q are already in the same component
        if (pid == qid) return;

        for (int i = 0; i < id.length; i++) {
            if(id[i] == pid)
                id[i] = qid;
        }
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
        return id[p];
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuickFindUF{");
        sb.append("id=").append(Arrays.toString(id));
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
