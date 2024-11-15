package javaapplication48;

import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {
    private Button[][] buttons = new Button[3][3];
    private char currentPlayer = 'X';
    private boolean gameActive = true;

    public TicTacToe() {
        // Set up the game window
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));

        // Initialize the buttons for each cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        // Set window close operation
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button buttonClicked = (Button) e.getSource();

        // Only process clicks if the game is active
        if (gameActive && buttonClicked.getLabel().equals("")) {
            buttonClicked.setLabel(String.valueOf(currentPlayer));
            
            // Check for a winner after the current move
            if (checkWinner()) {
                gameActive = false;
                showMessage("Player " + currentPlayer + " wins!");
            } else if (isDraw()) {
                gameActive = false;
                showMessage("It's a draw!");
            } else {
                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getLabel().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getLabel().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getLabel().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (buttons[0][i].getLabel().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getLabel().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getLabel().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getLabel().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getLabel().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getLabel().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[0][2].getLabel().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getLabel().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getLabel().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getLabel().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showMessage(String message) {
        Dialog dialog = new Dialog(this, "Game Over", true);
        dialog.setLayout(new FlowLayout());
        dialog.add(new Label(message));
        Button okButton = new Button("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                resetGame();
            }
        });
        dialog.add(okButton);
        dialog.setSize(200, 100);
        dialog.setVisible(true);
    }

    private void resetGame() {
        // Reset the board and game status
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setLabel("");
            }
        }
        currentPlayer = 'X';
        gameActive = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
