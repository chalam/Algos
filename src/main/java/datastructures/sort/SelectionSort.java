package datastructures.sort;

import java.util.Arrays;

/**
 * Selection Sort
 * Created by Lamuel on 3/26/2016.
 */
public class SelectionSort<T  extends Comparable<T>> implements Sortable<T> {

    /**
     *  find the next min in the remaining entry and swap with curr item
     *  invariants:
     *    entries to left of min are fixed and in ascending order
     *    no entry to the right is smaller that any entry on left of minpick a key x and maintain invariant [ <=x | x | > x ]
     *  Not stable = equal keys are reordered
     *  O(1) extra space
     *  O(n^2) compare
     *  O(n) swap, minimal data move
     *  not adaptive = doesn't care about input when nearly sorted
     *  worst and avg and best = O(N^2)
     * @param data
     */
    public void sort(T[] data){
        for (int i = 0; i < data.length - 1; i++) {
            /* find the min element in the unsorted a[i .. n-1] */

            /* assume the min is the first element */
            int min = i;

            /* test against elements after i to find the smallest */
            for(int j = i + 1; j < data.length; j++) {
//                System.out.printf("\n%d, %d", data[i], data[j]);

                /* if this element is less, then it is the new minimum */
                if (less(data[j], data[min])) {
                    min = j;
                }
            }

            if (min != i) {
                swap(data, i, min);
            }
            System.out.printf("\nIter=%d, Array=%s", i, Arrays.toString(data));
        }
    }

}
