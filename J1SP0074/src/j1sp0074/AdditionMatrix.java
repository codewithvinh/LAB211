/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1sp0074;

/**
 *
 * @author Thành Vinh
 */
public class AdditionMatrix {

    InputAndOutputMatrix inout = new InputAndOutputMatrix();
    Validation v = new Validation();

    public int[][] getMatrix1() {
        int row1 = v.checkInputRowAndColumn("Enter Row Matrix 1: ");
        int col1 = v.checkInputRowAndColumn("Enter Column Matrix 1: ");
        int[][] matrix1 = inout.getMatrix(row1, col1);
        return matrix1;
    }

    public int[][] getMatrix2() {
        int row2 = v.checkInputRowAndColumn("Enter Row Matrix 1: ");
        int col2 = v.checkInputRowAndColumn("Enter Column Matrix 1: ");
        int[][] matrix2 = inout.getMatrix(row2, col2);
        return matrix2;
    }

    public void displayMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println();
        }
    }

    public int[][] additionMatrix(int[][] matrix1, int[][] matrix2) {
        int row1 = matrix1.length;
        int col1 = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;
        if (row1 != row2 && col1 != col2) {
            System.out.println("Can not add two matrix");
        }
        int[][] result = new int[row1][col1];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }
}
