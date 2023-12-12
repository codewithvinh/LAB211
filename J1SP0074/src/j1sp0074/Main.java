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
public class Main {

    public static void main(String[] args) {
        Validation v = new Validation();
        MatrixCalculation matrix = new MatrixCalculation();
        InputAndOutputMatrix io = new InputAndOutputMatrix();
        AdditionMatrix add = new AdditionMatrix();
        int choice;
        int[][] matrix1;
        int[][] matrix2;
        int[][] result;
        while (true) {
            System.out.println("========Calculator program========");
            System.out.println("1. Addition Matrix");
            System.out.println("2. Subtraction Matrix");
            System.out.println("3. Multiplication Matrix");
            System.out.println("4. Quit");
            choice = v.getchoice(1, 4);
            switch (choice) {
                case 1:
                    System.out.println("----- Addition -----");
                    matrix1 = add.getMatrix1();
                    matrix2 = add.getMatrix2();
                    result = add.additionMatrix(matrix1, matrix2);
                    add.displayMatrix(result);
                    break;
                case 2:
                    System.out.println("----- Subtraction -----");
                    matrix1 = io.getMatrix1();
                    matrix2 = io.getMatrix2(matrix1, 2);
                    result = matrix.subtractionMatrix(matrix1, matrix2);
                    io.displayResult(matrix1, matrix2, choice, result);
                    break;
                case 3:
                    System.out.println("----- Multiplication -----");
                    matrix1 = io.getMatrix1();
                    matrix2 = io.getMatrix2(matrix1, 3);
                    result = matrix.multiplicationMatrix(matrix1, matrix2);
                    io.displayResult(matrix1, matrix2, choice, result);
                    break;
                case 4:
                    System.out.println("Thanks for using the program!");
                    return;
            }
        }
    }

}
