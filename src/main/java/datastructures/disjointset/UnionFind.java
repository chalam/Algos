package datastructures.disjointset;

/**
 * Interface for Dynamic connectivity or Disjoint data structure
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 * Created by Lamuel on 4/27/2016.
 */
public interface UnionFind {


    /**
     * Add connection b/w p and q
     * @param p
     * @param q
     */
    public void union(int p, int q);

    /**
     * component id for p (0 to N-1)
     * @param p
     * @return
     */
    public int find(int p);

    /**
     * returns tree of p and q are in the same component
     * @param p
     * @param q
     * @return
     */
    public boolean isConnected(int p, int q);

    /**
     * number of component
     * @return
     */
    public int count();
}
