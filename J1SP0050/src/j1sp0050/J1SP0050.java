/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1sp0050;

import java.util.ArrayList;

/**
 *
 * @author Thành Vinh
 */
public class J1SP0050 {

    /**
     * @param args the command line arguments
     */
    static final Equation EQUA = new Equation();
    static final QuadraticEquation QUA = new QuadraticEquation();
    static final Validation CHECK = new Validation();
    static final ArrayList<Float> LIST_FLOAT = new ArrayList<>();

    public static void main(String[] args) {
        Model();
    }

    public static void Model() {
        equationProgramMenu();
        int choose;
        do {
            choose = CHECK.getInt("Please enter choice one option 1-3: ", 1, 3);
            switch (choose) {
                case 1:
                    EQUA.outputEquation(LIST_FLOAT);
                    LIST_FLOAT.clear();
                    break;
                case 2:
                    QUA.outputQuadratic(LIST_FLOAT);
                    LIST_FLOAT.clear();
                    break;
                case 3:
                    return;
            }
        } while (choose != 3);

    }

    private static void equationProgramMenu() {
        System.out.println("==========Equation Program========\n"
                + "1. Caculate Superlative Equation\n"
                + "2. Caculate Quadratic Equation\n"
                + "3. Exit.");
    }

}
