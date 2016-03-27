package datastructures.search;

import datastructures.sort.SelectionSort;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class BinarySearchTest {
    @Test
    public void doSearch() {
        SelectionSort sorter = new SelectionSort();
//        Integer[] data = {5,2, 12, 5,2 ,1,6,8,4,3,7,0, -3};
        Integer[] data = {5, 1, -4, 8, 2};


        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(data), Arrays.toString(data));
        sorter.sort(data);
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(data), Arrays.toString(data));

        BinarySearch search = new BinarySearch();
        System.out.printf("\nIs 5 Found: %d",search.searchIter(data, 5));
        System.out.printf("\nIs 4 Found: %d",search.searchIter(data, 4));
        System.out.printf("\nIs 8 Found: %d",search.searchIter(data, 8));

        System.out.printf("\nIs 5 Found: %d",search.searchRecur(data, 5));
        System.out.printf("\nIs 4 Found: %d",search.searchRecur(data, 4));
        System.out.printf("\nIs 8 Found: %d",search.searchRecur(data, 8));
    }
}