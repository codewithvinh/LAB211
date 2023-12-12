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
import java.util.Scanner;

public class Validation {

    Scanner sc = new Scanner(System.in);

    public int getchoice(int min, int max) {
        int choice;
        while (true) {
            System.out.print("Your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice < min || choice > max) {
                    throw new NumberFormatException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.err.println("Number out of range!");
            }
        }
    }

    public int checkInputMatrix(String matrix, int i, int j) {
        int n;
        while (true) {
            System.out.print("Enter " + matrix + "[" + (i + 1) + "]" + "[" + (j + 1) + "]: ");
            try {
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.err.println("Value of matrix is digit");
            }
        }
    }

    public int checkInputRowAndColumn(String msg) {
        int n;
        while (true) {
            System.out.print(msg);
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0) {
                    throw new NumberFormatException();
                }
                return n;
            } catch (NumberFormatException e) {
                System.err.println("Row or column of matrix must be a digit greater than 0");
            }
        }
    }

    public int checkRowAndColumn(String msg, int input) {
        while (true) {
            int value = checkInputRowAndColumn(msg);
            if (value == input) {
                return value;
            } else {
                System.err.println("You must input equal row or column with matrix 1");
            }
        }
    }

}
