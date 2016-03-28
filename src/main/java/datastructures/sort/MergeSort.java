package datastructures.sort;

import java.util.Arrays;

/**
 * John von Neumann, 1948
 * First divide the list into the smallest unit (1 element), then compare each element
 * with the adjacent list to sort and merge the two adjacent lists.
 * Finally all the elements are sorted and merged.
 *
 * worst = N lg N
 * avg   = N lg N
 * best  = N lg N
 * space = 2N (N + extra N for aux)
 *
 * cost model comparisons = 1/2 N lg N, optimized to N
 * array access = 6 N lg N
 * stable = yes
 * adaptive = yes
 *
 * Created by Lamuel on 3/26/2016.
 */
public class MergeSort<T extends Comparable<T>> implements Sortable<T> {

    /**
     * sort method driver
     *
     * @param data
     */
    @Override
    public void sort(T[] data) {

        // Optimization: Cutoff to Insertion for tiny array N < 7

        T[] aux = (T[]) new Comparable[data.length];
        mergeSort(data, aux, 0, data.length-1);
    }

    /**
     * recursively sort data subarray
     * @param data
     * @param aux
     * @param lo
     * @param hi
     */
    public void mergeSort(T[] data, T[] aux, int lo, int hi) {
        // termination cond on equals for one element subarray
        if (hi <= lo)
            return;

        int mid = lo + (hi - lo) / 2;
        mergeSort(data, aux, lo, mid);
        mergeSort(data, aux, mid+1, hi);

        // Optimization: stop merge if already sorted
        // if biggest of first half <= smallest item in second half
        if (!less(data[mid+1], data[mid]))
            return;

        merge(data, aux, lo, mid, hi);
    }
    /**
     * merge array with sorted subarray in [lo..mid], [mid+1..hi]
     * @param data actual array
     * @param aux  temp array holding sorted subarry
     * @param lo
     * @param mid
     * @param hi
     */
    public void merge(T[] data, T[] aux, int lo, int mid, int hi) {
        assert isSorted(copyArray(data, lo, mid));
        assert isSorted(copyArray(data, mid + 1, hi));

        // copy to aux
        for (int k = lo; k <= hi; k++) {
            aux[k] = data[k];
        }
//        aux = copyArray(data, lo, hi);

        int i = lo;
        int j = mid+1;
        // k in [lo, hi]
        for(int k = lo; k <= hi; k++){
            if (i > mid) {
                // when i runs out, copy from other side
                data[k] = aux[j++];
            } else if (j > hi) {
                // when j runs out, copy from other side
                data[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                // copy the less of two
                data[k] = aux[j++];
            } else {
                // copy the greater of two
                data[k] = aux[i++];
            }
        }

        assert isSorted(copyArray(data, lo, hi));
    }

    public T[] copyArray(T[] a, int lo, int hi) {
        return Arrays.copyOfRange(a, lo, hi);
    }

    /**
     * bottom up sort data subarray without recursion
     * repeat with subarray heapSize = 1, 2, 4, 8...
     * @param data
     */
    public void mergeSortBottomUp(T[] data) {
        int N = data.length;
        T[] aux = (T[]) new Comparable[N];

        // double heapSize of subarray
        for (int sz = 1; sz < N; sz = sz+sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(data, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }
}
