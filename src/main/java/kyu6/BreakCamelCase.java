package kyu6;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BreakCamelCase {
    public static String camelCase(String input) {

        String[] split = input.replaceAll("([A-Z])", " $1").split(" ");

        return Arrays.stream(split, 1, split.length)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" ", split[0] + " ", ""))
                .trim();
    }
}
