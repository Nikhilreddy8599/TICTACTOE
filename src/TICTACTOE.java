import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TICTACTOE {
    int boardwidth = 600;
    int boardheight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textlabel = new JLabel();
    JPanel textpanel = new JPanel();
    JPanel boardpanel = new JPanel();
    JButton[][] board = new JButton[3][3];

    JButton restartButton = new JButton("New Match");

    String playerx = "X";
    String playero = "O";
    String currentplayer = playerx;

    boolean gameover = false;
    int turns = 0;

    TICTACTOE() {
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textlabel.setBackground(Color.black);
        textlabel.setForeground(Color.white);
        textlabel.setFont(new Font("Arial", Font.BOLD, 40));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("Tic-Tac-Toe");
        textlabel.setOpaque(true);

        restartButton.setFont(new Font("Arial", Font.BOLD, 20));
        restartButton.setFocusable(false);
        restartButton.setBackground(Color.lightGray);

        // Action listener for the Restart Button
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        textpanel.setLayout(new BorderLayout());
        textpanel.add(textlabel, BorderLayout.CENTER);
        textpanel.add(restartButton, BorderLayout.EAST);

        frame.add(textpanel, BorderLayout.NORTH);

        boardpanel.setLayout(new GridLayout(3, 3));
        boardpanel.setBackground(Color.darkGray);
        frame.add(boardpanel, BorderLayout.CENTER);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardpanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setText("");

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameover) return;
                        JButton tile = (JButton) e.getSource();

                        if (tile.getText().equals("")) {
                            tile.setText(currentplayer);
                            turns++;
                            checkwinner();

                            if (!gameover) {
                                currentplayer = currentplayer.equals(playerx) ? playero : playerx;
                                textlabel.setText(currentplayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }

        frame.setVisible(true);
    }

    void resetGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
            }
        }
        currentplayer = playerx;
        gameover = false;
        turns = 0;
        textlabel.setText("Tic-Tac-Toe");
    }

    void checkwinner() {
        // horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().equals("")) continue;

            if (board[r][0].getText().equals(board[r][1].getText()) &&
                    board[r][1].getText().equals(board[r][2].getText())) {
                textlabel.setText(currentplayer + " Wins!");
                gameover = true;
                return;
            }
        }

        // vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals("")) continue;

            if (board[0][c].getText().equals(board[1][c].getText()) &&
                    board[1][c].getText().equals(board[2][c].getText())) {
                textlabel.setText(currentplayer + " Wins!");
                gameover = true;
                return;
            }
        }

        // Diagonal checks (Top-Left to Bottom-Right)
        if (!board[0][0].getText().equals("") &&
                board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText())) {
            textlabel.setText(currentplayer + " Wins!");
            gameover = true;
            return;
        }

        // Anti-Diagonal checks (Top-Right to Bottom-Left)
        if (!board[0][2].getText().equals("") &&
                board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText())) {
            textlabel.setText(currentplayer + " Wins!");
            gameover = true;
            return;
        }

        // Tie game check
        if (turns == 9 && !gameover) {
            textlabel.setText("It's a Tie!");
            gameover = true;
        }
    }

}