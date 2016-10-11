package bit;

import java.util.Scanner;

/**
 * Created by Lamuel on 10/10/2016.
 */
public class ConsecutiveBits {
    public static void main(String[] args) {

//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int numBits = 0;
//        int lsb = 0;
//        int prev = 0, next = 0;
//        do {
//            lsb = n & 1;
//            if (numBits == 0 && lsb == 1)
//                numBits = lsb;
//            numBits += (lsb & prev);
//            n >>>= 1;
//            prev = lsb;
//        } while (n != 0);
//        System.out.println(numBits);

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int count = 0;
        int countMax = 0;
        int lsb = 0;
        do {
            lsb = n & 1;
            if (lsb == 1)
                count++;
            else
                count=0;
            if (count > countMax)
                countMax = count;
            n >>>= 1;
        } while (n != 0);
        System.out.println(countMax);
    }
}
