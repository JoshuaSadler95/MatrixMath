package objekts;

public class Matrix {
    private int numberOfColums = 0;
    private int numberOfRows = 0;
    private double[][] matrix;

    public Matrix(int numberOfRows , int numberOfColums , String numbers){
        this.numberOfRows = numberOfRows;
        this.numberOfColums = numberOfColums;
        matrix = new double[numberOfRows][numberOfColums];
        fillMatrix(numbers);
    }

    public Matrix(int numberOfRows, int numberOfColums){
        this.numberOfColums = numberOfColums;
        this.numberOfRows = numberOfRows;
        matrix = new double[numberOfRows][numberOfColums];
    }

    public Matrix(double[][] matrix){
        setMatrix(matrix);
    }

    public int getNumberOfRows(){
        return numberOfRows;
    }

    public int getNumberOfColums(){
        return numberOfColums;
    }

    public double getPos(int row, int colum){
        if(row>=numberOfRows || colum>=numberOfColums){
            throw new IndexOutOfBoundsException();
        }
        return matrix[row][colum];
    }

    public void setPos(int row, int colum, double number){
        if(row>=numberOfRows || colum>=numberOfColums){
            throw new IndexOutOfBoundsException();
        }
        matrix[row][colum] = number;
    }

    public double[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(double[][] matrix){
        this.matrix = matrix;
        numberOfColums = matrix[0].length;
        numberOfRows = matrix.length;
    }

    public void fillMatrix(String s){
        String[] rows = s.split(";");
        for(int rowNumber = 0 ; rowNumber < numberOfRows ; rowNumber++){
            String row = rows[rowNumber];
            String[] numbers = row.split(",");
            for(int columNumber = 0 ; columNumber<numberOfColums ; columNumber++){
                double number = Double.parseDouble(numbers[columNumber]);
                matrix[rowNumber][columNumber] = number;
            }
        }
    }

    public void printMatrix(){
        for(int rowNumber = 0 ; rowNumber < numberOfRows ; rowNumber++){
            String line = "";
            for(int columNumber = 0 ; columNumber<numberOfColums ; columNumber++){
                line += matrix[rowNumber][columNumber]+" ";
            }
            System.out.println(line);
        }
    }

}
