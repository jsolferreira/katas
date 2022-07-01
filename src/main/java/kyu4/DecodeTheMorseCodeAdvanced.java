package kyu4;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DecodeTheMorseCodeAdvanced {

    private static final Map<String, String> morseCodeMap = Map.ofEntries(
            Map.entry(".-", "A"),
            Map.entry("-...", "B"),
            Map.entry("-.-.", "C"),
            Map.entry("-..", "D"),
            Map.entry(".", "E"),
            Map.entry("..-.", "F"),
            Map.entry("--.", "G"),
            Map.entry("....", "H"),
            Map.entry("..", "I"),
            Map.entry(".---", "J"),
            Map.entry("-.-", "K"),
            Map.entry(".-..", "L"),
            Map.entry("--", "M"),
            Map.entry("-.", "N"),
            Map.entry("---", "O"),
            Map.entry(".--.", "P"),
            Map.entry("--.-", "Q"),
            Map.entry(".-.", "R"),
            Map.entry("...", "S"),
            Map.entry("-", "T"),
            Map.entry("..-", "U"),
            Map.entry("...-", "V"),
            Map.entry(".--", "W"),
            Map.entry("-..-", "X"),
            Map.entry("-.--", "Y"),
            Map.entry("--..", "Z"),
            Map.entry("/", " "),
            Map.entry(".----", "1"),
            Map.entry("..---", "2"),
            Map.entry("...--", "3"),
            Map.entry("....-", "4"),
            Map.entry(".....", "5"),
            Map.entry("-....", "6"),
            Map.entry("--...", "7"),
            Map.entry("---..", "8"),
            Map.entry("----.", "9"),
            Map.entry("-----", "0"),
            Map.entry(".-.-.-", "."),
            Map.entry("--..--", ","),
            Map.entry("---...", ":"),
            Map.entry("..--..", "?"),
            Map.entry(".----.", "'"),
            Map.entry("-....-", "-"),
            Map.entry("-..-.", "/"),
            Map.entry(".--.-.", "@"),
            Map.entry("-...-", "="),
            Map.entry("...---...", "SOS"),
            Map.entry("-.-.--", "!")
    );

    public static String decodeBits(String bits) {

        final String trimmedBits = bits.replaceAll("(^0*|0*$)", "");

        final int timeUnit = getTimeUnit(trimmedBits);
        final String pauseBetweenWords = "0".repeat(timeUnit * 7);
        final String pauseBetweenCharacters = "0".repeat(timeUnit * 3);
        final String pauseBetweenDotsAndDashes = "0".repeat(timeUnit);
        final String dot = "1".repeat(timeUnit);

        return Arrays.stream(trimmedBits.split(pauseBetweenWords))
                .map(word -> Arrays.stream(word.split(pauseBetweenCharacters))
                        .map(character -> Arrays.stream(character.split(pauseBetweenDotsAndDashes))
                                .map(dotOrDash -> dotOrDash.equals(dot) ? "." : "-")
                                .collect(Collectors.joining()))
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining("   "));
    }

    private static int getTimeUnit(String bits) {

        Pattern pattern = Pattern.compile("((1+)|(0+))");
        Matcher matcher = pattern.matcher(bits);

        int timeUnit = 0;
        int otherTimeUnit = 0;

        while (matcher.find()) {
            final String group = matcher.group();
            if (timeUnit == 0) {
                timeUnit = group.length();
            } else if (group.length() != timeUnit) {
                otherTimeUnit = group.length();
            }
        }

        return otherTimeUnit == 0 ? timeUnit : Math.min(timeUnit, otherTimeUnit);
    }

    public static String decodeMorse(String morseCode) {

        return Arrays.stream(morseCode.trim().split(" {3}"))
                .map(word -> Arrays.stream(word.split(" "))
                        .map(morseCodeMap::get)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));
    }
}
