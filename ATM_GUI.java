import java.awt.*;
import javax.swing.*;

class ATM_GUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private double balance = 10000.00;
    private final int correctPin = 13579;

    public ATM_GUI() {
        frame = new JFrame("ATM Simulation System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Screens
        JPanel loginPanel = createLoginPanel();
        JPanel menuPanel = createMenuPanel();

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(menuPanel, "Menu");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Enter PIN:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        JPasswordField pinField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            try {
                int enteredPin = Integer.parseInt(new String(pinField.getPassword()));
                if (enteredPin == correctPin) {
                    cardLayout.show(mainPanel, "Menu");
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(label, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(pinField, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(loginButton, gbc);

        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 248, 255));
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel title = new JLabel("ATM Main Menu", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(title);

        JButton checkBalanceBtn = new JButton("Check Balance");
        JButton depositBtn = new JButton("Deposit Money");
        JButton withdrawBtn = new JButton("Withdraw Money");
        JButton exitBtn = new JButton("Exit");

        checkBalanceBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(frame, "Current Balance: ₹" + String.format("%.2f", balance))
        );

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    balance += amount;
                    JOptionPane.showMessageDialog(frame, "Deposited ₹" + amount);
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter a positive amount.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid amount.");
                } else if (amount > balance) {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance!");
                } else {
                    balance -= amount;
                    JOptionPane.showMessageDialog(frame, "Withdrawn ₹" + amount);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you! Have a nice day!");
            System.exit(0);
        });

        panel.add(checkBalanceBtn);
        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(exitBtn);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATM_GUI::new);
    }
}
