package kyu4;

import java.util.ArrayList;

public class HammingNumbers {

    private static long findNextHammingNumber(ArrayList<Long> z, long n) {

        long prev = 5 * n;

        for (int i = z.size() - 1; i >= 0; i--) {

            long el = z.get(i);

            long l2 = 2L * el;
            long l3 = 3L * el;
            long l5 = 5L * el;

            if (l2 < n && l3 < n && l5 < n) {
                break;
            }

            if (l5 > n) {

                prev = Math.min(l5, prev);
            }

            if (l3 > n) {

                prev = Math.min(l3, prev);
            }

            if (l2 > n) {

                prev = Math.min(l2, prev);
            }
        }

        return prev;
    }

    public static long hamming(int n) {

        final ArrayList<Long> previousHammingNumbers = new ArrayList<>();
        previousHammingNumbers.add(1L);
        long previousHammingNumber = 1L;
        int i = 1;

        while (i <= n) {
            previousHammingNumber = findNextHammingNumber(previousHammingNumbers, previousHammingNumber);
            previousHammingNumbers.add(previousHammingNumber);
            i++;
        }

        return previousHammingNumbers.get(n - 1);
    }
}
