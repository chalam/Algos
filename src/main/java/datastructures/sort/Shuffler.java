package datastructures.sort;

import java.util.Random;

/**
 * Knuth Shuffler
 * each iter, swap a[i] with a[r] where r between 1 and i uniformly at random
 * Hard problem FIPS 140-2 or NIST
 * Created by Lamuel on 3/26/2016.
 */
public class Shuffler<T extends Comparable<T>>  implements Sortable {

    private static Random random;    // pseudo-random number generator
    private static long seed;        // pseudo-random number generator seed

    // static initializer
    static {
        // this is how the seed was set in Java 1.4
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    public void shuffle(T[] data) {
        for (int i = 0; i < data.length-1; i++) {
            int r = random.nextInt(i+1); //uniformly in [0, i )
            swap(data, i, r);
        }
    }
}
