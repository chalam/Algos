package datastructures.sort;

import java.util.Arrays;

/**
 * Insertion Sort
 * Created by Lamuel on 3/26/2016.
 */
public class InsertionSort<T extends Comparable<T>> implements Sortable<T> {

    /**
     * CLRS
     *  pick a key x and maintain invariant [ <=x | x | > x ]
     *    in iteration of i, swap a[i] with each larger entry to the left
     *    twice as fast as selection sort
     *  stable  = equal keys are not reordered
     *  space = O(1)
     *  worst and avg = O(N^2)
     *  best O(N)
     *  O(n^2) 1/4 N^2 compare and swap
     *  adaptive = o(n) when nearly sorted
     * @param data
     */
    public void sort(T[] data){
        // loop from the second to right
        for (int j = 1; j < data.length; j++) {
            T key = data[j];
            // insert A[j] into the sorted sequence A[1..j-1]
            int i = j-1;
            // loop runs to the left
            while (i >= 0 && greater(data[i], key)) {
                //shuffle to the right until the  index
                data[i + 1] = data[i];
                i--;
            }
            //move to the c index
            data[i+1] = key;
            System.out.println(Arrays.toString(data));
        }
    }

    /**
     * Sedgewick Algo with skip/hop size = 1
     * @param data
     */
    public void sort2(T[] data){
        sortWithHop(data, 1);
    }

    /**
     * Sedgewick Algo with skip/hop size h
     * @param data
     */
    public void sortWithHop(T[] data, int h){
        int N = data.length;

        // insertion sort
        for (int i = h; i < N; i++) {
            // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]...
            for (int j = i; j >= h && less(data[j], data[j-h]); j -= h )
                swap(data, j, j-h);
        }
    }
}
