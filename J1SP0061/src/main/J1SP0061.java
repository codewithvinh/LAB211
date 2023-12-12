/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Thành Vinh
 */
import entity.Circle;
import entity.Rectangle;
import entity.Shape;
import entity.Triangle;
import java.util.Scanner;
import validation.Validation;

public class J1SP0061 {

    /**
     * @param args the command line arguments
     */
    final Scanner scanner = new Scanner(System.in);
    static final validation.Validation CHECK = new Validation();

    public static Rectangle inputRectangle() {
        double w,l;
        do {
            l = CHECK.getDouble("Please input length of Rectangle:", 0, Double.MAX_VALUE);
            w = CHECK.getDouble("Please input width of Rectangle:", 0, Double.MAX_VALUE);
            if (l < w) {
                System.out.println("Please input length must greater than width!");
            }
        } while (l < w);
        return new Rectangle(w, l);
    }

    public static Circle inputCircle() {
        double r = CHECK.getDouble("Please input radius of Circle:", 0, Double.MAX_VALUE);
        return new Circle(r);
    }

    public static Triangle inputTriangle() {
        double s1, s2, s3;

        do {
            s1 = CHECK.getDouble("Please input side A of Triangle:", 0, Double.MAX_VALUE);
            s2 = CHECK.getDouble("Please input side B of Triangle:", 0, Double.MAX_VALUE);
            s3 = CHECK.getDouble("Please input side C of Triangle:", 0, Double.MAX_VALUE);
            if(!((s1 + s2 > s3) && (s2 + s3 > s1) && (s1 + s3 > s2))){
                System.out.println("Please enter one side greater than the sum of two side remain");
            }
        } while (!((s1 + s2 > s3) && (s2 + s3 > s1) && (s1 + s3 > s2)));

        return new Triangle(s1, s2, s3);
    }

    public static void caculator() {
        System.out.println("==========Caculator Shape Program=========");
        Shape rec = inputRectangle();
        Shape ci = inputCircle();
        Shape tri = inputTriangle();
        rec.printResult();
        ci.printResult();
        tri.printResult();
    }

    public static void main(String[] args) {
        caculator();
    }

}
