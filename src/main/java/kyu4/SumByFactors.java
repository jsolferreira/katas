package kyu4;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SumByFactors {
    public static String sumOfDivided(int[] l) {

        final TreeMap<Integer, Integer> map = new TreeMap<>();
        final HashMap<Integer, Boolean> primeCache = new HashMap<>();

        for (int i : l) {
            for (int j = Math.abs(i); j > 1; j--) {
                if (i % j != 0) continue;

                boolean isPrime = primeCache.computeIfAbsent(j, SumByFactors::isPrime);

                if (isPrime) {
                    map.merge(j, i, Integer::sum);
                }
            }
        }

        return map.entrySet().stream()
                .map(entry -> "(" + entry.getKey() + " " + entry.getValue() + ")")
                .collect(Collectors.joining());
    }

    private static boolean isPrime(int n) {

        for (int i = n - 1; i > 1; i--) {
            if (n % i == 0) return false;
        }

        return true;
    }
}
