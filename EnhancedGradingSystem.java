import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class EnhancedGradingSystem extends JFrame implements ActionListener {

    JTextField nameField, rollField;
    JTextField[] subjectFields = new JTextField[5];
    JLabel resultLabel, gradeLabel;
    int[] marks = new int[5];
    GraphPanel graphPanel;

    public EnhancedGradingSystem() {
        setTitle("Enhanced Grading System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Roll Number:"));
        rollField = new JTextField();
        inputPanel.add(rollField);

        inputPanel.add(new JLabel("Enter Marks (out of 100):"));

        for (int i = 0; i < 5; i++) {
            inputPanel.add(new JLabel("Subject " + (i + 1) + ":"));
            subjectFields[i] = new JTextField();
            inputPanel.add(subjectFields[i]);
        }

        JButton calcButton = new JButton("Calculate & Save");
        calcButton.addActionListener(this);
        inputPanel.add(calcButton);

        resultLabel = new JLabel("Total & Percentage: ");
        gradeLabel = new JLabel("Grade: ");
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(resultLabel);
        inputPanel.add(gradeLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Graph Panel
        graphPanel = new GraphPanel();
        graphPanel.setPreferredSize(new Dimension(600, 200));
        add(graphPanel, BorderLayout.CENTER);

        getContentPane().setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int total = 0;
        boolean valid = true;

        for (int i = 0; i < 5; i++) {
            try {
                marks[i] = Integer.parseInt(subjectFields[i].getText());
                if (marks[i] < 0 || marks[i] > 100) {
                    throw new NumberFormatException();
                }
                total += marks[i];
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid marks (0-100) for Subject " + (i + 1));
                valid = false;
                break;
            }
        }

        if (valid) {
            double percentage = total / 5.0;
            char grade;
            Color gradeColor;

            if (percentage >= 90) {
                grade = 'A'; gradeColor = Color.GREEN;
            } else if (percentage >= 80) {
                grade = 'B'; gradeColor = Color.BLUE;
            } else if (percentage >= 70) {
                grade = 'C'; gradeColor = Color.ORANGE;
            } else if (percentage >= 60) {
                grade = 'D'; gradeColor = Color.MAGENTA;
            } else if (percentage >= 50) {
                grade = 'E'; gradeColor = Color.PINK;
            } else {
                grade = 'F'; gradeColor = Color.RED;
            }

            resultLabel.setText("Total: " + total + "/500 | Percentage: " + percentage + "%");
            gradeLabel.setText("Grade: " + grade);
            gradeLabel.setForeground(gradeColor);

            // Refresh graph
            graphPanel.repaint();

            // Save to file
            saveToFile(nameField.getText(), rollField.getText(), marks, percentage, grade);
        }
    }

    void saveToFile(String name, String roll, int[] marks, double percentage, char grade) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("grades.txt", true))) {
            pw.println("Name: " + name);
            pw.println("Roll No: " + roll);
            for (int i = 0; i < 5; i++) {
                pw.println("Subject " + (i + 1) + ": " + marks[i]);
            }
            pw.println("Total: " + (marks[0] + marks[1] + marks[2] + marks[3] + marks[4]));
            pw.println("Percentage: " + percentage);
            pw.println("Grade: " + grade);
            pw.println("----------------------------");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file.");
        }
    }

    class GraphPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);

            int barWidth = 80;
            int startX = 40;

            for (int i = 0; i < 5; i++) {
                int height = (int) (marks[i] * 1.5);
                g.setColor(new Color(100, 100 + i * 30, 200));
                g.fillRect(startX + i * 90, 150 - height, barWidth, height);
                g.setColor(Color.BLACK);
                g.drawString("Sub " + (i + 1), startX + i * 90 + 10, 170);
                g.drawString(String.valueOf(marks[i]), startX + i * 90 + 20, 150 - height - 5);
            }
        }
    }

    public static void main(String[] args) {
        new EnhancedGradingSystem();
    }
}
