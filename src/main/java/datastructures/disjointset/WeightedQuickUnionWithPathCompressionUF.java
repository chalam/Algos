package datastructures.disjointset;

import java.util.Arrays;

/**
 * Just after computing the root of p, set the id of each examined note to
 * point to that of root
 * root has multiple subtrees and tree is almost flat
 *
 * init = N
 * union = lg N
 * find = lg N
 * total = N + M log* N (M - uf ops on N objects)
 * Inverse Ackermann function (<5 for whole universe)
 * Created by Lamuel on 4/27/2016.
 */
public class WeightedQuickUnionWithPathCompressionUF extends WeightedQuickUnionUF {
    public WeightedQuickUnionWithPathCompressionUF(int N){
        super(N);
    }

    /**
     * find the root and compress path
     * @param i
     * @return
     */
    protected int root(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];  // one line makes it log*
            i = parent[i];
        }
        return i;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeightedQuickUnionWithPathCompressionUF{");
        sb.append("parent=").append(Arrays.toString(parent));
        sb.append(", size=").append(Arrays.toString(size));
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
