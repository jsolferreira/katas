package kyu4;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StripComments {
    public static String stripComments(String text, String[] commentSymbols) {

        String commentRegex = Arrays.stream(commentSymbols)
                .map(symbol -> symbol.equals("-") ? "\\" + symbol : symbol)
                .collect(Collectors.joining());

        final String regex = "(^[" + commentRegex + "].*)|( ?[" + commentRegex + "].*)|( +)(\n|$)";

        return text.replaceAll(regex, "$4");
    }
}
