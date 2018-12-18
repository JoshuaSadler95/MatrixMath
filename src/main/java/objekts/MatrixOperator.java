package objekts;


public class MatrixOperator {

    public static Matrix matrixAdd(Matrix m1, Matrix m2) throws Exception{
        if(m1.getNumberOfColums() != m2.getNumberOfColums() || m1.getNumberOfRows() !=m2.getNumberOfRows()){
            throw new Exception(){
                @Override
                public String toString(){
                    return "Illegal Formats!";
                }
            };
        } else {

            double[][] A_Add_B = new double[m1.getNumberOfRows()][m1.getNumberOfColums()];
            double[][] A = m1.getMatrix();
            double[][] B = m2.getMatrix();

            for(int i = 0 ; i<m1.getNumberOfRows() ; i++){
                for(int j = 0 ; j<m1.getNumberOfColums() ; j++){
                    A_Add_B[i][j] = A[i][j]+B[i][j];
                }
            }
            return new Matrix(A_Add_B);
        }
    }

    public static Matrix matrixSubtakt(Matrix m1, Matrix m2) throws Exception{
        return matrixAdd(m1,matrixSkalarMult(m2,-1));
    }

    public static Matrix matrixSkalarMult(Matrix matrix, double skalar){
        double[][] skalar_Mult_matrix = new double[matrix.getNumberOfRows()][matrix.getNumberOfColums()];
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColums(); j++) {
                skalar_Mult_matrix[i][j] = (skalar * matrix.getMatrix()[i][j]);
            }
        }
        return new Matrix(skalar_Mult_matrix);
    }

    public static Matrix matrixTransformation(Matrix matrix){
        Matrix matrixTransformd = new Matrix(matrix.getNumberOfColums(),matrix.getNumberOfRows());
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColums(); j++) {
                matrixTransformd.setPos(j,i,matrix.getPos(i,j));
            }
        }
        return matrixTransformd;
    }

    public static Matrix matrixMult(Matrix matrix1, Matrix matrix2) throws Exception {
        if(matrix1.getNumberOfColums() != matrix2.getNumberOfRows()) throw new Exception();
        Matrix mult = new Matrix(matrix1.getNumberOfRows(), matrix2.getNumberOfColums());
        for (int i = 0; i < mult.getNumberOfRows(); i++) {
            for (int j = 0; j < mult.getNumberOfColums(); j++) {
                double number = 0;
                for (int k = 0; k < matrix2.getNumberOfRows(); k++) {
                    number += (matrix1.getPos(i,k)*matrix2.getPos(k,j));
                }
                mult.setPos(i,j,number);
            }
        }
        return mult;
    }

    public static Matrix matrixUntermatrix(Matrix matrix, int row, int coulum) throws Exception{
        if(matrix.getNumberOfRows() != matrix.getNumberOfColums()) throw new Exception();
        Matrix unterMatrix = new Matrix(matrix.getNumberOfRows()-1,matrix.getNumberOfColums()-1);

        int i_=0;
        int j_=0;

        for (int i = 0; i < unterMatrix.getNumberOfRows(); i++) {
            if(i>=row) i_ = 1;
            else i_ = 0;
            for (int j = 0; j < unterMatrix.getNumberOfColums(); j++) {
                if(j>=coulum) j_ = 1;
                else j_ = 0;
                unterMatrix.setPos(i,j,matrix.getPos((i+i_),(j+j_)));
            }
        }

        return unterMatrix;
    }

    public static double matrixDet(Matrix matrix) throws Exception{
        double[][] mx = matrix.getMatrix();
        return matrixDetArray(mx);
    }

    public static Matrix invert(Matrix m) throws Exception{
        if(matrixDet(m) == 0) throw new Exception();
        double[][] invert = invert(m.getMatrix());
        return new Matrix(invert);
    }

    private static double matrixDetArray(final double[][] matrix){
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Die Matix ist nicht quadratisch!");
        }
        int n = matrix.length;
        double[][] subMatrix;
        double det = 0;
        if (n == 1) {
            det = matrix[0][0];
            return det;
        } else if (n == 2) {
            det = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
            return det;
        }
        for (int i = 0; i < n; i++) {
            subMatrix = new double[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k < i) {
                        subMatrix[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        subMatrix[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            det += (matrix[0][i] * Math.pow(-1, i) * matrixDetArray(subMatrix));
        }
        return det;
    }

    private static double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i)
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    private static void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }
}
