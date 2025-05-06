import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class ResumeBuilder extends JFrame {

    // Form fields
    JTextField nameField, emailField, phoneField;
    JTextArea educationArea, experienceArea, skillsArea;
    JButton exportButton, resetButton;

    public ResumeBuilder() {
        setTitle("Resume Builder");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // === Form Panel ===
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fields
        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        educationArea = new JTextArea(3, 20);
        experienceArea = new JTextArea(3, 20);
        skillsArea = new JTextArea(3, 20);

        // Scrollable text areas
        JScrollPane eduScroll = new JScrollPane(educationArea);
        JScrollPane expScroll = new JScrollPane(experienceArea);
        JScrollPane skillsScroll = new JScrollPane(skillsArea);

        // Add to form
        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Education:"));
        formPanel.add(eduScroll);
        formPanel.add(new JLabel("Experience:"));
        formPanel.add(expScroll);
        formPanel.add(new JLabel("Skills:"));
        formPanel.add(skillsScroll);

        // === Buttons ===
        exportButton = new JButton("Export to PDF");
        resetButton = new JButton("Reset");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exportButton);
        buttonPanel.add(resetButton);

        // Add to frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // === Actions ===
        exportButton.addActionListener(e -> exportToPDF());
        resetButton.addActionListener(e -> resetForm());

        setVisible(true);
    }

    private void exportToPDF() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Resume_" + nameField.getText().replaceAll(" ", "_") + ".pdf"));
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font sectionFont = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font bodyFont = new Font(Font.HELVETICA, 12);

            document.add(new Paragraph("Resume", titleFont));
            document.add(new Paragraph("Name: " + nameField.getText(), bodyFont));
            document.add(new Paragraph("Email: " + emailField.getText(), bodyFont));
            document.add(new Paragraph("Phone: " + phoneField.getText(), bodyFont));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Education", sectionFont));
            document.add(new Paragraph(educationArea.getText(), bodyFont));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Experience", sectionFont));
            document.add(new Paragraph(experienceArea.getText(), bodyFont));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Skills", sectionFont));
            document.add(new Paragraph(skillsArea.getText(), bodyFont));

            document.close();
            JOptionPane.showMessageDialog(this, "Resume exported successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to export resume.");
        }
    }

    private void resetForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        educationArea.setText("");
        experienceArea.setText("");
        skillsArea.setText("");
    }

    public static void main(String[] args) {
        new ResumeBuilder();
    }
}
