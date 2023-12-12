/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1sp0050;

import static j1sp0050.Equation.isOdd;
import static j1sp0050.Equation.isPerfectSquare;
import java.util.List;

/**
 *
 * @author Thành Vinh
 */
public class OutPut {

    public void handleOutput(List<Float> listFloat) {
        String outputOdd = "";
        String outputEven = "";
        String outputPerfectNum = "";

        for (float number : listFloat) {
            if (isOdd(number)) {
                outputOdd += number + ", ";
            } else {
                outputEven += number + ", ";
            }

            if (isPerfectSquare(number)) {
                outputPerfectNum += number + ", ";
            }
        }
        
        if (!outputOdd.isEmpty()) {
            System.out.println("Odd number: "
                    + outputOdd.substring(0, outputOdd.length() - 2));
        }

        if (!outputEven.isEmpty()) {
            System.out.println("Even number: "
                    + outputEven.substring(0, outputEven.length() - 2));
        }

        if (!outputPerfectNum.isEmpty()) {
            System.out.print("Perfect number: "
                    + outputPerfectNum.substring(0, outputPerfectNum.length() - 2));
        }

    }
}
