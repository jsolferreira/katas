package kyu5;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JosephusSurvivor {

    public static int josephusSurvivor(final int n, final int k) {
        ArrayList<Integer> elements = IntStream.rangeClosed(1, n)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        int step = 0;

        while (elements.size() > 1) {
            step = (step + (k - 1)) % elements.size();
            elements.remove(step);
        }

        return elements.get(0);
    }
}
