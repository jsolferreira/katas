package kyu5;

import java.util.HashMap;
import java.util.Map;

public class GreedIsGood {

    private static final Map<Integer, Integer> tripletPoints = Map.of(
            1, 1000,
            6, 600,
            5, 500,
            4, 400,
            3, 300,
            2, 200);

    private static final Map<Integer, Integer> singlePoints = Map.of(
            1, 100,
            5, 50);

    public static int greedy(int[] dice) {

        int points = 0;
        final HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int number : dice) {

            final int count = countMap.getOrDefault(number, 0);

            final int newCount = count + 1;
            countMap.put(number, newCount);

            if (newCount % 3 == 0) {

                points += tripletPoints.get(number);

                if (singlePoints.containsKey(number)) {

                    points -= singlePoints.get(number) * 2;
                }
            } else if (singlePoints.containsKey(number)) {

                points += singlePoints.get(number);
            }
        }

        return points;
    }
}
