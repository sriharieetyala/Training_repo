
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A derived class representing a Student.
 * Inherits from Person and adds a list of grades.
 */
public class Student extends Person {

    // Requirement: Use a Collection (List) to store grades
    private List<Double> grades;

    /**
     * Constructor for Student.
     * It calls the parent (Person) constructor using 'super'.
     */
    public Student(String name, String id) {
        // Call the constructor of the parent class (Person)
        super(name, id);
        // Initialize the collection
        this.grades = new ArrayList<>();
    }

    /**
     * Adds a grade to the student's grade list.
     */
    public void addGrade(double grade) {
        this.grades.add(grade);
    }

    /**
     * Calculates and returns the average grade.
     */
    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    /**
     * Overrides the toString method to provide a detailed
     * description of the student, including their grades.
     */
    @Override
    public String toString() {
        // 'super.toString()' calls the method from the Person class
        String personInfo = super.toString();
        
        String gradesString;
        if (grades.isEmpty()) {
            gradesString = "No grades entered.";
        } else {
            // Use streams to format the list nicely (optional but clean)
            gradesString = this.grades.stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(", "));
        }

        return String.format(
            "--- Student Details ---\n" +
            "%s\n" +
            "Grades: [ %s ]\n" +
            "Average: %.2f\n" +
            "-------------------------",
            personInfo, gradesString, getAverageGrade()
        );
    }
}