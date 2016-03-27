package datastructures.search;

import datastructures.sort.Sortable;

/**
 * Binary Search impl with iterative and recursive methods
 * Created by Lamuel on 3/26/2016.
 */
public class BinarySearch<T extends Comparable<T>> implements Sortable<T> {

    /**
     * find the key in the sorted data array
     */
    public int searchIter(T[] data, T key) {
        int lo = 0;
        int hi = data.length - 1;
        while (lo <= hi) {
//            int mid = (lo + hi) / 2; // Overflow ref bloch
            int mid = lo + (hi - lo) / 2;

            if (less(key, data[mid]))
                hi = mid - 1;
            else if (greater(key, data[mid]))
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    /**
     *   Recursive fn to find the key
     */
    private int search_inner(T[] data, T key, int lo, int hi) {
        if(lo > hi)
            return -1;

        int mid = lo + (hi - lo) / 2;
        if (less(key, data[mid]))
            return search_inner(data, key, lo, mid - 1);
        else if (greater(key, data[mid]))
            return search_inner(data, key, mid + 1, hi);
        else
            return mid;
    }

    /**
     *   Helper fn for recursive fn
     */
    public int searchRecur(T[] data, T key) {
        return search_inner(data, key, 0, data.length - 1);
    }
}
