package datastructures.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/27/2016.
 */
public class QuickSelectTest {
    @Test
    public void doSelect() throws Exception {
        QuickSelect qs = new QuickSelect();
        Integer[] data = {5,2, 12, 5,2 ,1,6,8,4,3,7,0, -3};

        Integer fourthMin = (Integer) qs.select(data, 4);
        assertEquals("Quick select failed", 2, fourthMin.intValue());

        Integer thirthMax = (Integer) qs.select(data, data.length - 3);
        assertEquals("Quick select failed", 7, thirthMax.intValue());

        QuickSort sorter = new QuickSort();
        sorter.quickSort(data, 0, data.length - 1);
        System.out.printf("\nSorted for confirmation: %s", Arrays.toString(data));

    }
}