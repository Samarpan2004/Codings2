import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class NumberGuessingGUI extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton guessButton, restartButton;
    private JLabel messageLabel;
    private int targetNumber, attempts;

    public NumberGuessingGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        guessButton = new JButton("Guess");
        restartButton = new JButton("Restart");
        messageLabel = new JLabel("Guess a number between 1 and 100");

        add(messageLabel);
        add(inputField);
        add(guessButton);
        add(restartButton);

        guessButton.addActionListener(this);
        restartButton.addActionListener(e -> resetGame());

        resetGame();
        setVisible(true);
    }

    private void resetGame() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1;
        attempts = 0;
        messageLabel.setText("New Game! Guess a number between 1 and 100");
        inputField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(inputField.getText());
            attempts++;
            if (guess == targetNumber) {
                messageLabel.setText("Correct! It took " + attempts + " tries.");
            } else if (guess < targetNumber) {
                messageLabel.setText("Too low! Try again.");
            } else {
                messageLabel.setText("Too high! Try again.");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGUI();
    }
}
