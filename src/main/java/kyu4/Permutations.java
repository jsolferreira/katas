package kyu4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    public static List<String> singlePermutations(String s) {

        final List<String> permutations = new ArrayList<>();
        permutations.add(s);
        singlePermutationsAux(permutations, s, 0);

        return permutations;
    }

    private static void singlePermutationsAux(List<String> permutations, String s, int index) {

        final char[] chars = s.toCharArray();

        for (int i = index + 1; i < chars.length; i++) {

            singlePermutationsAux(permutations, s, index + 1);

            if (chars[index] == chars[i]) {
                continue;
            }

            char[] charsAux = Arrays.copyOf(chars, chars.length);

            char aux = charsAux[index];
            charsAux[index] = charsAux[i];
            charsAux[i] = aux;
            String permutation = String.valueOf(charsAux);

            if (!permutations.contains(permutation)) {
                permutations.add(permutation);
                singlePermutationsAux(permutations, permutation, index + 1);
            }
        }
    }
}
