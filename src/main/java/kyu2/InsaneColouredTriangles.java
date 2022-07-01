package kyu2;

import java.util.List;
import java.util.stream.IntStream;

public class InsaneColouredTriangles {

    public static char triangle(final String row) {

        List<Character> characters = row.chars().mapToObj(c -> (char) c).toList();

        return triangleAux(characters);
    }

    private static char triangleAux(List<Character> characters) {

        double v = Math.floor(Math.log10(characters.size() - 1) / Math.log10(3));

        if (v == 0 || Double.isInfinite(v)) {

            return triangleAux2(characters);
        } else {

            int v1 = (int) Math.pow(3, v) + 1;

            List<Character> finalCharacters = characters;
            characters = IntStream.rangeClosed(0, characters.size() - v1)
                    .mapToObj(i -> getColour(finalCharacters.get(i), finalCharacters.get(i + v1 - 1)))
                    .toList();

            return triangleAux(characters);
        }
    }

    private static char triangleAux2(List<Character> characters) {

        while (characters.size() > 1) {
            List<Character> finalCharacters = characters;
            characters = IntStream.range(0, characters.size() - 1)
                    .mapToObj(i -> getColour(finalCharacters.get(i), finalCharacters.get(i + 1)))
                    .toList();
        }

        return characters.get(characters.size() - 1);
    }

    private static char getColour(char a, char b) {

        if (a == b) {
            return a;
        } else {
            if ((a == 'R' && b == 'B') || (a == 'B' && b == 'R')) {
                return 'G';
            } else if ((a == 'G' && b == 'B') || a == 'B' && b == 'G') {
                return 'R';
            } else {
                return 'B';
            }
        }
    }
}
