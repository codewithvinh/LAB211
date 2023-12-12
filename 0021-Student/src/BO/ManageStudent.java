/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Report;
import model.Student;

public class ManageStudent {

    List<Student> list = new ArrayList<>();
    List<Report> listReport = new ArrayList<>();

    //Student student = new Student();
    public List<Report> getListReport() {
        return listReport;
    }

    public ManageStudent() {
        Student s1 = new Student("1", "Nguyen Thanh A", 1, 1);
        list.add(s1);
        Student s2 = new Student("2", "Tran Van B", 1, 1);
        list.add(s2);
        Student s3 = new Student("1", "Nguyen Thi B", 3, 2);
        list.add(s3);
        Student s4 = new Student("1", "Nguyen Van B", 2, 3);
        list.add(s4);
        Student s5 = new Student("2", "Tran Van C", 1, 3);
        list.add(s5);
        Student s6 = new Student("3", "Trinh Van D", 1, 3);
        list.add(s6);
        Student s7 = new Student("3", "Do Phan Ha Giang", 3, 2);
        list.add(s7);

        HashMap<String, ArrayList<Student>> map = createStudentMap(list);
        updateStudentNames(map);

    }

    public static HashMap<String, ArrayList<Student>> createStudentMap(List<Student> list) {
        HashMap<String, ArrayList<Student>> map = new HashMap<>();

        // trong đó key của HashMap là ID của sinh viên và value 
        // là danh sách các sinh viên có cùng ID.
        //Thêm sinh viên vào map
        for (Student student : list) {
            /*
            Với mỗi sinh viên trong danh sách list, ta kiểm tra xem hashmap
            đã chứa ID của sinh viên này chưa bằng cách gọi 
            phương thức containsKey(). 
            Nếu chưa có, ta sẽ thêm một entry mới
            vào hashmap với key là ID của sinh viên này và value 
            là một ArrayList rỗng.
             */
            if (!map.containsKey(student.getId())) {
                map.put(student.getId(), new ArrayList<Student>());
            }
            map.get(student.getId()).add(student);
        }

        return map;
    }

    // Cập nhật tên cho các sinh viên có ID trùng nhau
    public static void updateStudentNames(HashMap<String, ArrayList<Student>> map) {
        for (ArrayList<Student> students : map.values()) {
            /*
            Kiểm tra xem danh sách sinh viên có lớn hơn 1 ko
            nếu không thì do nothing
            nếu lớn hơn 1 thì sẽ lấy student id đầu tiên của list
            sau đó update all name của student có cùng id 
             */
            if (students.size() > 1) {
                String id = students.get(0).getId();
                String name = students.get(0).getName();
                for (Student student : students) {
                    if (student.getId().equals(id)) {
                        student.setName(name);
                    }
                }
            }
        }
    }

    public List<Student> getList() {
        return list;
    }

    public boolean checkCreateStudent(String id, String name, int semester, int course) {

        //check duplicate => true:  return false || false: return true
        if (checkDuplicate(id, name, semester, course)) {
            System.out.println("Student duplicate: " + id + " | " + name + " | " + semester + " | " + course);
            return false;
        } else {
            Student student = new Student(id, name, semester, course);
            list.add(student);
            return true;
        }
    }

    public boolean checkDuplicate(String id, String name, int semester, int course) {
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id)
                    && student.getSemester() == semester
                    && student.getCourse() == course
                    && student.getName().equalsIgnoreCase(name)) {

                return true;
            }
        }
        return false;

    }

    public String getStudentNameByID(String id) {
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student.getName();
            }
        }
        return null;
    }

    public String ten(String str) {
        String[] ten = str.split(" ");
        return ten[ten.length - 1];
    }

    public List<Student> getStudentByName(String name) {
        List<Student> listSearch = new ArrayList<>();
        for (Student student : list) {
            String ten = ten(student.getName());
            if (ten.toUpperCase().contains(name.toUpperCase())) {
                listSearch.add(student);
            }
        }
        return listSearch;
    }

    public List<Student> getStudentByID(String id) {
        List<Student> listSearch = new ArrayList<>();

        //loop from first to last element in list
        for (Student student : list) {
            //if student has id equal to id ( parameter ) => add to listSearch
            if (student.getId().equalsIgnoreCase(id)) {
                listSearch.add(student);
            }
        }
        return listSearch;
    }

    public void removeStudent(Student student) {
        list.remove(student);
    }

    public boolean checkNotUpdate(String idUpdate, String nameUpdate, int semesterUpdate,
            int courseUpdate, Student student) {

        //check all properties of student equal to parameter 
        if (student.getId().equalsIgnoreCase(idUpdate)
                && student.getName().equalsIgnoreCase(nameUpdate)
                && student.getSemester() == semesterUpdate
                && student.getCourse() == courseUpdate) {
            return true;
        } else {
            return false;
        }
    }

    public void updateAllName(String nameUpdate, String idUpdate) {
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(idUpdate)) {
                student.setName(nameUpdate);
            }
        }
    }

    public boolean checkUpdateByID(String nameUpdate, String idUpdate) {
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(idUpdate) && !student.getName().equalsIgnoreCase(nameUpdate)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistReport(Student student) {
        for (Report report : listReport) {
            if (report.getId().equalsIgnoreCase(student.getId())
                    && report.getCourse() == student.getCourse()) {
                return true;
            }
        }
        return false;
    }

    public List<Report> addReport() {
        listReport.clear();
        for (Student first : getList()) {
            if (checkExistReport(first) == false) {
                int total = 0;

                for (Student second : getList()) {
                    if (first.getId().equalsIgnoreCase(second.getId())
                            && first.getCourse() == second.getCourse()) {
                        total++;
                    }
                }
                Report report = new Report(first.getId(), first.getName(), first.getCourse(), total);
                listReport.add(report);
            }
        }
        return listReport;
    }
}
