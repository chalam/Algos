package datastructures.search;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Symbol Table impl with Binary Search Tree BST
 * Binary Search Tree is order symbol table of generic key val
 * Unbalanced BST
 * Combines the flexibility of insertion in linked lists with the efficiency of search in an ordered array.
 * All cost of BST is height h = lg N amortized
 * http://algs4.cs.princeton.edu/32bst/
 *
 * Search
 *  Guarantee = N
 *  Expected = 1.39 lg N
 * Insert
 *  Guarantee = N
 *  Expected  = 1.39 lg N
 * Delete
 *  Guarantee = N
 *  Expected  = Square root of N
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
        private Key   key;   // sorted by key
        private Value val;   // assoc data
        private Node  left;  // left subtree
        private Node  right; // right subtree
        private int   N;     // number of nodes in current node's subtree

        public Node(Key k, Value v, int N) {
            this.key = k;
            this.val = v;
            this.N   = N;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("key=").append(key);
            sb.append(", val=").append(val);
            sb.append(", left=").append(left);
            sb.append(", right=").append(right);
            sb.append(", N=").append(N);
            sb.append('}');
            return sb.toString();
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
        else           return x.N;
    }

    /**
     * Returns true if this symbol table is empty.
     * @return <tt>true</tt> if this symbol table is empty; <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
     *         <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("argument to contains() is null");
        return get(key) != null;
    }

    /*************************************************************************
     *  Basic Ops of BST data structure.
     ***************************************************************************/

    /**
     * Get the val for give key
     * #Compares = 1 + depth
     * @param key the key
     * @return
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("Key cannot be null");

        Node x = root;
        while (x != null) {
            int comp = key.compareTo(x.key);
            if      (comp < 0) x = x.left;
            else if (comp > 0) x = x.right;
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
        if (key == null) throw new NullPointerException("first argument to put() is null");

        if (val == null) {
            delete(key);    // soft delete for put key with null val
            return;
        }
        root = put(root, key, val);
        assert check();
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
            return new Node(key, val, 1);   // new leaf node

        int comp = key.compareTo(x.key);
        if      (comp < 0) x.left  = put(x.left,  key, val);
        else if (comp > 0) x.right = put(x.right, key, val);
        else               x.val   = val;

        // update the size
        x.N = 1 + size(x.left) + size(x.right); // add one for self, then left, then right
        return x;
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        assert check();
    }

    /**
     *
     * @param x
     * @return
     */
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Delete the given key from BST
     * @param key
     */
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to delete() is null");
        root = delete(root, key);
        assert check();
    }

    /**
     * Hibbard deletion
     * Choose the next larger key as the replacement
     * Replace the key to be deleted with this next larger key.
     * Delete the replacement key from the subtree where it was located.
     * @param x
     * @param key
     * @return
     */
    private Node delete(Node x, Key key){
        if (x == null) return null; // k not in the tree

        // Find the node to delete: the one containing k
        int comp = key.compareTo(x.key);
        if      (comp < 0) x.left  = delete(x.left,  key);
        else if (comp > 0) x.right = delete(x.right, key);
        else { // we found it. Now delete t
            // So we are in case 1 with one child
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;

            // case 2 with two children - hibbard deletion
            // get the next larger key or previous smaller key
            Node t = x;             // save the node to be deleted
            x = min(t.right);       // find the successor in right subtree
            x.right = deleteMin(t.right); // replace right with the new right subtree
            x.left  = t.left;             // replace left with the old left subtree
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /*************************************************************************
     *  Ordered Operations
     ***************************************************************************/
    /**
     * Returns the smallest key in the symbol table.
     * get the left most node
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    /**
     * Returns the largest key in the symbol table.
     * get the right most node
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    /**
     * Returns the largest key in the symbol table less than or equal to <tt>key</tt>.
     *
     * @param key the key
     * @return the largest key in the symbol table less than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("argument to floor() is null");
        if (isEmpty())   throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(this.root, key);
        if (x == null) return null;
        else           return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        if (comp == 0) return x;
        if  (comp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to <tt>key</tt>.
     *
     * @param  key the key
     * @return the smallest key in the symbol table greater than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("argument to ceiling() is null");
        if (isEmpty())   throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    /**
     *
     * @param x
     * @param key
     * @return
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }

    /**
     * Return the kth smallest key in the symbol table.
     *
     * @param  k the order statistic
     * @return the kth smallest key in the symbol table
     * @throws IllegalArgumentException unless <tt>k</tt> is between 0 and
     *        <em>N</em> &minus; 1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        Node x = select(root, k);
        return x.key;
    }

    /**
     * Return key of rank k.
     * @param x
     * @param k
     * @return
     */
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if      (t > k) return select(x.left,  k);
        else if (t < k) return select(x.right, k-t-1);
        else            return x;
    }

    /**
     * Return the number of keys in the symbol table strictly less than <tt>key</tt>.
     *
     * @param  key the key
     * @return the number of keys in the symbol table strictly less than <tt>key</tt>
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public int rank(Key key) {
        if (key == null) throw new NullPointerException("argument to rank() is null");
        return rank(key, root);
    }

    /**
     * Number of keys in the subtree less than key.
     * @param key
     * @param x
     * @return
     */
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
    }

    /**
     * Returns all keys in the symbol table as an <tt>Iterable</tt>.
     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
     *
     * @return all keys in the symbol table
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an <tt>Iterable</tt>.
     *
     * @return all keys in the sybol table between <tt>lo</tt>
     *         (inclusive) and <tt>hi</tt> (exclusive)
     * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
     *         is <tt>null</tt>
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new NullPointerException("first argument to keys() is null");
        if (hi == null) throw new NullPointerException("second argument to keys() is null");

        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)                keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0)                keys(x.right, queue, lo, hi);
    }

    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @return the number of keys in the sybol table between <tt>lo</tt>
     *         (inclusive) and <tt>hi</tt> (exclusive)
     * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
     *         is <tt>null</tt>
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new NullPointerException("first argument to size() is null");
        if (hi == null) throw new NullPointerException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

    /**
     * Returns the height of the BST (for debugging).
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }

    /**
     *
     * @param x
     * @return
     */
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * Returns the keys in the BST in level order (for debugging).
     *
     * @return the keys in the BST in level order traversal
     */
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new LinkedList<Key>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node x = queue.remove();
            if (x == null) continue;
            keys.add(x.key);
            queue.add(x.left);
            queue.add(x.right);
        }
        return keys;
    }

    /*************************************************************************
     *  Check integrity of BST data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST())            System.out.println("Not in symmetric order");
        if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
        if (!isRankConsistent()) System.out.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
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

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BinarySearchTree{");
        sb.append("root=").append(root);
        sb.append('}');
        return sb.toString();
    }
}
