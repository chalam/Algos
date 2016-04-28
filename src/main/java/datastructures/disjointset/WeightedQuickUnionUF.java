package datastructures.disjointset;

import java.util.Arrays;

/**
 * Rather than arbitrarily connecting the second tree to the first for union() in the quick-union algorithm,
 * we keep track of the size of each tree and always connect the smaller tree to the larger.
 * init = N
 * union = lg N depth of tree
 * find = lg N
 * total = N + M log N (M - connections on N nodes)
 * Created by Lamuel on 4/27/2016.
 */
public class WeightedQuickUnionUF extends QuickUnionUF {
    protected int[] size; //size[i] = number of sites in subtree rooted at i

    WeightedQuickUnionUF() {}

    public WeightedQuickUnionUF(int N) {
        count = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Merges the component containing site <tt>p</tt> with the
     * the component containing site <tt>q</tt>.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IndexOutOfBoundsException unless
     *         both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        // merge smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeightedQuickUnionUF{");
        sb.append("parent=").append(Arrays.toString(parent));
        sb.append(", size=").append(Arrays.toString(size));
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
