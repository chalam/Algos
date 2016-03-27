package datastructures.sort;

/**
 * Shell proposed increasing the hop/stride length h of insertion sort
 * a g-sorted array remains g-sorted after h-sorting
 * Shell is off-course faster than Insertion
 * Created by Lamuel on 3/26/2016.
 */
public class ShellSort<T extends Comparable<T>> implements Sortable<T> {

    /**
     * sort method
     *
     * @param data input array
     */
    @Override
    public void sort(T[] data) {
        int N = data.length;
        int h = 1;
        InsertionSort ins = new InsertionSort();

        //Knuth proposal for h = 1, 4, 13, 40, 121
        while (h < N/3)
            h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            System.out.printf("\n%d-sorting now", h);
            // insertion sort
//            for (int i = h; i < N; i++) {
//                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]...
//                for (int j = i; j >= h && less(data[j], data[j-h]); j -= h )
//                    swap(data, j, j-h);
//            }
            ins.sortWithHop(data, h);

            // move to next incr
            h = h/3;
        }
    }
}
