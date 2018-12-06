import objekts.Matrix;
import objekts.MatrixOperator;
import org.junit.Test;

public class JUnitTests {
    @Test
    public void erstellenEinerMatrix(){

        Matrix matrix1 = new Matrix(3,3,"1,0,0;0,1,0;0,0,1");
        matrix1.printMatrix();

        System.out.println();

        Matrix matrix2 = new Matrix(matrix1.getMatrix());
        matrix2.printMatrix();


    }
    @Test
    public void additionVonMatrizen(){

        MatrixOperator MO = new MatrixOperator();

        Matrix matrix1 = new Matrix(3,3,"1,2,3;1,2,3;1,2,3");
        Matrix matrix2 = new Matrix(3,3,"3,2,1;-1,-2,-3;1,1,1");

        try{
            Matrix add = MO.matrixAdd(matrix1,matrix2);
            add.printMatrix();
        } catch (Exception e){
            System.out.println(e);
        }
        matrix2 = new Matrix(4,4);
        try{
            Matrix add = MO.matrixAdd(matrix1,matrix2);
            add.printMatrix();
        } catch (Exception e){
            System.out.println(e);
        }

    }
    @Test
    public void skalarMultiTest(){
        MatrixOperator MO = new MatrixOperator();

        Matrix matrix2 = new Matrix(3,3,"3,2,1;-1,-2,-3;1,1,1");
        try{
            Matrix sMult = MO.matrixSkalarMult(matrix2,5);
            sMult.printMatrix();
        }catch (Exception e){
            System.out.println(e);
        }

    }
    @Test
    public void subtraktionVonMatrizen(){

        MatrixOperator MO = new MatrixOperator();

        Matrix matrix1 = new Matrix(3,3,"1,2,3;1,2,3;1,2,3");
        Matrix matrix2 = new Matrix(3,3,"3,2,1;-1,-2,-3;1,1,1");

        try{
            Matrix sub = MO.matrixSubtakt(matrix1,matrix2);
            sub.printMatrix();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    @Test
    public void matrixTransponieren(){
        MatrixOperator MO = new MatrixOperator();

        Matrix matrix1 = new Matrix(3,3,"1,0,1;1,1,0;0,0,0");

        Matrix trans = MO.matrixTransformation(matrix1);
        trans.printMatrix();
    }
    @Test
    public void matrixSetGetPos(){
        Matrix matrix = new Matrix(1,1);
        try{
            matrix.setPos(2,2,0);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Catched IndexOutOfBoundsExeption!");
        }
        try{
            matrix.getPos(2,2);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Catched IndexOutOfBoundsExeption!");
        }
        matrix = new Matrix(3,3,"1,1,1;2,1,1;1,1,3");
        for(int i = 0 ; i < matrix.getNumberOfRows(); i++){
            for (int j = 0; j < matrix.getNumberOfColums(); j++) {
                System.out.print(matrix.getPos(i,j)+" ");
            }
            System.out.println();
        }
    }
    @Test
    public void matrixMult(){
        MatrixOperator MO = new MatrixOperator();

        Matrix matrix1 = new Matrix(3,3,"1,2,3;1,2,3;1,2,3");
        Matrix matrix2 = new Matrix(3,4,"1,2,3,4;1,2,3,4;1,2,3,4");

        try{
            matrix2.printMatrix();
            System.out.println();
            matrix1.printMatrix();
            System.out.println();
            Matrix mult = MO.matrixMult(matrix1,matrix2);
            mult.printMatrix();
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            System.out.println();
            matrix1 = new Matrix(2,2,"2,2;2,2");
            matrix2 = new Matrix(1,5,"1,2,3,4,5");

            matrix2.printMatrix();
            System.out.println();
            matrix1.printMatrix();
            System.out.println();
            Matrix mult = MO.matrixMult(matrix1,matrix2);
            mult.printMatrix();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Test
    public void matrixUntermatrix(){
        Matrix matrix = new Matrix(3,3,"1,1,1;1,1,2;1,3,4");
        matrix.printMatrix();
        System.out.println();

        MatrixOperator MO = new MatrixOperator();

        try{
            Matrix unterMatrix = MO.matrixUntermatrix(matrix,1,1);
            unterMatrix.printMatrix();
        } catch(Exception e){
            System.out.println(e);
        }
    }
    @Test
    public void matrixDet(){
        Matrix matrix = new Matrix(3,3,"3,1,6;-3,5,0;3,7,1");
        MatrixOperator MO = new MatrixOperator();

        matrix.printMatrix();
        System.out.println();

        try{
            System.out.println(MO.matrixDet(matrix));
        } catch (Exception e){
            System.out.println(e);
        }
        try{
            matrix = new Matrix(2,3,"1,2,3;1,2,3");
            MO.matrixDet(matrix);
        } catch (Exception e){
            System.out.println(e);
        }
        try{
            matrix = new Matrix(1,1,"1");
            MO.matrixDet(matrix);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
