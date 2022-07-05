package kyu6;

import java.util.List;

public class MultiTapKeypadTextEntryOnAnOldMobilePhone {

    private static final List<String> keyboard = List.of(
            "1",
            "ABC2",
            "DEF3",
            "GHI4",
            "JKL5",
            "MNO6",
            "PQRS7",
            "TUV8",
            "WXYZ9",
            "#",
            " 0",
            "*"
    );

    public static int presses(String phrase) {

        return phrase.toUpperCase().chars()
                .map(c -> keyboard.stream()
                        .filter(k -> k.indexOf(c) != -1)
                        .map(k -> k.indexOf(c) + 1)
                        .findFirst()
                        .orElse(0))
                .sum();
    }
}
