package datastructures.hash;

import datastructures.search.SymbolTableLinkedList;

/**
 * Hash function convert key into array indices
 * Hash Collision resolution by Chaining with LinkedList
 * Easier Implementation
 * Performance degrade gracefully
 * Clustering less sensitive to poor hash function
 * Worst
 *  Search = < lg N
 *  insert = < lg N
 * Average
 *  Search = N/2M 3.5 when M = N/5
 *  insert = N/M  3.5 when M = N/5
 *
 * average list length = N/M
 *
 * Ref java.util.HashMap = key1.equals(key2)
 * Created by Lamuel on 4/11/2016.
 */
public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 5;

    private int N;  // number of Key, Value pairs
    private int M;  // hash table size

    SymbolTableLinkedList<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SymbolTableLinkedList<Key, Value>[]) new SymbolTableLinkedList[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SymbolTableLinkedList<Key, Value>();
        }
    }

    /**
     * convert hashcode to postive 31 bit and mod to hast table
     *
     * @param key
     * @return hash value between 0 and M-1
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return N;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt>;
     *         <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Get the value assoc with key
     * @param key
     * @return
     */
    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    /**
     * insert key val to st
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, value);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param  key the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);
    }
}
