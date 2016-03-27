package datastructures.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class ShufflerTest {
    @Test
    public void doShuffle() {
        String[] letters = "SHELLSORTEXAMPLE".split("");

        System.out.printf("\nBefore shuffle: %s", Arrays.toString(letters));
        Shuffler shuffler = new Shuffler();
        shuffler.shuffle(letters);

        ShellSort sorter = new ShellSort();
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));
        sorter.sort(letters);
        System.out.printf("\nArray sorted: %s, %s", sorter.isSorted(letters), Arrays.toString(letters));

    }
}