package datastructures.disjointset;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 4/27/2016.
 */
public class QuickFindUFTest {
    @Test
    public void doUF() throws Exception {
        UnionFind uf = new QuickFindUF(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(8, 9);
        uf.union(5 ,0);
        uf.union(7 ,2);
        uf.union(6, 1);
        uf.union(1 ,0);
        uf.union(6 ,7);
        System.out.println(uf);
        assertTrue(uf.isConnected(0, 5));
        assertTrue(uf.isConnected(3, 9));
        assertFalse(uf.isConnected(0, 9));
        assertFalse(uf.isConnected(2, 4));
        assertEquals("Count of Components", 2, uf.count());
    }
}