package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lamuel on 10/11/2016.
 */
public class GenerateKSubSets {
    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        int n = 5; // 1-5
        int k = 2; //subset size
        int offset = 1;
        directedCombinations(n, k, offset, new ArrayList<>(), result);
        System.out.println("nPerm="+result.size());
        System.out.println(result);
    }
    public static void directedCombinations(int n, int k, int offset,
                                        List<Integer> partialCombination,
                                        List<List<Integer>> result) {
        if (partialCombination.size() == k) {
            result.add(new ArrayList<>(partialCombination));
            return;
        }

        // gen remaining comb over {offset, ... n - 1}
        final int numbRemaining = k - partialCombination.size();
        for (int i = offset; i <= n && numbRemaining <= n - i + 1; i++) {
            partialCombination.add(i);
            directedCombinations(n, k, i+1, partialCombination,result);
            partialCombination.remove(partialCombination.size() - 1);
        }
    }
}
