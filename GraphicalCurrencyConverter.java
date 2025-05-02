import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicalCurrencyConverter extends JFrame implements ActionListener {

    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField, resultField;
    private JButton convertButton;

    private final double[][] rates = {
        // INR, USD, EUR, GBP, JPY
        {1, 0.012, 0.011, 0.0095, 1.77},    // INR
        {83.33, 1, 0.92, 0.79, 147.32},     // USD
        {90.91, 1.09, 1, 0.86, 160.15},     // EUR
        {105.26, 1.27, 1.16, 1, 186.25},    // GBP
        {0.56, 0.0068, 0.0062, 0.0054, 1}   // JPY
    };

    private final String[] currencies = {"INR", "USD", "EUR", "GBP", "JPY"};

    public GraphicalCurrencyConverter() {
        setTitle("💱 Currency Converter");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Background panel
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(20, 30, 40));  // Dark theme
            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 400);

        JLabel title = new JLabel("Currency Converter");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(140, 20, 300, 30);
        panel.add(title);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setBounds(70, 80, 80, 25);
        panel.add(fromLabel);

        fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(150, 80, 120, 25);
        panel.add(fromCurrency);

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.WHITE);
        toLabel.setBounds(280, 80, 80, 25);
        panel.add(toLabel);

        toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(320, 80, 120, 25);
        panel.add(toCurrency);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBounds(70, 130, 100, 25);
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 130, 290, 30);
        panel.add(amountField);

        convertButton = new JButton("Convert");
        convertButton.setBounds(190, 180, 120, 35);
        convertButton.setBackground(new Color(0, 153, 76));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        convertButton.addActionListener(this);
        panel.add(convertButton);

        JLabel resultLabel = new JLabel("Converted:");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBounds(70, 240, 100, 25);
        panel.add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(150, 240, 290, 30);
        resultField.setEditable(false);
        resultField.setBackground(Color.WHITE);
        panel.add(resultField);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            int from = fromCurrency.getSelectedIndex();
            int to = toCurrency.getSelectedIndex();
            double converted = amount * rates[from][to];
            resultField.setText(String.format("%.2f", converted));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount.");
        }
    }

    public static void main(String[] args) {
        new GraphicalCurrencyConverter();
    }
}
