import java.util.Scanner;

public class CGPACalculator {

    public static void main(String[] args) {
        // Create scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Ask for the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Declare an array to store the grades
        double[] grades = new double[numSubjects];
        
        // Total grade points initialized to 0
        double totalGradePoints = 0;

        // Input grades for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter grade points for subject " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
            totalGradePoints += grades[i];
        }

        // Calculate CGPA
        double cgpa = totalGradePoints / numSubjects;

        // Display the result
        System.out.println("Your CGPA is: " + cgpa);

        // Close the scanner
        scanner.close();
    }
}
