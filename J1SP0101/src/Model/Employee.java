package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Thành Vinh
 */
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String dob;
    private String sex;
    private double salary;
    private String agency;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String phone, String email, String address, String dob, String sex, double salary, String agency) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.sex = sex;
        this.salary = salary;
        this.agency = agency;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getSex() {
        return sex;
    }

    public String getAgency() {
        return agency;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getSalary() {
        return salary;
    }

    // Add getters and setters if needed
    @Override
    public String toString() {
        return String.format("%-5s | %-15s | %-15s | %-12s | %-25s | %-30s | %-12s | %-6s | %-10s | %-20s\n",
                "ID", "First Name", "Last Name", "Phone", "Email", "Address", "DOB", "Sex", "Salary", "Agency")
                + String.format("%-5d | %-15s | %-15s | %-12s | %-25s | %-30s | %-12s | %-6s | %-10.2f | %-20s\n",
                        id, firstName, lastName, phone, email, address, dob, sex, salary, agency);
                
    }

}
