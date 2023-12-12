/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import BO.ManageStudent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Report;
import model.Student;
import util.Utility;

public class View_Student {

    ManageStudent manage = new ManageStudent();
    Utility input = new Utility();

    public void inputStudent() {
        //check list size is 10
        if (manage.getList().size() >= 10) {
            System.out.println("List student is: " + manage.getList().size());
            if (input.checkYN() == false) {
                return;
            }
        }

        //input student
        String id = input.inputString("Enter ID: ");

        //find record have id in list
        String name = manage.getStudentNameByID(id);
        //if name == null => (input name ) 
        if (name == null) {
            name = input.inputString("Enter name: ");
        } else {
            System.out.println("Enter name: " + name);
        }

        int semester = input.inputChoice("Enter semester: ", 1, 15);
        System.out.println("1. Java");
        System.out.println("2. .Net");
        System.out.println("3. C/C++");
        int course = input.inputChoice("Enter course: ", 1, 3);

        //add to list
        boolean result = manage.checkCreateStudent(id, name, semester, course);
        if (result == false) {
            System.out.println("Duplicate !!!");
        } else {
            System.out.println("SUCCESSFUL");
        }
    }

// sắp xếp theo tên ko theo họ
    public void findAndSort() {
        //input name
        String name = input.inputString("Enter name: ");
        //search by name
        List<Student> listSearch = manage.getStudentByName(name);

        //if listsearch empty => not found
        if (listSearch.isEmpty()) {
            System.out.println("NOT FOUND");
            return;
        }

        //sort by name
        Collections.sort(listSearch, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        //display
        System.out.printf("%-5s | %-15s | %-10s | %-10s\n", "ID", "Name", "Semester", "Course");
        for (Student student : listSearch) {
            System.out.println(student);
        }
    }

    public void updateOrDelete() {
        //input id
        String id = input.inputString("Enter id: ");

        //find students by ID
        List<Student> listFoundByID = manage.getStudentByID(id);
        if (listFoundByID.isEmpty()) {
            System.out.println("NOT FOUND !!!");
        } else {
            // check update or delete
            //U = true || D = false
            if (checkUpdateOrDelete()) {
                updateStudent(listFoundByID);
            } else {
                deleteStudent(listFoundByID);
            }
        }
    }

    private void deleteStudent(List<Student> listFoundByID) {
        displayListFoundByID(listFoundByID);

        int choice = input.inputChoice("Enter record you want to delete: ", 1, listFoundByID.size());
        Student student = listFoundByID.get(choice - 1);

        manage.removeStudent(student);
        System.out.println("DELETE SUCCESSFULL !!!");
    }
// ko duplicate báo duplicate

    private void updateStudent(List<Student> listFoundByID) {
        displayListFoundByID(listFoundByID);
        //Enter record you want to delete: 
        int choice = input.inputChoice("Enter record you want to update: ", 1, listFoundByID.size());

        Student student = listFoundByID.get(choice - 1);

        //get properties of student
        String idUpdate = student.getId();
        String nameUpdate = student.getName();
        int semesterUpdate = student.getSemester();
        int courseUpdate = student.getCourse();

        //check user want to update ?
        if (checkWantUpdate("id")) {
            idUpdate = input.inputString("Enter id: ");
        }
        if (checkWantUpdate("name")) {
            nameUpdate = input.inputString("Enter name: ");
        }
        if (checkWantUpdate("semester")) {
            semesterUpdate = input.checkInt("Enter semester: ");
        }
        if (checkWantUpdate("course")) {
            System.out.println("1. Java");
            System.out.println("2. .Net");
            System.out.println("3. C/C++");
            courseUpdate = input.inputChoice("Enter course: ", 1, 3);
        }

        //check not update
        if (manage.checkNotUpdate(idUpdate, nameUpdate, semesterUpdate, courseUpdate, student)) {
            System.out.println("YOU NOT UPDATE !!!");
        } // check duplicate
        else if (manage.checkDuplicate(idUpdate, nameUpdate, semesterUpdate, courseUpdate)) {
            System.out.println("DUPLICATE !!!");
        } else {
            //update
            student.setId(idUpdate);
            manage.updateAllName(nameUpdate, idUpdate);
            student.setSemester(semesterUpdate);
            student.setCourse(courseUpdate);
            System.out.println("UPDATE SUCCESFULL");
        }

    }

    private boolean checkUpdateOrDelete() {
        while (true) {
            String a = input.inputString("Do you want to update ( U ) or delte (D)? ");
            if (a.equalsIgnoreCase("U")) {
                return true;
            }
            if (a.equalsIgnoreCase("D")) {
                return false;
            }
            System.out.println("You must be enter U/u or D/d, please re-enter.");
        }
    }

    private void displayListFoundByID(List<Student> listFoundByID) {

        System.out.printf("%-5s | %-5s | %-15s | %-10s | %-10s\n", "No", "ID", "Name", "Semester", "Course");

        for (int i = 0; i < listFoundByID.size(); i++) {
            System.out.printf("%-5s | %s\n", i + 1, listFoundByID.get(i));
        }
    }

    public void hienthi() {
        displayListFoundByID(manage.getList());
    }

    private boolean checkWantUpdate(String message) {

        while (true) {
            String a = input.inputString("Do you want to update " + message + "?(Y/N) ");
            if (a.equalsIgnoreCase("Y")) {
                return true;
            }
            if (a.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("You must be enter Y/y or N/n, please re-enter.");
        }
    }

    public void report() {
        List<Report> reports = manage.addReport();
        System.out.printf("%-15s | %-5s | %-10s\n", "Name", "Course", "Total Course");
        for (Report o : manage.getListReport()) {
            System.out.println(o);
        }

    }
}
