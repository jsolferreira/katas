package kyu3;

import java.math.BigInteger;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AlphabeticAnagrams {

    public BigInteger listPosition(String word) {

        if (word.length() == 1) {
            return BigInteger.ONE;
        }

        final char firstChar = word.charAt(0);
        String substring = word.substring(1);

        Map<Integer, Long> collect = word.chars()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long count = substring.chars()
                .filter(c -> c < (int) firstChar)
                .count();

        BigInteger repetitions = collect.values().stream()
                .filter(c -> c > 1)
                .map(this::factorial)
                .reduce(BigInteger.ONE, BigInteger::multiply);

        return BigInteger.valueOf(count).multiply(factorial(substring.length())).divide(repetitions).add(listPosition(substring));
    }

    private BigInteger factorial(long x) {

        if (x == 1) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(x).multiply(factorial(x - 1));
        }
    }
}
