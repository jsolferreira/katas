package kyu7;

import java.util.stream.IntStream;

public class TheOfficeIVFindAMeetingRoom {
    public static Object meeting(char[] x) {
        return IntStream.range(0, x.length)
                .boxed()
                .filter(i -> x[i] == 'O')
                .map(i -> (Object) i)
                .findFirst()
                .orElse("None available!");
    }
}
