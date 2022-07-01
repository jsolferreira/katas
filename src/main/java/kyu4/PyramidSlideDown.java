package kyu4;

public class PyramidSlideDown {

    public static int longestSlideDown(int[][] pyramid) {

        final int pyramidSize = pyramid.length;

        int[] nRow = pyramid[pyramidSize - 1];

        for (int i = pyramidSize - 1; i > 0; i--) {
            final int[] nextRow = pyramid[i - 1];
            final int nextRowLength = nextRow.length;

            for (int j = 0; j <= nextRowLength; j++) {

                if (j != 0) {

                    final int s1 = nRow[j] + nextRow[j - 1];

                    if (nRow[j - 1] < s1) {
                        nRow[j - 1] = s1;
                    }
                }

                if (j != nextRowLength) {

                    nRow[j] = nRow[j] + nextRow[j];
                }
            }
        }

        return nRow[0];
    }
}
