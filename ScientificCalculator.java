import java.util.Scanner;

public class ScientificCalculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Scientific Calculator");
            System.out.println("Select operation:");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("5. Square Root (sqrt)");
            System.out.println("6. Power (^) ");
            System.out.println("7. Logarithm (log)");
            System.out.println("8. Sine (sin)");
            System.out.println("9. Cosine (cos)");
            System.out.println("10. Tangent (tan)");
            System.out.println("11. Pi (π)");
            System.out.println("12. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            
            if (choice == 12) {
                System.out.println("Exiting calculator...");
                break;
            }
            
            double num1, num2, result;
            
            switch (choice) {
                case 1: // Addition
                    System.out.print("Enter first number: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    num2 = scanner.nextDouble();
                    result = num1 + num2;
                    System.out.println("Result: " + result);
                    break;
                case 2: // Subtraction
                    System.out.print("Enter first number: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    num2 = scanner.nextDouble();
                    result = num1 - num2;
                    System.out.println("Result: " + result);
                    break;
                case 3: // Multiplication
                    System.out.print("Enter first number: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    num2 = scanner.nextDouble();
                    result = num1 * num2;
                    System.out.println("Result: " + result);
                    break;
                case 4: // Division
                    System.out.print("Enter first number: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    num2 = scanner.nextDouble();
                    if (num2 != 0) {
                        result = num1 / num2;
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("Error: Division by zero");
                    }
                    break;
                case 5: // Square Root
                    System.out.print("Enter a number: ");
                    num1 = scanner.nextDouble();
                    if (num1 >= 0) {
                        result = Math.sqrt(num1);
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("Error: Negative number");
                    }
                    break;
                case 6: // Power
                    System.out.print("Enter base: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter exponent: ");
                    num2 = scanner.nextDouble();
                    result = Math.pow(num1, num2);
                    System.out.println("Result: " + result);
                    break;
                case 7: // Logarithm
                    System.out.print("Enter a number: ");
                    num1 = scanner.nextDouble();
                    if (num1 > 0) {
                        result = Math.log10(num1);
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("Error: Logarithm of non-positive number");
                    }
                    break;
                case 8: // Sine
                    System.out.print("Enter angle in degrees: ");
                    num1 = scanner.nextDouble();
                    result = Math.sin(Math.toRadians(num1));
                    System.out.println("Result: " + result);
                    break;
                case 9: // Cosine
                    System.out.print("Enter angle in degrees: ");
                    num1 = scanner.nextDouble();
                    result = Math.cos(Math.toRadians(num1));
                    System.out.println("Result: " + result);
                    break;
                case 10: // Tangent
                    System.out.print("Enter angle in degrees: ");
                    num1 = scanner.nextDouble();
                    result = Math.tan(Math.toRadians(num1));
                    System.out.println("Result: " + result);
                    break;
                case 11: // Pi
                    System.out.println("Pi: " + Math.PI);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            System.out.println(); // Add a blank line for better readability
        }
        scanner.close();
    }
}
