package datastructures.search;

import java.util.NoSuchElementException;

/**
 * Symbol Table impl with Binary Search Tree BST
 * Combines the flexibility of insertion in linked lists with the efficiency of search in an ordered array.
 * Search
 *  Guarantee = N
 *  Expected = 1.39 lg N
 * Insert
 *  Guarantee = N
 *  Expected  = 1.39 lg N
 * height
 *  Expected  = ~4.311 ln N
 *  worst     = N
 * Ordered by next()
 * Cost Ops = compareTo()
 * Created by Lamuel on 3/30/2016.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;  // root of BST

    private class Node {
        private Key key;    // sorted by key
        private Value val;  // assoc data
        private Node left;  // left subtree
        private Node right; // right subtree
        private int N;      // number of nodes in subtree

        public Node(Key k, Value v, int N) {
            this.key = k;
            this.val = v;
            this.N   = N;
        }
    }

    /**
     * init
     */
    public BinarySearchTree() {
    }


    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     *  number of key-value pairs in BST rooted at x
     * @param x
     * @return
     */
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     * Get the val for give key
     * #Compares = 1 + depth
     * @param key
     * @return
     */
    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");

        Node x = root;
        while (x != null) {
            int comp = key.compareTo(x.key);
            if      (comp < 0) x = x.left;
            else if (comp > 0) x = x.left;
            else               return x.val;
        }
        return null;
    }

    /**
     * Add key val to BST
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    /**
     * walk down the tree for the right node
     * #Compares = 1 + depth
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);

        int comp = key.compareTo(x.key);
        if      (comp < 0)
            x.left = put(x.left, key, val);
        else if (comp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;

        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Delete the given key from BST
     * @param key
     */
    public void delete(Key key) {

    }

    /**
     * traverse the BST
     * @return
     */
    public Iterable<Key> iterator() {
        return null;
    }
}
