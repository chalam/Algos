package datastructures.sort;

import java.util.Arrays;

/**
 * Based on QuickSort by C.A.R. Hoare
 * Given an array of N, find a kth smallest item
 * min(k=0), max(k=N-1) and median(k=N/2)
 * order stats or find the top k
 *
 * worst = 1/2 N^2
 * avg = N linear
 * compares ~ 1/2 N^2
 * Created by Lamuel on 3/27/2016.
 */
public class QuickSelect<T extends Comparable<T>> extends QuickSort<T> {

    /**
     * find the kth item on the array
     * @param a data array
     * @param k kth item to be found
     * @return
     */
    public T select(T[] a, int k) {

        // Avg case guarantee for N
        Shuffler shuffler = new Shuffler();
        shuffler.shuffle(a);

        System.out.printf("\nAfter Shuffle: %s", Arrays.toString(a));
        int lo = 0;
        int hi = a.length-1;

        while (hi > lo) {

            // quick sort partition so that a[j] is in place
            // lo <= j >= hi
            int j = partition(a, lo, hi);

            // repeat partition in one subarray depending on j
            if      (j < k)     lo = j + 1;     // right subarray
            else if (j > k)     hi = j - 1;     // left subarray
            else                return a[k];    // bingo: j = k

        }
        return a[k];
    }
}
