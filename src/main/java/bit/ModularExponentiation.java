package bit;

import com.google.common.base.Stopwatch;

import java.math.BigInteger;

/**
 * https://en.wikipedia.org/wiki/Modular_exponentiation
 * Created by Lamuel on 10/13/2016.
 */
public class ModularExponentiation {
    public static void main(String[] args) {
        long b = 4, e = 0x7ffffffL, m = 497;
        Stopwatch timer = Stopwatch.createStarted();
        System.out.println(Math.pow(b, e) % m);
        timer.stop();
        System.out.println("Method took " + timer);

        timer.start();
        System.out.println(moduloPower(b, e, m));
        timer.stop();
        System.out.println("Method took " + timer);

        timer.start();
        BigInteger bigB = BigInteger.valueOf(b);
        BigInteger bigE = BigInteger.valueOf(e);
        BigInteger bigM = BigInteger.valueOf(m);
        System.out.println(bigB.modPow(bigE, bigM).toString());
        timer.stop();
        System.out.println("Method took " + timer);

    }

    private static long moduloPower(final long b, final long e, final long m) {
        if (m == 1)
            return 0;
        long c = 1;
        for (int ePrime = 1; ePrime <= e; ePrime++) {
            c = (c * b) % m;
//            System.out.println(c);
        }
        return c;
    }
}
