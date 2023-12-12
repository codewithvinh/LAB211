
import Model.Employee;
import Validation.Utility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EmployeeManagement {

    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Utility input = new Utility();
    
    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    searchEmployees();
                    break;
                case 5:
                    sortEmployeesBySalary();
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static void addEmployee() {
        System.out.println("Enter Employee ID:");
        int id = input.checkInt("Enter ID: ");

        if (findEmployeeById(id) != null) {
            System.out.println("Employee with this ID already exists. Please enter a unique ID.");
            return;
        }

        
        String firstName = input.inputString("Enter First Name: ");

        String lastName = input.inputString("Enter Last Name: ");

        String phone = input.inputString("Enter Phone: ");

        String email = input.inputString("Enter Email: ");

        String address = input.inputString("Enter Address: ");

        String dob = input.inputString("Enter Date of Birth: ");

        String sex = input.inputString("Enter Sex: ");

        double salary = input.checkDouble("Enter Salary: ");

        String agency = input.inputString("Enter Agency: ");

        Employee newEmployee = new Employee(id, firstName, lastName, phone, email, address, dob, sex, salary, agency);
        employees.add(newEmployee);

        System.out.println("Employee added successfully.");
    }
    
    private static void displayMenu() {
        System.out.println("Main menu:");
        System.out.println("1. Add employees");
        System.out.println("2. Update employees");
        System.out.println("3. Remove employees");
        System.out.println("4. Search employees");
        System.out.println("5. Sort employees by salary");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static Employee findEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    private static void updateEmployee() {
        System.out.println("Enter Employee ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Employee employeeToUpdate = findEmployeeById(id);
        if (employeeToUpdate == null) {
            System.out.println("Employee with this ID not found.");
            return;
        }

        System.out.println("Enter New First Name:");
        String firstName = scanner.nextLine();
        employeeToUpdate.setFirstName(firstName);

        System.out.println("Enter New Last Name:");
        String lastName = scanner.nextLine();
        employeeToUpdate.setLastName(lastName);

        // Update other properties if needed
        System.out.println("Employee details updated successfully.");
    }

    private static void removeEmployee() {
        System.out.println("Enter Employee ID to remove:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Employee employeeToRemove = findEmployeeById(id);
        if (employeeToRemove == null) {
            System.out.println("Employee with this ID not found.");
            return;
        }

        employees.remove(employeeToRemove);
        System.out.println("Employee removed successfully.");
    }

    private static void searchEmployees() {
        System.out.println("Enter Name (First Name or Last Name) or a part of name to search:");
        String searchQuery = scanner.nextLine();

        ArrayList<Employee> searchResults = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getFirstName().toLowerCase().contains(searchQuery.toLowerCase())
                    || emp.getLastName().toLowerCase().contains(searchQuery.toLowerCase())) {
                searchResults.add(emp);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No matching employees found.");
        } else {
            System.out.println("Search Results:");
            for (Employee emp : searchResults) {
                System.out.println(emp);
            }
        }
    }

    private static void sortEmployeesBySalary() {
        Collections.sort(employees, Comparator.comparingDouble(Employee::getSalary));

        System.out.println("Employees sorted by salary:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
