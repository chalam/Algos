package datastructures.sort;

/**
 * Created by Lamuel on 3/26/2016.
 */
public interface Sortable<T extends Comparable<T>> {
    /**
     * sort method
     */
    default void sort(T[] data) {}

    /**
     * is x < y
     * @param x
     * @param y
     * @return true if x < y
     */
    default boolean less(T x, T y) {
        return  x.compareTo(y) < 0;
    }

    /**
     * is x > y
     * @param x
     * @param y
     * @return true if x > y
     */
    default boolean greater(T x, T y) {
        return  x.compareTo(y) > 0;
    }

    /**
     * is x == y
     * @param x
     * @param y
     * @return true if x == y
     */
    default boolean equalTo(T x, T y) {
        return  x.compareTo(y) == 0;
    }

    /**
     * swap index i with j on data array
     *
     * @param data
     * @param i from
     * @param j to
     */
    default void swap(T[] data, int i, int j) {
        System.out.printf("\nSwapping %s with %s\n", data[i], data[j]);
        T temp  = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * check if all elements are sorted
     * @param data
     * @return
     */
    default boolean isSorted(T[] data) {
        for (int i = 1; i < data.length; i++) {
            if (greater(data[i-1], data[i]))
                return false;
        }
        return true;
    }
}
