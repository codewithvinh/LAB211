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
public class QuadraticEquation {
    
    
    static final Validation CHECK = new Validation();
    OutPut out = new OutPut();
    Equation euquation = new Equation();
    
    public void outputQuadratic(List<Float> listFloat) {
        System.out.println("---------Caculate Quadratic Equation-------");
        float a = CHECK.getFloat("Enter A: ", -Float.MAX_VALUE, Float.MAX_VALUE);
        float b = CHECK.getFloat("Enter B: ", -Float.MAX_VALUE, Float.MAX_VALUE);
        float c = CHECK.getFloat("Enter C: ", -Float.MAX_VALUE, Float.MAX_VALUE);
        out.handleOutput(caculateQuadraticEquation(listFloat, a, b, c));
        System.out.println();

    }

    public List<Float> caculateQuadraticEquation(List<Float> listFloat, float a, float b, float c) {
        float x1, x2;
        final float delta = b * b - 4 * a * c;
        listFloat.add(a);

        if (a == 0) {
            euquation.caculateEquation(listFloat, b, c);
        } else {
            listFloat.add(b);
            listFloat.add(c);
            if (delta < 0) {
                System.out.println("No solution!");
            } else if (delta == 0) {
                x1 = -b / (2 * a);
                x2 = x1;
                listFloat.add(x1);
                listFloat.add(x2);
                System.out.println("Solution x1 = " + x1 + " and x2 = " + x2);
            } else {
                x1 = (float) ((-b - Math.sqrt(delta)) / (2 * a));
                x2 = (float) ((-b + Math.sqrt(delta)) / (2 * a));
                listFloat.add(x1);
                listFloat.add(x2);
                System.out.println("Equation has 2 different solutions: x1 = " + x1 + " and x2 = " + x2);
            }
        }
        return listFloat;
    }
}
