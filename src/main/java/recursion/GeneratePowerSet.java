package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lamuel on 10/11/2016.
 */
public class GeneratePowerSet {
    public static void main(String[] args) {
        List<List<Integer>> powerSet = new ArrayList<>();
        List<Integer> inputSet = new ArrayList<>(Arrays.asList(1,2,3));
        directedPowerSet(inputSet, 0, new ArrayList<Integer>(), powerSet);
        System.out.println("nPerm="+powerSet.size());
        System.out.println(powerSet);
    }
    public static void directedPowerSet(List<Integer> inputSet, int toBeSelected,
                                            List<Integer> selectedSoFar,
                                            List<List<Integer>> powerSet) {
        if (toBeSelected == inputSet.size()) {
            powerSet.add(new ArrayList<>(selectedSoFar));
            return;
        }

        //gen all subset with inputSet[toBeSelected]
        selectedSoFar.add(inputSet.get(toBeSelected));
        directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet);

        //gen all subsets that do not contain inputSet[toBeSelected]
        selectedSoFar.remove(selectedSoFar.size() - 1);
        directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet);
    }
}
