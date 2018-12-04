package objekts;


public class MatrixOperator {

    public Matrix matrixAdd(Matrix m1, Matrix m2) throws Exception{
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

    public Matrix matrixSubtakt(Matrix m1, Matrix m2) throws Exception{
        return matrixAdd(m1,matrixSkalarMult(m2,-1));
    }

    public Matrix matrixSkalarMult(Matrix matrix, double skalar){
        double[][] skalar_Mult_matrix = new double[matrix.getNumberOfRows()][matrix.getNumberOfColums()];
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColums(); j++) {
                skalar_Mult_matrix[i][j] = (skalar * matrix.getMatrix()[i][j]);
            }
        }
        return new Matrix(skalar_Mult_matrix);
    }

    public Matrix matrixTransformation(Matrix matrix){
        Matrix matrixTransformd = new Matrix(matrix.getNumberOfColums(),matrix.getNumberOfRows());
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColums(); j++) {
                matrixTransformd.setPos(j,i,matrix.getPos(i,j));
            }
        }
        return matrixTransformd;
    }

    public Matrix matrixMult(Matrix matrix1, Matrix matrix2) throws Exception {
        if(matrix1.getNumberOfColums() != matrix2.getNumberOfRows()) throw new Exception();
        Matrix mult = new Matrix(matrix1.getNumberOfRows(), matrix2.getNumberOfColums());
        for (int i = 0; i < mult.getNumberOfRows(); i++) {
            for (int j = 0; j < mult.getNumberOfColums(); j++) {
                double number = 0;
                for (int k = 0; k < matrix1.getNumberOfColums(); k++) {
                    number += matrix1.getPos(j,k)*matrix2.getPos(k,j);
                }
                mult.setPos(j,i,number);
            }
        }
        return mult;
    }

}
