package kyu4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixDeterminantTest {

    private static final int[][][] matrix = {{{1}},
            {{1, 3}, {2, 5}},
            {{2, 5, 3}, {1, -2, -1}, {1, 3, 4}}};

    private static final int[] expected = {1, -1, -20};

    private static final String[] msg = {"Determinant of a 1 x 1 matrix yields the value of the one element",
            "Should return 1 * 5 - 3 * 2 == -1 ",
            ""};

    @Test
    public void sampleTests() {
        for (int n = 0; n < expected.length; n++)
            assertEquals(expected[n], MatrixDeterminant.determinant(matrix[n]), msg[n]);
    }
}
