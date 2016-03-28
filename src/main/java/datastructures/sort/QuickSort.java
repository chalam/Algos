package datastructures.sort;

import java.util.Arrays;

/**
 * Charles Antony Richard Hoare, 1961
 * Shuffle the array
 * Partition so that, for some J
 *   entry a[j] is in place (pivot)
 *   left subarray no larger than a[j]
 *   right subarray no small than a[j]
 * Sort each subarray recursively,
 * unlike mergesort, quicksort use recursion after the work
 *
 * worst = 1/2 N^2 where pivot is lowest
 * avg   = 2(N+1) ln N ~ 1.39 N lg N
 * best  = N ln N or N for 3way
 * compares = 1.39 N lg N, 39% more compares than mergesort
 * swaps = 1/3 N ln N
 * stable = no, partitions long range moves
 * adaptive = no
 * space = in-place + lg N for partition
 *
 * Created by Lamuel on 3/26/2016.
 */
public class QuickSort<T extends Comparable<T>> implements Sortable<T> {

    /**
     * sort method calls shuffler to ensure random pivot
     * before calling quicksort
     *
     * @param a array to be sorted
     */
    @Override
    public void sort(T[] a) {
        // random shuffle for performance guarantee
        // Without the shuffle, quicksorting an array of N distinct keys is quadratic.
        Shuffler shuffler = new Shuffler();
        shuffler.shuffle(a);
        System.out.printf("\nAfter Shuffle: %s", Arrays.toString(a));

        // call quicksort, always a[0..len-1];
        quickSort(a, 0, a.length-1);
    }

    /**
     * quick sort will first
     * call partition
     * sort subarrays recurvisely
     * @param a array
     * @param lo lower idx
     * @param hi upper idx
     */
    public void quickSort(T[] a, int lo, int hi) {

        // OPTIMIZE: cutoff to insertion for heapSize <= 10

        //termination clause
        if (hi <= lo)
            return;

        // OPTIMIZE: best choice of pivot = median
        // partition in the middle
        // estimate the true median by taking median of sample
        int m = medianOf3(a, lo, lo + (hi - lo)/2, hi);
        swap(a, lo, m);

        // partitioning
        int pivot = partition(a, lo, hi);
//        System.out.printf("\nArray pivoted on: %s", a[pivot]);

        // sort the subarrays
        quickSort(a, lo, pivot-1);
        quickSort(a, pivot+1, hi);
    }

    /**
     * Ensure array with lo < mid < hi and choose mid(median) as pivot
     * @param a
     * @param lo
     * @param mid
     * @param hi
     * @return mid median
     */
    private int medianOf3(T[] a, int lo, int mid, int hi) {
        if (less(a[hi], a[lo]))
            swap(a, lo, hi);
        if (less(a[mid], a[lo]))
            swap(a, mid, lo);
        if (less(a[hi], a[mid]))
            swap(a, hi, mid);
        return mid;
    }

    /**
     * 2-way Partition in-place using the lo as pivot
     * Hoare partition scheme
     *
     * Phase I:
     * Repeat until i and j pointers cross
     * scan i from left to right so long as a[i] < a[lo]
     * scan j from right to left so long as a[j] > a[lo]
     * exchange a[i] and a[j]
     * Phase II:
     * when pointers cross
     *   exchange a[lo] with a[j]
     *
     * invariant: a[1..v-1] < a[v] <= a[v+1..n]
     * before partition [v|..]
     * during partition [v|<=v|...|>=v]
     * after partition  [<=v|v|>=v]
     * @param a array
     * @param lo lower idx
     * @param hi upper idx
     * @return array which is partitioned on lo
     */
    public int partition(T[] a, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        while (true) {
            // scan i from left to right so long as a[i] < a[lo]
            while (less(a[++i], a[lo])) {
                // reached right end, terminate
                if (i == hi)
                    break;
            }

            // scan j from right to left so long as a[j] > a[lo]
            while (less(a[lo], a[--j])) {
                // reached left end, terminate. usually don't reach
                if (j == lo)
                    break;
            }

            // pointers cross over, terminate
            if (i >= j)
                break;

            //stuck on i and j, so swap
            swap(a, i, j);
        }

        // swap pivot to the middle
        swap(a, lo, j);

        // return index of the pivot
        return j;
    }

    /**
     * Dijkstra
     * 3-way Partition in-place to solve duplicate keys
     * invariant [lo <v | lt =v i|...gt| >v hi]
     * lt..gt are equal to v
     * dutch national flag problem
     * bug on qsort 1991 takes quadratic time on dups on keys
     * entropy optimal
     *
     * @param a array
     * @param lo lower idx
     * @param hi upper idx
     * @return array which is partitioned on lo
     */
    public void quickSort3WayPartition(T[] a, int lo, int hi) {
//        System.out.printf("\n%s", Arrays.toString(a));

        if (hi < lo)
            return;

        // idx for the partition = v
        int lt = lo;
        int i = lo;
        int gt = hi;

        int cmp;

        // pivot
        T v = a[lo];

        // partition keeping the invariant
        while (i <= gt ) {
            cmp = a[i].compareTo(v);
            if      (cmp < 0) swap(a, lt++, i++);
            else if (cmp > 0) swap(a, i,    gt--);
            else              i++;
        }

        quickSort3WayPartition(a, lo, lt - 1);
        quickSort3WayPartition(a, gt + 1, hi);
    }
}
