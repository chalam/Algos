package datastructures.search;

import datastructures.queue.MyQueue;
import datastructures.queue.MyQueueLinkedList;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Symbol Table impl on ordered Array
 * Search
 *  Worst = log N
 *  Avg   = log N
 * Insert
 *  Worst  = N (shift to right)
 *  Avg  = N/2
 * Ordered
 * Cost Ops = compareTo()
 * Created by Lamuel on 3/30/2016.
 */

public class SymbolTableOrderedArray<Key extends Comparable<Key>, Value> {
    private int INIT_CAPACITY = 2;
    private int N = 0;      //Size
    private Key[] keys;
    private Value[] vals;

    /**
     * Init
     */
    public SymbolTableOrderedArray() {
        keys = (Key[]) new Comparable[INIT_CAPACITY];
        vals = (Value[]) new Object[INIT_CAPACITY];
    }

    public SymbolTableOrderedArray(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
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
        if(isEmpty())
            return null;
        if (key == null)
            throw new NullPointerException("Key should not be null");

        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else 
            return null;
    }

    /**
     * get the largest index smaller than the key
     * Impl Binary Search
     * log N
     * @param key
     * @return
     */
    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else              return mid;
        }
        return lo;
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

        int i = rank(key);

        // keys exists in the table, overwrite val
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        if (N == keys.length) {
            resize(2 * keys.length);
        }

        // move all key vals greater than this to the right
        for(int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        //insert the new key val
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    /**
     * increase the array size
     * @param capacity
     */
    private void resize(int capacity) {
        assert capacity >= N;
        Key[] newKeys = (Key[]) new Comparable[capacity];
        Value[] newVals  = (Value[]) new Object[capacity];
        newKeys = Arrays.copyOf(keys, newKeys.length);
        newVals = Arrays.copyOf(vals, newVals.length);
        keys    = newKeys;
        vals    = newVals;
    }

    /**
     * Removes the key and value
     * @param key
     */
    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("Key should not be null");
        if(isEmpty())
            return;

        int i = rank(key);
        // key not in table
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }

        // move all key vals greater than this to the left
        for (int j = i; j < N-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        keys[N] = null;
        vals[N] = null;
        N--;

        // resize if 1/4 full
        if (N > 0 && N == keys.length/4)
            resize(keys.length/2);
    }



    /***************************************************************************
     *  Ordered symbol table methods.
     ***************************************************************************/
    /**
     * Returns the smallest key in this symbol table.
     * @return smallest of ST
     */
    public Key min() {
        if (isEmpty())
            return null;
        return keys[0];
    }

    /**
     * Returns the largest key in this symbol table.
     * @return largest of ST
     */
    public Key max() {
        if (isEmpty())
            return null;
        return keys[N-1];
    }

    /**
     * Return the kth smallest key in this symbol table.
     *
     * @param  k the order statistic
     * @return the kth smallest key in this symbol table
     * @throws IllegalArgumentException unless <tt>k</tt> is between 0 and
     *        <em>N</em> &minus; 1
     */
    public Key select(int k) {
        if (k < 0 || k >= N) return null;
        return keys[k];
    }

    /**
     * Returns the largest key in this symbol table less than or equal to <tt>key</tt>.
     *
     * @param  key the key
     * @return the largest key in this symbol table less than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("argument to floor() is null");
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];
    }

    /**
     * Returns the smallest key in this symbol table greater than or equal to <tt>key</tt>.
     *
     * @param  key the key
     * @return the smallest key in this symbol table greater than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("argument to ceiling() is null");
        int i = rank(key);
        if (i == N) return null;
        else return keys[i];
    }

    /**
     * Returns the number of keys in this symbol table in the specified range.
     *
     * @return the number of keys in this sybol table between <tt>lo</tt>
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
     * Returns all keys in this symbol table as an <tt>Iterable</tt>.
     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Returns all keys in this symbol table in the given range,
     * as an <tt>Iterable</tt>.
     *
     * @return all keys in this symbol table between <tt>lo</tt>
     *         (inclusive) and <tt>hi</tt> (exclusive)
     * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
     *         is <tt>null</tt>
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new NullPointerException("first argument to size() is null");
        if (hi == null) throw new NullPointerException("second argument to size() is null");

        MyQueue<Key> queue = new MyQueueLinkedList<>();
        // if (lo == null && hi == null) return queue;
        if (lo == null) throw new NullPointerException("lo is null in keys()");
        if (hi == null) throw new NullPointerException("hi is null in keys()");
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enQueue(keys[i]);
        if (contains(hi)) queue.enQueue(keys[rank(hi)]);
        return queue;
    }
}
