package datastructures.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class InsertionSortTest {
    @Test
    public void sort() {
        InsertionSort sorter = new InsertionSort();
//        Integer[] data = {5,2, 12, 5,2 ,1,6,8,4,3,7,0, -3};
//        Integer[] data = {5, 1, -4, 8, 2};
//        Integer[] data = {5, 2, 4, 6, 1, 3};
        Integer[] data = {5, 1, 6, 2, 4, 3};


        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(data), Arrays.toString(data));
        sorter.sort(data);
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(data), Arrays.toString(data));

        String[] letters = "SORTEXAMPLE".split("");
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));
        sorter.sort2(letters);
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));

    }
}