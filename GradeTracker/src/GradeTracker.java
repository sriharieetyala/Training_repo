

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main application class for the CLI Grade Tracker.
 * Manages the user interface and student data.
 */
public class GradeTracker {

    // Requirement: Use a Collection (Map) to store students
    // This allows for fast lookup by student ID.
    private Map<String, Student> students;
    private Scanner scanner;

    public GradeTracker() {
        this.students = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * The main application loop.
     */
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrade();
                    break;
                case 3:
                    viewStudentDetails();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add a newline for readability
        }
    }

    private void printMenu() {
        System.out.println("=== Student Grade Tracker ===");
        System.out.println("1. Add New Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View Student Details");
        System.out.println("4. Exit");
        System.out.println("=============================");
    }

    /**
     * Handles creation of a new student.
     */
    private void addStudent() {
        String id = getStringInput("Enter Student ID: ");
        if (students.containsKey(id)) {
            System.out.println("Error: Student with this ID already exists.");
            return;
        }
        String name = getStringInput("Enter Student Name: ");
        
        // Create a new Student object (demonstrates inheritance)
        Student newStudent = new Student(name, id);
        
        // Add to our collection
        students.put(id, newStudent);
        System.out.println("Student added successfully: " + name);
    }

    /**
     * Finds a student and adds a grade to their record.
     */
    private void addGrade() {
        Student student = findStudentById();
        if (student == null) {
            return;
        }

        double grade = getDoubleInput("Enter grade: ");
        if (grade < 0.0 || grade > 100.0) {
             System.out.println("Invalid grade. Please enter a value between 0 and 100.");
             return;
        }

        student.addGrade(grade);
        System.out.println("Grade added successfully for " + student.getName());
    }

    /**
     * Finds and displays all details for a specific student.
     */
    private void viewStudentDetails() {
        Student student = findStudentById();
        if (student == null) {
            return;
        }
        
        // This implicitly calls the student.toString() method,
        // which we overrode to show all details.
        System.out.println(student);
    }

    // --- Helper Methods ---

    /**
     * A reusable helper to find a student by ID.
     */
    private Student findStudentById() {
        String id = getStringInput("Enter Student ID: ");
        Student student = students.get(id); // Fast lookup from HashMap
        if (student == null) {
            System.out.println("Error: Student not found with ID: " + id);
        }
        return student;
    }

    /**
     * Helper to get a non-empty string from the user.
     */
    private String getStringInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty.");
            }
        }
    }

    /**
     * Helper to get an integer from the user.
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                // Use getStringInput to handle the newline character bug
                return Integer.parseInt(getStringInput(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

     /**
     * Helper to get a double from the user.
     */
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                // Use getStringInput to handle the newline character bug
                return Double.parseDouble(getStringInput(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid grade (e.g., 85.5).");
            }
        }
    }


    /**
     * Main entry point of the application.
     */
    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();
        tracker.run();
    }
}