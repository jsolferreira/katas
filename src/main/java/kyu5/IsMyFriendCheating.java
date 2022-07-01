package kyu5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IsMyFriendCheating {

    public static List<long[]> removNb(long n) {

        final long sum = n * (n + 1) / 2;
        final ArrayList<long[]> list = new ArrayList<>();

        for (long i = 1; i <= n; i++) {
            final long estProd = i * n;
            final long estDifference = sum - i - n;

            if (estProd < estDifference) {

                continue;
            }

            long lowerBound = i;
            long upperBound = n;

            long pivot = lowerBound + (upperBound - lowerBound) / 2;

            long prod = i * pivot;
            long difference = sum - pivot - i;

            while (pivot > lowerBound && pivot < upperBound) {
                if (prod < difference) {

                    lowerBound = pivot;
                } else if (prod > difference) {

                    upperBound = pivot;
                } else {

                    list.add(new long[]{i, pivot});
                    list.add(new long[]{pivot, i});
                    break; // unsure
                }

                pivot = lowerBound + (upperBound - lowerBound) / 2;

                prod = i * pivot;
                difference = sum - pivot - i;
            }
        }

        return list.stream()
                .sorted(Comparator.comparingDouble(o -> o[0]))
                .collect(Collectors.toList());
    }
}
