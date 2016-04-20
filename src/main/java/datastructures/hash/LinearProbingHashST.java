package datastructures.hash;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Linear Probing
 * Open addressing strategy
 * Map key to int i in (0, m-1)
 * Put at table if index i is free else try i+1. i+2 until empty space
 * Less waster space (load factor 70%)  = N/M
 * Better Caching
 * Worst
 *  Search = c lg N
 *  insert = c lg N
 * Average
 *  Search = < 1.50
 *  insert = < 2.50
 * average list length = N/M
 * * Ref java.util.IdentityHashMap = special cash with key1 == key2
 * Created by Lamuel on 4/12/2016.
 */
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private int N; // number of key-val pairs in ST
    private int M; // size of linear probing table
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        M      = capacity;
        keys   = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
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
     * Returns true if this symbol table is empty.
     *
     * @return <tt>true</tt> if this symbol table is empty;
     *         <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
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
     * Convert hash to int and mod down to size of table
     * hash function for keys - returns value between 0 and M-1
     * @param key
     * @return
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * resizes the hash table to the given capacity by re-hashing all of the keys
     * @param capacity
     */
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        M    = temp.M;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is <tt>null</tt>.
     *
     * @param  key the key
     * @param  val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = val;
                return;
            }
        }
        keys[i]   = key;
        values[i] = val;
        N++;
    }

    /**
     * Returns the value associated with the specified key.
     * @param key the key
     * @return the value associated with <tt>key</tt>;
     *         <tt>null</tt> if no such value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return values[i];
        return null;
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
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        // delete key and associated value
        keys[i]   = null;
        values[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % M;
        while (keys[i] != null) {
            // delete keys[i] an values[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = values[i];
            keys[i]   = null;
            values[i] = null;
            N--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }

        N--;

        // halves size of array if it's 12.5% full or less
        if (N > 0 && N <= M/8) resize(M/2);

        assert check();
    }

    /**
     * Returns all keys in this symbol table as an <tt>Iterable</tt>.
     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
     *
     * @return all keys in this sybol table
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.add(keys[i]);
        return queue;
    }

    // integrity check - don't check after each put() because
    // integrity not maintained during a delete()
    private boolean check() {

        // check that hash table is at most 50% full
        if (M < 2*N) {
            System.err.println("Hash table size M = " + M + "; array size N = " + N);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < M; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != values[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; values[i] = " + values[i]);
                return false;
            }
        }
        return true;
    }
}
