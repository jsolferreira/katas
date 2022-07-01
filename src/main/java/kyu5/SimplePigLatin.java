package kyu5;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SimplePigLatin {
    public static String pigIt(String str) {

        return Arrays.stream(str.split(" "))
                .map(word -> {

                    final char firstLetter = word.charAt(0);

                    if (Character.isAlphabetic(firstLetter)) {

                        return word.substring(1) + firstLetter + "ay";
                    } else {

                        return word;
                    }

                })
                .collect(Collectors.joining(" "));
    }
}
