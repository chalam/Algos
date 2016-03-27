package datastructures.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class BubbleSortTest {
    @Test
    public void sort() {
        BubbleSort sorter = new BubbleSort();
//        Integer[] data = {5,2, 12, 5,2 ,1,6,8,4,3,7,0, -3};
//        Integer[] data = {5, 1, -4, 8, 2};
//        Integer[] data = {5, 1, 4, 2, 8};
        Integer[] data = {5, 1, 6, 2, 4, 3};


        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(data), Arrays.toString(data));
        sorter.sort(data);
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(data), Arrays.toString(data));
    }
}