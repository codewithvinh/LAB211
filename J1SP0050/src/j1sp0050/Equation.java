/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1sp0050;

import java.util.List;

/**
 *
 * @author Thành Vinh
 */
public class Equation {

    static final Validation CHECK = new Validation();
    OutPut out = new OutPut();
    
    public static boolean isOdd(float number) {
        return (number % 2) != 0;
    }

    public static boolean isPerfectSquare(float number) {
        int temp = (int) Math.sqrt(number);
        return number == temp * temp;
    }

    public void outputEquation(List<Float> listFloat) {
        System.out.println("--------Caculate Equation---------");
        float a = CHECK.getFloat("Enter A: ", -Float.MAX_VALUE, Float.MAX_VALUE);
        float b = CHECK.getFloat("Enter B: ", -Float.MAX_VALUE, Float.MAX_VALUE);
        out.handleOutput(caculateEquation(listFloat, a, b));
        System.out.println();
    }

    public List<Float> caculateEquation(List<Float> listFloat, float a, float b) {
        listFloat.add(a);
        listFloat.add(b);

        if (a == 0) {
            if (b == 0) {
                System.out.println("Infinite many solutions!");
                System.out.println("empty");
            } else {
                System.out.println("No solution!");
                System.out.println("null");
            }
        } else {
            float x = -b / a;
            listFloat.add(x);
            System.out.println("Solution: x = " + x);
        }
        return listFloat;
    }

}
