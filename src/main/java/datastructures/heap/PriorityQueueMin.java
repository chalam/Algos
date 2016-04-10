package datastructures.heap;

/**
 * Binary Heap impl with fixed arrays based on Sedgewick
 * Ref java.util.PriorityQueue;
 * Created by Lamuel on 3/28/2016.
 */
public class PriorityQueueMin<E extends Comparable<E>> {
    private final E[] pq;
    private int N = 0; //size of PQ

    public PriorityQueueMin(int capacity) {
        pq = (E[]) new Comparable[capacity+1]; // idx = 0 is not used
    }

    /**
     * is PQ empty
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Get Size of Heap
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Get MIN of Heap
     * @return min
     */
    public E min() {
        return pq[1];
    }

    /**
     * Get the parent node of idx i
     * @param i
     * @return
     */
    public static int parent(int i) {
        return (int) Math.floor(i/2);
    }

    /**
     * et the left node of idx i
     * @param i
     * @return
     */
    public static int left(int i) {
        return (2 * i);
    }

    /**
     * et the left node of idx i
     * @param i
     * @return
     */
    public static int right(int i) {
        return (2 * i) + 1;
    }

    /**
     * is left > right
     * @param left
     * @param right
     * @return boolean
     */
    private boolean greater(E left, E right) {
        return left.compareTo(right) > 0;
    }

    /**
     * swap index i with j on data array
     *
     * @param data
     * @param i from
     * @param j to
     */
    void swap(E[] data, int i, int j) {
//        System.out.printf("\nSwapping %s with %s", data[i], data[j]);
        E temp  = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    /**
     * swim up heapify, maintain in the heap property
     * log N
     * @param k
     */
    public void swim(int k) {
        while (k > 1 && greater(pq[parent(k)], pq[k])) {
            swap(pq, parent(k), k);
            k = parent(k);
        }
    }


    /**
     * sink heapify to the bottom whether the node fits in
     * log N
     * @param k
     */
    public void sink(int k) {
        while (left(k) <= N) {
            int maxOfSibling = left(k);
            if (maxOfSibling < N && greater(pq[left(k)], pq[right(k)]))
                maxOfSibling = right(k);
            if (!greater(pq[k], pq[maxOfSibling]))
                break;

            swap(pq, k, maxOfSibling);
             k = maxOfSibling;
        }
    }

    /**
     * Add the new key to the end and float it up
     * log N
     * @param key
     */
    public void insert(E key) {
        pq[++N] = key;
        swim(N);
    }

    /**
     * remove the next max from the heap
     * log N
     * @return
     */
    public E deleteMax() {
        E max = pq[1]; // retrive max at idx 1
        swap(pq, 1, N); // exch with last
        N--;            // reduce PQ size
        pq[N+1] = null; // avoid loitering for GC
        sink(1);
        return max;
    }
}
