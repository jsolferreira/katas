package kyu7;

import java.util.ArrayList;

public class ColouredTriangles {

    public static char triangle(final String row) {

        if (row.length() == 1) {

            return row.charAt(0);
        }

        final ArrayList<Character> characters = new ArrayList<>();

        for (int i = 0; i < row.length() - 1; i++) {
            char colour = getColour(row.charAt(i), row.charAt(i + 1));
            for (int j = 0; j < characters.size(); j++) {
                char prevColour = characters.get(j);
                characters.set(j, colour);
                colour = getColour(prevColour, colour);
            }
            characters.add(colour);
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
