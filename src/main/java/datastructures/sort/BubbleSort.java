package datastructures.sort;

import java.util.Arrays;

/**
 * Bubble or sinking or comparator sort
 * Created by Lamuel on 3/26/2016.
 */
public class BubbleSort<T extends Comparable<T>> implements Sortable<T> {

    /**
     *  sinking sort compares adj items and swaps until no swap is done
     *  stable  = equal keys are not reordered
     *  O(n^2) compare and swap
     *  adaptive = o(n) when nearly sorted
     * @param data
     */
    public void sort(T[] data) {
        boolean swapped = true;
        for (int i = data.length -1; i > 0 && swapped; i--) {
            swapped = false;
            // compare adj items
            for (int j = 0; j < i; j++) {
                // swap if needed
                if (greater(data[j], data[j+1])) {
                    swap(data, j, j+1);
                    swapped = true;
                }
            }
            System.out.printf("\nIter=%d, Array=%s", i, Arrays.toString(data));
        }
    }
}
