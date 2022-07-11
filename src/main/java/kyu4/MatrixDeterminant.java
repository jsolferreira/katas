package kyu4;

public class MatrixDeterminant {

    public static int determinant(int[][] matrix) {

        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0];
        }

        int[][] m = new int[matrix.length - 1][matrix[0].length - 1];
        Integer determinant = null;

        for (int k = 0; k < matrix[0].length; k++) {

            for (int i = 1; i < matrix.length; i++) {
                int l = 0;
                for (int j = 0; j < matrix[i].length; j++) {
                    if (j == k) continue;
                    m[i - 1][l++] = matrix[i][j];
                }
            }

            if (determinant == null) {
                determinant = matrix[0][k] * determinant(m);
            } else {
                if (k % 2 != 0) {
                    determinant -= matrix[0][k] * determinant(m);
                } else {
                    determinant += matrix[0][k] * determinant(m);
                }
            }
        }

        return determinant;
    }
}
