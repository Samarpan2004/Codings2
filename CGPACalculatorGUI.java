import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CGPACalculatorGUI extends JFrame {

    private JTextField subjectCountField;
    private JButton enterSubjectsButton;
    private JPanel gradesPanel;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JTextField[] gradeFields;

    public CGPACalculatorGUI() {
        setTitle("CGPA Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Layout for main window
        setLayout(new BorderLayout());

        // Top panel: Enter number of subjects
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Number of Subjects: "));
        subjectCountField = new JTextField(5);
        topPanel.add(subjectCountField);
        enterSubjectsButton = new JButton("Enter Grades");
        topPanel.add(enterSubjectsButton);

        add(topPanel, BorderLayout.NORTH);

        // Center panel: For grades
        gradesPanel = new JPanel();
        gradesPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(gradesPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel: Calculate button and result
        JPanel bottomPanel = new JPanel();
        calculateButton = new JButton("Calculate CGPA");
        resultLabel = new JLabel("CGPA: ");
        bottomPanel.add(calculateButton);
        bottomPanel.add(resultLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Event to generate input fields
        enterSubjectsButton.addActionListener(e -> createGradeFields());

        // Event to calculate CGPA
        calculateButton.addActionListener(e -> calculateCGPA());

        setVisible(true);
    }

    private void createGradeFields() {
        gradesPanel.removeAll(); // Clear previous fields
        try {
            int count = Integer.parseInt(subjectCountField.getText());
            gradeFields = new JTextField[count];

            for (int i = 0; i < count; i++) {
                gradesPanel.add(new JLabel("Subject " + (i + 1) + ": "));
                gradeFields[i] = new JTextField();
                gradesPanel.add(gradeFields[i]);
            }

            gradesPanel.revalidate();
            gradesPanel.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void calculateCGPA() {
        try {
            double total = 0;
            int count = gradeFields.length;

            for (JTextField field : gradeFields) {
                double grade = Double.parseDouble(field.getText());
                total += grade;
            }

            double cgpa = total / count;
            resultLabel.setText("CGPA: " + String.format("%.2f", cgpa));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid grades for all subjects.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CGPACalculatorGUI::new);
    }
}
