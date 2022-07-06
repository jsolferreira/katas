package kyu3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BattleshipFieldValidator {

    public static boolean fieldValidator(int[][] field) {
        final Map<Integer, Integer> remainingShips = new HashMap<>(
                Map.ofEntries(
                        Map.entry(4, 1),
                        Map.entry(3, 2),
                        Map.entry(2, 3),
                        Map.entry(1, 4))
        );

        Boolean[][] visited = Arrays
                .stream(field)
                .map(row -> Arrays.stream(row).mapToObj(__ -> false).toArray(Boolean[]::new))
                .toArray(Boolean[][]::new);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int pos = field[i][j];

                if (pos == 1 && !visited[i][j]) {
                    visited[i][j] = true;

                    boolean validBorders = validateBorders(field, i, j);

                    if (!validBorders) {
                        return false;
                    }

                    if (hasHorizontalAdjacent(field, i, j)) {
                        int count = 0;
                        for (int k = i; k < field.length; k++) {
                            if (field[k][j] == 0) {
                                break;
                            }
                            if (!validateBorders(field, k, j))
                                return false;
                            count++;
                            visited[k][j] = true;
                        }

                        Integer integer = remainingShips.get(count);

                        if (integer != null && integer > 0) {
                            remainingShips.put(count, integer - 1);
                        } else {
                            return false;
                        }

                    } else if (hasVerticalAdjacent(field, i, j)) {
                        int count = 0;
                        for (int k = j; k < field[i].length; k++) {
                            if (field[i][k] == 0) {
                                break;
                            }
                            if (!validateBorders(field, i, k))
                                return false;
                            count++;
                            visited[i][k] = true;
                        }

                        Integer integer = remainingShips.get(count);

                        if (integer != null && integer > 0) {
                            remainingShips.put(count, integer - 1);
                        } else {
                            return false;
                        }
                    } else {

                        Integer integer = remainingShips.get(1);

                        if (integer != null && integer > 0) {
                            remainingShips.put(1, integer - 1);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }

        return remainingShips.values().stream().allMatch(r -> r == 0);
    }

    private static boolean validateBorders(int[][] field, int i, int j) {

        final boolean hasHorizontalAdjacent = hasHorizontalAdjacent(field, i, j);
        final boolean hasVerticalAdjacent = hasVerticalAdjacent(field, i, j);
        final boolean hasUpperRightCornerContact = i != 0 && j != field[i].length - 1 && field[i - 1][j + 1] == 1;
        final boolean hasLowerRightCornerContact = i != field.length - 1 && j != field[i].length - 1 && field[i + 1][j + 1] == 1;

        return !hasUpperRightCornerContact && !hasLowerRightCornerContact && !(hasHorizontalAdjacent && hasVerticalAdjacent);
    }

    private static boolean hasHorizontalAdjacent(int[][] field, int i, int j) {

        return i != field.length - 1 && field[i + 1][j] == 1;
    }

    private static boolean hasVerticalAdjacent(int[][] field, int i, int j) {

        return j != field[i].length - 1 && field[i][j + 1] == 1;
    }
}
