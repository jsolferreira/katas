package kyu6;

import java.util.Arrays;

public class WhichAreIn {

    public static String[] inArray(String[] array1, String[] array2) {

        return Arrays.stream(array1)
                .filter(element -> contains(array2, element))
                .sorted()
                .toArray(String[]::new);
    }

    private static boolean contains(String[] array, String element) {

        return Arrays.stream(array)
                .anyMatch(s -> s.contains(element));
    }
}
