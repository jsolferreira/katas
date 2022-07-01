package kyu4;

import java.util.stream.IntStream;

public class MagnetParticlesInBoxes {

    public static double doubles(int maxk, int maxn) {

        return IntStream.rangeClosed(1, maxk)
                .mapToDouble(x -> (float) x)
                .reduce(0, (acc1, k) -> IntStream.rangeClosed(1, maxn)
                        .mapToDouble(x -> (float) x)
                        .reduce(acc1, (acc2, n) -> acc2 + (1 / (k * Math.pow((n + 1), 2 * k))))
                );
    }
}
