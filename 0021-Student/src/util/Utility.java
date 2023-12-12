package util;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Utility {

    static Scanner sc = new Scanner(System.in);

    public int inputChoice(String msg, int min, int max) {
        while (true) {

            try {
                System.out.print(msg);
                int input = Integer.parseInt(sc.nextLine());
                if (input < min || input > max) {
                    System.out.println("Your choice must be between " + min + " to " + max);
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Choice must be a digit!");
            }
        }
    }

    public String inputString(String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input can't be null!!");
                continue;
            }
            return input.trim();
        }
    }

    public int checkInt(String mess) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            try {
                System.out.print(mess);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.err.println("Please enter a digit");
            }
        }
    }

    public double checkDouble(String mess) {
        Scanner sc = new Scanner(System.in);
        double n;
        while (true) {
            try {
                System.out.print(mess);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.err.println("Please enter a digit");
            }
        }
    }
    
    public boolean checkYN() {
        while (true) {
            String a = inputString("Do you want to continue (Y/N)? Choose Y to continue, N to return main screen: ");
            if (a.equalsIgnoreCase("Y")) {
                return true;
            }
            if (a.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("You must be enter Y/y or N/n, please re-enter.");
        }
    }
}
