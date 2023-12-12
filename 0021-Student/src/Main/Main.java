/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;


import BO.ManageStudent;
import util.Utility;
import view.View_Student;


public class Main {

    public static void main(String[] args) {
        View_Student view = new View_Student();
        Utility input = new Utility();
        ManageStudent manager = new ManageStudent();
        //view.View_Student view = new View_Student();
        while (true) {
            displayMenu();
            //manager.ManageStudent();
            int option = input.inputChoice("Enter the choice: ", 1, 6);
            switch (option) {
                case 1:
                    //create 
                    view.inputStudent();
                    break;
                case 2:
                    //find and sort
                    view.findAndSort();
                    break;
                case 3:
                    //update or delte
                    view.updateOrDelete();
                    break;
                case 4:
                    //report
                    view.report();
                    break;
                case 5:
                    //display list 
                    view.hienthi();
                    break;
                case 6:
                    //exit
                    System.out.println("THANK YOU!!!");
                    System.exit(0);
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("+==========MENU===========+");
        System.out.println("|1. Create                |");
        System.out.println("|2. Find and sort         |");
        System.out.println("|3. Update/Delete         |");
        System.out.println("|4. Report                |");
        System.out.println("|5. Display               |");
        System.out.println("|6. Exit                  |");
        System.out.println("+=========================+");
    }
}
