package datastructures.heap;

/**
 * HeapSort with Binary Heap
 * Binary Heap impl with fixed arrays based on Sedgewick
 * Created by Lamuel on 3/27/2016.
 */
public class HeapSort {

    private HeapSort() {}

    /**
     * Rearranges the array in ascending order, using the natural order.
     * Compares = 2N
     * Exchanges < N
     *
     * @param pq the unsorted array
     */
    public static void sort(Comparable[] pq) {
        // build a heap
        int N = pq.length;

        // Construct the heap
        // scan from middle to 1, since second half is 1 element heap leaves
        for (int k = N / 2; k >= 1; k--) {
            sink(pq, k, N);
        }

        // sortdown
        while (N > 1) {
            exch(pq, 1, N--); // remove the max off heap
            sink(pq, 1, N);   // float down new root
        }
    }


    /**
     * Helper functions to restore the heap invariant.
     * @param pq
     * @param k
     * @param N
     */
    private static void sink(Comparable[] pq, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     *
     ***************************************************************************/
    /**
     * Compare less =  MaxPriorityQueue
     * Indices are "off-by-one" to support 1-based indexing.
     * @param pq
     * @param i
     * @param j
     * @return
     */
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    /**
     * Swap func
     * Indices are "off-by-one" to support 1-based indexing.
     * @param pq
     * @param i
     * @param j
     */
    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    /**
     * is v < w ?
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    /**
     * Check if array is sorted - useful for debugging.
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
}
