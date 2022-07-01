package kyu7;

import java.util.List;
import java.util.stream.IntStream;

public class OnesAndZeroes {

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {

        final int l = binary.size();

        return IntStream.range(0, l)
                .reduce(0, (acc, val) -> binary.get(val) == 1 ? acc + (int) Math.pow(2, l - (val + 1)) : acc);
    }
}
