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
public class InputAndOutputMatrix {

    Validation v = new Validation();

    public int[][] getMatrix(int row, int col) {
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = v.checkInputMatrix("", i, j);
            }
        }
        return matrix;
    }

    /*
    public void displayResult(int[][] matrix1, int[][] matrix2, int choice, int[][] result) {
        String expression = null;
        switch (choice) {
            case 1:
                expression = "+";
                break;
            case 2:
                expression = "-";
                break;
            case 3:
                expression = "*";
                break;
        }

        System.out.println("------- Result -------");
        displayMatrix(matrix1);
        System.out.println(expression);
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(result);
    }
     */
}
