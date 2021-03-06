package kyu6;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DecodeTheMorseCode {

    private static final Map<String, String> codeMap = Map.ofEntries(
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

    public static String decode(String morseCode) {

        return Arrays.stream(morseCode.trim().split(" {3}"))
                .map(word -> Arrays.stream(word.split(" "))
                        .map(codeMap::get)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));
    }
}
