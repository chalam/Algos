package datastructures.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Balanced BST with cost to be guaranteed logarithmic
 * Our trees have near-perfect balance, where the height is guaranteed to be no larger than 2 lg N.
 * 2-3 search tree => Red Black Tree => B-Tree
 * Encoding 3-nodes with RED color link
 * Left Leaning Red Black Tree LLRB Tree
 *
 * Ref java.util.TreeMap, java.util.TreeSet
 *
 * http://algs4.cs.princeton.edu/33balanced/
 * <p>
 * Search
 *  worst = 2 lg N
 *  avg   = 1 lg N
 * Insert
 *  worst = 2 lg N
 *  avg   = 1 lg N
 * Delete
 *  worst = 2 lg N
 *  avg   = 1 lg N
 * height
 *  Expected  = <= 2 ln N
 *  worst     = N
 * Ordered by next()
 * Cost Ops = compareTo()
 * Created by Lamuel on 4/10/2016.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;  // root of BST

    private class Node {
        private Key key;   // sorted by key
        private Value val;   // assoc data
        private Node left;  // left subtree
        private Node right; // right subtree
        private boolean color;  //color of the parent link
        private int N;     // number of nodes in current node's subtree

        public Node(Key k, Value v, boolean color, int N) {
            this.key = k;
            this.val = v;
            this.color = color;
            this.N = N;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("key=").append(key);
            sb.append(", val=").append(val);
            sb.append(", left=").append(left);
            sb.append(", right=").append(right);
            sb.append(", color=").append(color);
            sb.append(", N=").append(N);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public RedBlackBST() {
    }

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    /**
     * is node x red; false if x is null ?
     *
     * @param x
     * @return
     */
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    /**
     * number of node in subtree rooted at x; 0 if x is null
     *
     * @param x
     * @return
     */
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }


    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Is this symbol table empty?
     *
     * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     * and <tt>null</tt> if the key is not in the symbol table
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
     * <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }


    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is <tt>null</tt>.
     *
     * @param key
     * @param val
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("argument to put() is null");
//        if (val == null) {
//            delete(key);
//            return;
//        }
        root = put(root, key, val);
        root.color = BLACK;
        check();
    }

    /**
     * insert the key-value pair in the subtree rooted at h
     * @param h
     * @param key
     * @param val
     * @return
     */
    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED, 1); // Always RED new node

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else              h.val   = val;

        h = balance(h);
        return h;
    }


    //



    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/
    /**
     * make a left-leaning link lean to the right
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);

        Node x  = h.left;
        h.left  = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.N     = h.N;
        h.N     = 1 + size(h.left) + size(h.right);

        return x;
    }

    /**
     *  make a right-leaning link lean to the left
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        Node x  = h.right;
        h.right = x.left;
        x.left  = h;

        x.color = h.color;
        h.color = RED;

        x.N     = h.N;
        h.N     = 1 + size(h.left) + size(h.right);

        return x;
    }

    /**
     * flip the colors of a node and its two children
     * move the RED link up
     * @param h
     */
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
                || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color       = !h.color;
        h.left.color  = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * Assuming that h is red and both h.left and h.left.left
     * are black, make h.left or one of its children red.
     * @param h
     * @return
     */
    private Node moveRedLeft(Node h) {
        return null;
    }

    /**
     * Assuming that h is red and both h.right and h.right.left
     * are black, make h.right or one of its children red.
     * @param h
     * @return
     */
    private Node moveRedRight(Node h) {
        return null;
    }

    /**
     * restore red-black tree invariant
     * The first rotates left any right-leaning 3-node (or a right-leaning red link at the bottom of a temporary 4-node); the
     * second rotates right the top link in a temporary 4-node with two left-leaning red links;
     * and the third flips colors to pass a red link up the tree
     * @param h
     * @return
     */
    private Node balance(Node h) {
        assert (h != null);

        // fix up any right leaning links
        // case 1. left is BLACK, right is RED
        if (isRed(h.right) && !isRed(h.left))       h = rotateLeft(h);
        // case 2. two consecutive left RED
        if (isRed(h.left)  && isRed(h.left.left))   h = rotateRight(h);
        // case 3. both left and right is RED
        if (isRed(h.left)  && isRed(h.right))       flipColors(h);

//        if (isRed(h.right))                      h = rotateLeft(h);
//        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
//        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        // recalc the size
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    /***************************************************************************
     *  Utility functions.
     ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /***************************************************************************
     *  Iterate using an inorder traversal.
     *  Iterating through N elements takes O(N) time.
     ***************************************************************************/
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue) {
        if (x == null) return;
        keys(x.left, queue);
        queue.add(x.key);
        keys(x.right, queue);
    }


    /***************************************************************************
     *  Check integrity of red-black tree data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST())            System.out.println("Not in symmetric order");
        if (!is23())             System.out.println("Not a 2-3 tree");
        if (!isBalanced())       System.out.println("Not balanced");
        return isBST() && is23() && isBalanced();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }
    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

}