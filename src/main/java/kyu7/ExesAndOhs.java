package kyu7;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExesAndOhs {
    public static boolean getXO(String str) {

        Map<String, Long> counts = Stream.of(str.split(""))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return Objects.equals(counts.get("x"), counts.get("o"));
    }
}
