package kyu4;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class ParseIntReloaded {

    private static final Map<String, Integer> numberMap = Map.ofEntries(
            Map.entry("zero", 0),
            Map.entry("one", 1),
            Map.entry("two", 2),
            Map.entry("three", 3),
            Map.entry("four", 4),
            Map.entry("five", 5),
            Map.entry("six", 6),
            Map.entry("seven", 7),
            Map.entry("eight", 8),
            Map.entry("nine", 9),
            Map.entry("ten", 10),
            Map.entry("eleven", 11),
            Map.entry("twelve", 12),
            Map.entry("thirteen", 13),
            Map.entry("fourteen", 14),
            Map.entry("fifteen", 15),
            Map.entry("sixteen", 16),
            Map.entry("seventeen", 17),
            Map.entry("eighteen", 18),
            Map.entry("nineteen", 19),
            Map.entry("twenty", 20),
            Map.entry("thirty", 30),
            Map.entry("forty", 40),
            Map.entry("fifty", 50),
            Map.entry("sixty", 60),
            Map.entry("seventy", 70),
            Map.entry("eighty", 80),
            Map.entry("ninety", 90)
    );

    private static final Map<String, Function<Integer, Integer>> multiplierMap = Map.ofEntries(
            Map.entry("hundred", x -> x * 100),
            Map.entry("thousand", __ -> 1000),
            Map.entry("million", __ -> 1000000)
    );

    public static int parseInt(String numStr) {

        String[] split = numStr.split(" ");

        int multiplier = 1;
        int number = 0;

        for (int i = split.length - 1; i >= 0; i--) {
            String s = split[i];

            if (s.equals("and")) {

                continue;
            }

            if (multiplierMap.containsKey(s)) {

                multiplier = multiplierMap.get(s).apply(multiplier);
            } else {

                final int n = Arrays.stream(s.split("-"))
                        .map(numberMap::get)
                        .mapToInt(Integer::intValue)
                        .sum();

                number += n * multiplier;
            }
        }

        return number;
    }
}
