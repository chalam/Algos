package datastructures.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class MergeSortTest {
    @Test
    public void sort() {
        MergeSort sorter = new MergeSort();
        String[] letters = "MERGESORTEXAMPLE".split("");
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));
        sorter.sort(letters);
        assertTrue("Array is not sorted", sorter.isSorted(letters));
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));

        letters = "MERGESORTEXAMPLE".split("");
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));
        sorter.mergeSortBottomUp(letters);
        assertTrue("Array is not sorted", sorter.isSorted(letters));
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));

    }
}