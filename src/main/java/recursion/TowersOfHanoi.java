package recursion;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lamuel on 10/10/2016.
 */
public class TowersOfHanoi {
    private static final int N_PEGS = 3;

    public static void main(String[] args) {
        towers(3);
    }

    public static void towers(int nRings) {
        List<Deque<Integer>> pegs = new ArrayList<>();
        for (int i = 0; i < N_PEGS; i++) {
            pegs.add(new LinkedList<>());
        }
        for (int i = nRings; i >= 1; i--) {
            pegs.get(0).addFirst(i); // high val at bottom
        }
        System.out.printf("\nInit :%s", pegs.toString());
        towersHelper(nRings, pegs, 0, 1, 2);
    }

    private static void towersHelper(int nRings, List<Deque<Integer>> pegs,
                                     int fromPeg, int toPeg, int usePeg) {
//        if(nRings == 1) {
//            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
//            System.out.printf("\nPegs :%s; Moved peg %d -> %d", pegs.toString(), fromPeg, toPeg);
//        } else  { // (nRings > 0)
//            towersHelper(nRings - 1, pegs, fromPeg, usePeg, toPeg);
//            towersHelper(1, pegs, fromPeg, usePeg, toPeg);
//            towersHelper(nRings - 1, pegs, usePeg, toPeg, fromPeg);
//        }
        if (nRings > 0) {
            towersHelper(nRings - 1, pegs, fromPeg, usePeg, toPeg);
            // One less recur call :)
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
            System.out.printf("\nPegs :%s; Moved peg %d -> %d", pegs.toString(), fromPeg, toPeg);
            towersHelper(nRings - 1, pegs, usePeg, toPeg, fromPeg);
        }

    }
}
