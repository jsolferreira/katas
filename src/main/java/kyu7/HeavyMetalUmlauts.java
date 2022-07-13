package kyu7;

import java.util.Map;
import java.util.stream.Collectors;

public class HeavyMetalUmlauts {

    private static final Map<Character, Character> map = Map.ofEntries(
            Map.entry('A', '\u00c4'),
            Map.entry('E', '\u00cb'),
            Map.entry('I', '\u00cf'),
            Map.entry('O', '\u00d6'),
            Map.entry('U', '\u00dc'),
            Map.entry('Y', '\u0178'),
            Map.entry('a', '\u00e4'),
            Map.entry('e', '\u00eb'),
            Map.entry('i', '\u00ef'),
            Map.entry('o', '\u00f6'),
            Map.entry('u', '\u00fc'),
            Map.entry('y', '\u00ff')
    );

    public static String heavyMetalUmlauts(String boringText) {

        return boringText.chars()
                .mapToObj(c -> String.valueOf(map.getOrDefault((char) c, (char) c)))
                .collect(Collectors.joining());
    }
}
