package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lamuel on 10/10/2016.
 */
public class NQueens {
    public static void main(String[] args) {
        nQueens(3);
    }

    public static void nQueens(int n) {
        List<List<Integer>> results = new ArrayList<>();
        nQueensHelper(n, 0, new ArrayList<Integer>(), results);
        System.out.printf("\nFull Results => %s", results);
    }

    private static void nQueensHelper(int n, int row,
                                      List<Integer> columnPlacements,
                                      List<List<Integer>> results) {
        System.out.printf("\n*** Caling row : %d", row);

        if (row == n) {
            // all q are valid placed
            results.add(new ArrayList<Integer>(columnPlacements));
            System.out.printf("\nResults => %s", results);

        } else {
            for(int col = 0; col < n; ++col) {
                columnPlacements.add(col);
                System.out.printf("\ntrying %d, %d; cols %s", row, col, columnPlacements);
                if (isValid(columnPlacements)) {
                    System.out.printf("\n\tfound %d, %d; cols %s", row, col, columnPlacements);
                    // solve the next row
                    nQueensHelper(n, row + 1, columnPlacements, results);
                }
                // backtrack the last step
                columnPlacements.remove(columnPlacements.size() - 1);
                System.out.printf("\nremoved %d, %d; cols %s", row, col, columnPlacements);
            }
        }
    }

    private static boolean isValid(List<Integer> columnPlacements) {
        // last placed row
        int rowId = columnPlacements.size() - 1;
        for (int i = 0; i < rowId; ++i) {
            int diff = Math.abs(columnPlacements.get(i) -
                                columnPlacements.get(rowId));
            if (diff == 0 || diff == rowId - i) {
                System.out.printf("\n\tInvalid row: %d, cols %s", rowId, columnPlacements);
                return false;
            }
        }
        System.out.printf("\n\tValid row: %d, cols %s", rowId, columnPlacements);
        return true;
    }
}
