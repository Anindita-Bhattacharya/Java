import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Custom Exception Classes
class InvalidGradeException extends Exception {
    public InvalidGradeException(String message) {
        super(message);
    }
}

class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}

class NoGradesEnteredException extends Exception {
    public NoGradesEnteredException(String message) {
        super(message);
    }
}

// GradeBook Class
class GradeBook {
    private Map<String, Double> grades; // Student name and grade

    public GradeBook() {
        grades = new HashMap<>();
    }

    public void addGrade(String studentName, double grade) throws InvalidGradeException {
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException("Grade must be between 0 and 100.");
        }
        grades.put(studentName, grade);
    }

    public double getGrade(String studentName) throws StudentNotFoundException {
        if (!grades.containsKey(studentName)) {
            throw new StudentNotFoundException("Student '" + studentName + "' not found.");
        }
        return grades.get(studentName);
    }

    public double calculateAverage() throws NoGradesEnteredException {
        if (grades.isEmpty()) {
            throw new NoGradesEnteredException("No grades have been entered.");
        }
        double total = 0;
        for (double grade : grades.values()) {
            total += grade;
        }
        return total / grades.size();
    }

    public String getStudentGrades() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}

// Main Class for Testing
public class Main {
    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();
        Scanner scanner = new Scanner(System.in);

        // Adding grades
        try {
            String studentName = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());
            gradeBook.addGrade(studentName, grade);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for the grade.");
        } catch (InvalidGradeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Retrieving a grade
        try {
            String studentName = scanner.nextLine();
            double retrievedGrade = gradeBook.getGrade(studentName);
            System.out.println("Grade for " + studentName + ": " + retrievedGrade);
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Calculating average
        try {
            double average = gradeBook.calculateAverage();
            System.out.println("Average Grade: " + average);
        } catch (NoGradesEnteredException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Listing all grades
        System.out.println("Student Grades:");
        System.out.println(gradeBook.getStudentGrades());

        // Closing the scanner
        scanner.close();
    }
}
