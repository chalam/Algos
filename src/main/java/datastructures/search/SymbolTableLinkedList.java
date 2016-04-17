package datastructures.search;

import datastructures.queue.MyQueue;

/**
 * Sequential Search in unordered linked list
 * Search
 *  Worst = N
 *  Avg   = N/2
 * Insert
 *  Worst = N
 *  Avg   = N
 * Unordered
 * Cost Ops = equals()
 *
 * Created by Lamuel on 3/29/2016.
 */

public class SymbolTableLinkedList<Key, Value> {

    /**
     * Linked List helper
     */
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("key=").append(key);
            sb.append(", val=").append(val);
            sb.append(", next=").append(next);
            sb.append('}');
            return sb.toString();
        }
    }

    private int N;      //Size
    private Node first;

    /**
     * Init
     */
    public SymbolTableLinkedList() {
    }

    /**
     * get # of K, V
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Is the table empty
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return tree if the key exist in symbol table
     * @param key
     * @return true if key exist
     * @throws NullPointerException if key is null
     */
    public boolean contains(Key key) {
        if (key == null)
            throw new NullPointerException("Key should not be null");

        return get(key) != null;
    }

    /**
     * Get the value associated to the given key
     * @param key
     * @return val if key is found, else null
     * @throws NullPointerException if key is null
     */
    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("Key should not be null");

        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key))
                return node.val;
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table,
     * if key exist, overwrites the existing value
     * if key not found, add a new node and re-point first
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("Key should not be null");

        if (val == null) {
            delete(key);
            return;
        }

        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                node.val = val; // overwritten
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }

    /**
     * Removes the key and value
     * @param key
     */
    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("Key should not be null");

        first = delete(first, key);
    }

    /**
     * Removes the key and value
     * @param key
     */
    public Node delete(Node node, Key key) {
        if (key == null)
            throw new NullPointerException("Key should not be null");
        if (node == null)
            return null;
        if (key.equals(node.key)) {
            N--;
            return node.next;
        }
        node.next = delete(node.next, key);
        return node;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SymbolTableLinkedList{");
        sb.append("N=").append(N);
        sb.append(", first=").append(first);
        sb.append('}');
        return sb.toString();
    }
}
