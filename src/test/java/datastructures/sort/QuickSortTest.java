package datastructures.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/27/2016.
 */
public class QuickSortTest {
    @Test
    public void sort() {
        QuickSort sorter = new QuickSort();
        String[] letters = "QUICKSORTEXAMPLE".split("");
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));
        sorter.sort(letters);
        assertTrue("Array is not sorted", sorter.isSorted(letters));
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));

        letters = "RBWWRWBRRWBR".split("");
        sorter.quickSort3WayPartition(letters, 0, letters.length - 1);
        assertTrue("Array is not sorted", sorter.isSorted(letters));
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));
    }
}