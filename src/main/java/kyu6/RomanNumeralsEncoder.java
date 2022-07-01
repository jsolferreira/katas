package kyu6;

import java.util.Map;
import java.util.TreeMap;

public class RomanNumeralsEncoder {

    public RomanNumeralsEncoder() {
    }

    private final Map<Integer, String> valueSymbolMap = new TreeMap<>(Map.of(
            1, "I",
            5, "V",
            10, "X",
            50, "L",
            100, "C",
            500, "D",
            1000, "M")).descendingMap();

    public String solution(int n) {

        final StringBuilder sb = new StringBuilder();

        solutionAux(n, sb);

        return sb.toString();
    }

    private void solutionAux(int n, StringBuilder sb) {

        if (n == 0) {

            return;
        }

        final int numberToTransform = getNumberToTransform(n);

        int approxNumber = findApproxNumber(numberToTransform);
        int previousValue = findPreviousValue(approxNumber);

        if ((numberToTransform % approxNumber == 0 && approxNumber * 3 >= numberToTransform) || (approxNumber != 1 && approxNumber + previousValue * 3 >= numberToTransform) || (approxNumber == 1 && previousValue * 3 >= numberToTransform)) {
            sb.append(valueSymbolMap.get(approxNumber));
            int j = numberToTransform - approxNumber;

            while (j > 0) {
                if (j % approxNumber == 0) {
                    sb.append(valueSymbolMap.get(approxNumber));
                    j -= approxNumber;

                } else {
                    sb.append(valueSymbolMap.get(previousValue));
                    j -= previousValue;
                }
            }
        } else {

            int nextValue = findNextValue(approxNumber);

            if (nextValue - approxNumber == numberToTransform) {

                sb.append(valueSymbolMap.get(approxNumber));
            } else {

                sb.append(valueSymbolMap.get(previousValue));
            }

            sb.append(valueSymbolMap.get(nextValue));
        }

        solutionAux(n - numberToTransform, sb);
    }

    private int findApproxNumber(int n) {

        return valueSymbolMap.keySet()
                .stream()
                .filter(k -> n >= k)
                .findFirst()
                .get();
    }

    private int getNumberToTransform(int n) {

        int i = 1;

        while (n % i != n) {
            i *= 10;
        }

        return n - (n % (i / 10));
    }

    private int findPreviousValue(int n) {

        return valueSymbolMap.containsKey(n / 2) ? n / 2 :
                valueSymbolMap.containsKey(n / 5) ? n / 5 : n;
    }

    private int findNextValue(int n) {

        return valueSymbolMap.containsKey(n * 2) ? n * 2 :
                valueSymbolMap.containsKey(n * 5) ? n * 5 : n;
    }
}
