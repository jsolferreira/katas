package kyu7;

import java.util.stream.Collectors;

public class TheOfficeIIIBrokenPhotocopier {
    public static String broken(final String x) {
        return x.chars()
                .mapToObj(c -> c == '0' ? "1" : "0")
                .collect(Collectors.joining());
    }
}
