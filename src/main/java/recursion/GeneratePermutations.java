package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lamuel on 10/11/2016.
 */
public class GeneratePermutations {
    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> A = new ArrayList<>(Arrays.asList(2,3,5,7));
        directedPermutations(0, A, result);
        System.out.println("nPerm="+result.size());
        System.out.println(result);
    }
    public static void directedPermutations(int i, List<Integer> A,
                                            List<List<Integer>> result) {
        if (i == A.size() - 1) {
            result.add(new ArrayList<>(A));
            return;
        }

        //try every possibility for A[j]
        for (int j = i; j < A.size(); j++) {
            Collections.swap(A, i, j);
            //generate all perm for A.sublist[i+1, A.size]
            directedPermutations(i+1, A, result);
            Collections.swap(A, i, j);
        }

    }
}
