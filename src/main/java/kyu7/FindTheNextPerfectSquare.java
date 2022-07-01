package kyu7;

import java.util.Optional;

public class FindTheNextPerfectSquare {
    public static long findNextSquare(long sq) {

        return Optional.of(Math.sqrt(sq))
                .filter(sqrt -> Math.floor(sqrt) == sqrt)
                .map(sqrt -> (long) Math.pow(sqrt + 1, 2))
                .orElse((long) -1);
    }
}
