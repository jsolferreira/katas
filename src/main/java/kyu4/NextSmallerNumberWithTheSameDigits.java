package kyu4;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NextSmallerNumberWithTheSameDigits {

    public static long nextSmaller(long n) {
        String numberAsString = String.valueOf(n);

        for (int i = numberAsString.length() - 1; i > 0; i--) {
            char a = numberAsString.charAt(i);
            char b = numberAsString.charAt(i - 1);

            if (a < b) {
                String substringToSwap = numberAsString.substring(i).chars()
                        .mapToObj(c -> (char) c)
                        .sorted(Comparator.reverseOrder())
                        .map(String::valueOf)
                        .collect(Collectors.joining());

                int firstSmaller = IntStream.range(0, substringToSwap.length())
                        .filter(j -> substringToSwap.charAt(j) < b)
                        .findFirst()
                        .orElse(-1);

                if (firstSmaller == -1) {
                    return -1;
                }

                String smallerNumber = numberAsString.substring(0, i - 1) +
                        substringToSwap.charAt(firstSmaller) +
                        substringToSwap.substring(0, firstSmaller) +
                        b +
                        substringToSwap.substring(firstSmaller + 1);

                if (smallerNumber.startsWith("0")) {
                    return -1;
                }

                return Long.parseLong(smallerNumber);
            }
        }

        return -1;
    }
}
