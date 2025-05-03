import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class AttendanceManagementSystem {
    private JFrame frame;
    private JTable studentTable;
    private JButton markAttendanceButton;
    private JButton showGraphButton;
    private Connection conn;
    private Statement stmt;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AttendanceManagementSystem window = new AttendanceManagementSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AttendanceManagementSystem() {
        initialize();
        connectDatabase();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for the GUI components
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        // JTable to show students and their attendance
        studentTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);

        markAttendanceButton = new JButton("Mark Attendance");
        markAttendanceButton.addActionListener(e -> markAttendance());
        buttonPanel.add(markAttendanceButton);

        showGraphButton = new JButton("Show Attendance Graph");
        showGraphButton.addActionListener(e -> showAttendanceGraph());
        buttonPanel.add(showGraphButton);
    }

    private void connectDatabase() {
        try {
            // Connect to SQLite database
            conn = DriverManager.getConnection("jdbc:sqlite:attendance.db");
            stmt = conn.createStatement();
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void markAttendance() {
        // Open a dialog to mark attendance for a student (Present/Absent)
        String studentId = JOptionPane.showInputDialog("Enter Student ID:");
        String status = JOptionPane.showInputDialog("Enter Attendance Status (Present/Absent):");

        try {
            String query = "INSERT INTO attendance (student_id, status, date) VALUES ('" + studentId + "', '" + status + "', DATE('now'))";
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(frame, "Attendance marked successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error marking attendance.");
        }
    }

    private void showAttendanceGraph() {
        // Display attendance graph using JFreeChart
        AttendanceGraph graph = new AttendanceGraph();
        JPanel graphPanel = graph.createGraph();
        JOptionPane.showMessageDialog(frame, graphPanel, "Attendance Graph", JOptionPane.INFORMATION_MESSAGE);
    }
}

class AttendanceGraph {
    public JPanel createGraph() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(75, "Attendance", "January");
        dataset.addValue(85, "Attendance", "February");
        dataset.addValue(90, "Attendance", "March");

        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Attendance", 
                "Month", 
                "Percentage", 
                dataset);

        return new ChartPanel(chart);
    }
}
