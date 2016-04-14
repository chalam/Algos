package datastructures.search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Binary Search Tree
 * Created by Lamuel on 4/10/2016.
 */
public class BinarySearchTreeTest {
    @Test
    public void doBST() {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        assertTrue("Symbol table lookup failed", bst.isEmpty());

        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int N = keys.length;
        for (int i = 0; i < N; i++)
            bst.put(keys[i], i);


        System.out.printf("\nsize = %d", bst.size());
        System.out.printf("\nmin = %s", bst.min());
        System.out.printf("\nmax = %s", bst.max());
        System.out.printf("\nheight = %s", bst.height());
        System.out.printf("\nlevelOrder = %s\n", bst.levelOrder());

        // print keys in order using allKeys()
        System.out.println("Testing keys()");
        System.out.println("--------------------------------");
        for (String s : bst.keys())
            System.out.println(s + " " + bst.get(s));
        System.out.println();

        // print keys in order using select
        System.out.println("Testing select");
        System.out.println("--------------------------------");
        for (int i = 0; i < bst.size(); i++)
            System.out.println(i + " " + bst.select(i));
        System.out.println();

        // test rank, floor, ceiling
        System.out.println("key rank floor ceil");
        System.out.println("-------------------");
        for (char i = 'A'; i <= 'Z'; i++) {
            String s = i + "";
            System.out.printf("%2s %4d %4s %4s\n", s, bst.rank(s), bst.floor(s), bst.ceiling(s));
        }
        System.out.println();

        // test range search and range count
        String[] from = { "A", "Z", "X", "0", "B", "C" };
        String[] to   = { "Z", "A", "X", "Z", "G", "L" };
        System.out.println("range search");
        System.out.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            System.out.printf("%s-%s (%2d) : ", from[i], to[i], bst.size(from[i], to[i]));
            for (String s : bst.keys(from[i], to[i]))
                System.out.print(s + " ");
            System.out.println();
        }
        System.out.println();

        System.out.println("BST now");
        System.out.println("--------------------------------");
        for (String s : bst.keys())
            System.out.println(s + " " + bst.get(s));
        System.out.println();

        // delete the smallest keys
        for (int i = 0; i < bst.size() / 2; i++) {
            bst.deleteMin();
        }
        System.out.println("After deleting the smallest " + bst.size() / 2 + " keys");
        System.out.println("--------------------------------");
        for (String s : bst.keys())
            System.out.println(s + " " + bst.get(s));
        System.out.println();

        // delete the largest keys
        for (int i = 0; i < bst.size() / 2; i++) {
            bst.deleteMax();
        }

        System.out.println("After deleting the largest " + bst.size() / 2 + " keys");
        System.out.println("--------------------------------");
        for (String s : bst.keys())
            System.out.println(s + " " + bst.get(s));
        System.out.println();

        // delete all the remaining keys
//        while (!bst.isEmpty()) {
//            bst.delete(bst.select(bst.size() / 2));
//            bst.deleteMin();
//        }

        System.out.println("After deleting the remaining keys");
        System.out.println("--------------------------------");
        for (String s : bst.keys())
            System.out.println(s + " " + bst.get(s));
        System.out.println();

        System.out.println("After adding back N keys");
        System.out.println("--------------------------------");
        for (int i = 0; i < N; i++)
            bst.put(keys[i], i);
        for (String s : bst.keys())
            System.out.println(s + " " + bst.get(s));
        System.out.println();
        
        assertEquals("Symbol table lookup failed", new Integer(8), bst.get("A"));
        assertEquals("Symbol table lookup failed", new Integer(5), bst.get("H"));

        assertEquals("Symbol table lookup failed", true, bst.contains("R"));
        assertEquals("Symbol table lookup failed", false, bst.contains("T"));

        bst.delete("A");
        assertEquals("Symbol table lookup failed", null, bst.get("A"));

        bst.put("H", 10);
        assertEquals("Symbol table lookup failed", new Integer(10), bst.get("H"));

        assertEquals("Symbol table lookup failed", 9, bst.size());

        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());

    }
}