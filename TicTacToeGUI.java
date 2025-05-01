import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel = new JLabel("Player X's turn");

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe Game");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                gridPanel.add(buttons[i][j]);
            }
        }

        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) return;

        clicked.setText(String.valueOf(currentPlayer));
        if (checkWinner()) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            disableAllButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a draw!");
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private boolean checkWinner() {
        String p = String.valueOf(currentPlayer);
        // Rows & columns
        for (int i = 0; i < 3; i++) {
            if (p.equals(buttons[i][0].getText()) && p.equals(buttons[i][1].getText()) && p.equals(buttons[i][2].getText()))
                return true;
            if (p.equals(buttons[0][i].getText()) && p.equals(buttons[1][i].getText()) && p.equals(buttons[2][i].getText()))
                return true;
        }
        // Diagonals
        if (p.equals(buttons[0][0].getText()) && p.equals(buttons[1][1].getText()) && p.equals(buttons[2][2].getText()))
            return true;
        if (p.equals(buttons[0][2].getText()) && p.equals(buttons[1][1].getText()) && p.equals(buttons[2][0].getText()))
            return true;
        return false;
    }

    private boolean isDraw() {
        for (JButton[] row : buttons)
            for (JButton b : row)
                if (b.getText().equals("")) return false;
        return true;
    }

    private void disableAllButtons() {
        for (JButton[] row : buttons)
            for (JButton b : row)
                b.setEnabled(false);
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
