package datastructures.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class SelectionSortTest {
    @Test
    public void sort() {
        SelectionSort sorter = new SelectionSort();
//        Integer[] data = {5,2, 12, 5,2 ,1,6,8,4,3,7,0, -3};
//        Integer[] data = {5, 1, -4, 8, 2};
//        Integer[] data = {64, 25, 12, 22, 11};
        Integer[] data = {5, 1, 6, 2, 4, 3};

        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(data), Arrays.toString(data));
        sorter.sort(data);
        assertTrue("Array is not sorted", sorter.isSorted(data));
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(data), Arrays.toString(data));

        String[] letters = "SORTEXAMPLE".split("");
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));
        sorter.sort(letters);
        assertTrue("Array is not sorted", sorter.isSorted(letters));
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));

    }
}