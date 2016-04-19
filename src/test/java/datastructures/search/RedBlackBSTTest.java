package datastructures.search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 4/10/2016.
 */
public class RedBlackBSTTest {
    @Test
    public void doRBTree() {
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();
        assertTrue("Symbol table lookup failed", bst.isEmpty());

        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int N = keys.length;
        for (int i = 0; i < N; i++)
            bst.put(keys[i], i);

        // print keys in order using allKeys()
        System.out.println("Testing keys()");
        System.out.println("--------------------------------");
        for (String s : bst.keys())
            System.out.println(s + " " + bst.get(s));
        System.out.println();


        System.out.printf("\nsize = %d", bst.size());
//        System.out.printf("\nmin = %s", bst.min());
//        System.out.printf("\nmax = %s", bst.max());
        System.out.printf("\nheight = %s", bst.height());
//        System.out.printf("\nlevelOrder = %s\n", bst.levelOrder());

        assertEquals("Symbol table lookup failed", new Integer(8), bst.get("A"));
        assertEquals("Symbol table lookup failed", new Integer(5), bst.get("H"));

        assertEquals("Symbol table lookup failed", true, bst.contains("R"));
        assertEquals("Symbol table lookup failed", false, bst.contains("T"));

//        bst.delete("A");
//        assertEquals("Symbol table lookup failed", null, bst.get("A"));

        bst.put("H", 10);
        assertEquals("Symbol table lookup failed", new Integer(10), bst.get("H"));

//        assertEquals("Symbol table lookup failed", 9, bst.size());
    }
}