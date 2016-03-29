package datastructures.heap;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/27/2016.
 */
public class HeapSortTest {
    @Test
    public void doHeapSort() {
        Integer[] data = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        HeapSort.sort(data);
        assertTrue("Array is not Sorted", HeapSort.isSorted(data));
        System.out.printf("\nArray sorted: %s", Arrays.toString(data));

        String[] letters = "HEAPSORTEXAMPLE".split("");
        HeapSort.sort(letters);
        assertTrue("Array is not Sorted", HeapSort.isSorted(letters));
        System.out.printf("\nArray sorted: %s", Arrays.toString(letters));
    }
}