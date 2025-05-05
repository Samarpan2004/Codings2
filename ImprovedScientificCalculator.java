import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class ImprovedScientificCalculator {
    private JFrame frame;
    private JTextField display;
    private StringBuilder currentInput;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ImprovedScientificCalculator window = new ImprovedScientificCalculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ImprovedScientificCalculator() {
        currentInput = new StringBuilder();
        initialize();
    }

    private void initialize() {
        // Initialize frame
        frame = new JFrame();
        frame.setTitle("Scientific Calculator");
        frame.setBounds(100, 100, 500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());

        // Create the display at the top
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        panel.add(display, BorderLayout.NORTH);
        display.setColumns(10);

        // Create buttons for numbers and operations
        JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(6, 6, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/", "sqrt", "pow",
            "4", "5", "6", "*", "exp", "abs",
            "1", "2", "3", "-", "log", "sin",
            "0", ".", "=", "+", "cos", "tan",
            "(", ")", "pi", "C", "!", "log10"
        };

        // Create buttons dynamically and add action listeners
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("=")) {
                try {
                    // Evaluate the current input and display the result
                    String result = evaluateExpression(currentInput.toString());
                    display.setText(result);
                    currentInput.setLength(0);  // Clear input after evaluation
                    currentInput.append(result);  // Store result for further use
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);  // Clear input on error
                }
            } else if (command.equals("C")) {
                // Clear the display and reset the input
                currentInput.setLength(0);
                display.setText("");
            } else if (command.equals("sqrt")) {
                try {
                    double value = Math.sqrt(Double.parseDouble(currentInput.toString()));
                    display.setText(String.valueOf(value));
                    currentInput.setLength(0);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            } else if (command.equals("pow")) {
                currentInput.append("^");
                display.setText(currentInput.toString());
            } else if (command.equals("log")) {
                try {
                    double value = Math.log(Double.parseDouble(currentInput.toString()));
                    display.setText(String.valueOf(value));
                    currentInput.setLength(0);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            } else if (command.equals("log10")) {
                try {
                    double value = Math.log10(Double.parseDouble(currentInput.toString()));
                    display.setText(String.valueOf(value));
                    currentInput.setLength(0);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            } else if (command.equals("sin") || command.equals("cos") || command.equals("tan")) {
                try {
                    double angle = Double.parseDouble(currentInput.toString());
                    double result = 0;
                    if (command.equals("sin")) result = Math.sin(Math.toRadians(angle));
                    if (command.equals("cos")) result = Math.cos(Math.toRadians(angle));
                    if (command.equals("tan")) result = Math.tan(Math.toRadians(angle));

                    display.setText(String.valueOf(result));
                    currentInput.setLength(0);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            } else if (command.equals("pi")) {
                currentInput.append(Math.PI);
                display.setText(currentInput.toString());
            } else if (command.equals("!")) {
                try {
                    long factorial = factorial(Integer.parseInt(currentInput.toString()));
                    display.setText(String.valueOf(factorial));
                    currentInput.setLength(0);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            } else if (command.equals("abs")) {
                try {
                    double value = Math.abs(Double.parseDouble(currentInput.toString()));
                    display.setText(String.valueOf(value));
                    currentInput.setLength(0);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            } else {
                // Add the pressed button to the current input string
                currentInput.append(command);
                display.setText(currentInput.toString());
            }
        }

        private String evaluateExpression(String expression) {
            try {
                // Evaluate the expression using a simple method (can be expanded)
                return evaluateBasicExpression(expression);
            } catch (Exception e) {
                return "Error";
            }
        }

        private String evaluateBasicExpression(String expression) {
            // Simplified parsing: Add more complex evaluation logic here for advanced cases
            if (expression.contains("^")) {
                String[] parts = expression.split("\\^");
                double base = Double.parseDouble(parts[0]);
                double exponent = Double.parseDouble(parts[1]);
                return String.valueOf(Math.pow(base, exponent));
            } else {
                // Handle basic cases for now
                return expression;  // Return as is for unsupported expressions
            }
        }

        private long factorial(int num) {
            if (num == 0 || num == 1) {
                return 1;
            }
            long result = 1;
            for (int i = 2; i <= num; i++) {
                result *= i;
            }
            return result;
        }
    }
}
