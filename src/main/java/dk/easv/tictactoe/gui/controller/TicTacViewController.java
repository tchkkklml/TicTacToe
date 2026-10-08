
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable {

    //starts one player game
    @FXML
    public Button btnOnePlayer;

    //starts new game
    @FXML
    public Button btnNewGame;

    //starts two player game
    @FXML
    public Button btnTwoPlayer;

    //makes all lines visible to controller
    @FXML
    private Line line1, line2, line3, line4, line5, line6, line7, line8;

    //makes all buttons visible to controller
    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @FXML
    private Label lblPlayer;

    @FXML
    private GridPane gridPane;


    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;
    private boolean onePlayerMode = false;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            if (game.play(c, r)) {
                Button btn = (Button) event.getSource();
                String xOrO = player == 0 ? "X" : "O";
                btn.setText(xOrO);
                setPlayer();
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                    checkLines();
                }
                // If we're in one player mode and game isnt over the computer makes a move
                if (onePlayerMode && !game.isGameOver()) {
                    computerMove();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event) {
        game.newGame();
        setPlayer();
        clearBoard();
        hideLines();

    }

    /**
     * Initializes a new controller
     *
     * @param url The location used to resolve relative paths for the root object, or
     *            {@code null} if the location is not known.
     * @param rb  The resources used to localize the root object, or {@code null} if
     *            the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        game.newGame();
        setPlayer();
        hideLines();
    }

    /**
     * Set the next player
     */
    private void setPlayer() {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }


    /**
     * Finds a winner or a draw and displays a message based
     *
     * @param winner
     */
    private void displayWinner(int winner) {
        String message = "";
        switch (winner) {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard() {
        hideLines();
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

    //shows lines if someone wins
    private void checkLines() {
        if (checkButtons(btn1, btn2, btn3)) {
            line1.setVisible(true);
        }
        if (checkButtons(btn4, btn5, btn6)) {
            line2.setVisible(true);
        }
        if (checkButtons(btn7, btn8, btn9)) {
            line3.setVisible(true);
        }

        if (checkButtons(btn1, btn4, btn7)) {
            line4.setVisible(true);
        }
        if (checkButtons(btn2, btn5, btn8)) {
            line5.setVisible(true);
        }
        if (checkButtons(btn3, btn6, btn9)) {
            line6.setVisible(true);
        }

        if (checkButtons(btn1, btn5, btn9)) {
            line7.setVisible(true);
        }
        if (checkButtons(btn3, btn5, btn7)) {
            line8.setVisible(true);
        }


    }

    //hides all the lines
    private void hideLines() {
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        line4.setVisible(false);
        line5.setVisible(false);
        line6.setVisible(false);
        line7.setVisible(false);
        line8.setVisible(false);
    }

    //checks if 3 chosen buttons have the same text and aren't empty
    private boolean checkButtons(Button b1, Button b2, Button b3) {
        if (b1.getText() != "" && b1.getText().equals(b2.getText()) && b1.getText().equals(b3.getText())) {
            return true;
        } else return false;
    }

    // if we press one player mode it starts game against computer
    public void handleOnePlayer(ActionEvent actionEvent) {
        onePlayerMode = true;
        game.newGame();
        setPlayer();
        clearBoard();
    }

    // starts two player mode
    public void handleTwoPlayer(ActionEvent actionEvent) {
        onePlayerMode = false;
        game.newGame();
        setPlayer();
        clearBoard();

    }

    // method for computers turn
    private void computerMove() {
        Random random = new Random();

        Button[] buttons = {
                btn1, btn2, btn3,
                btn4, btn5, btn6,
                btn7, btn8, btn9
        };

        //computer checks if game is over yet or if its their turn, if both is false it makes a turn
        boolean moveMade = false;

        while (!game.isGameOver() && !moveMade) {

            int position = random.nextInt(9);

            int row = position / 3;
            int col = position % 3;

            // checks if square is empty and then puts O if it is
            if (game.play(col, row)) {
                buttons[position].setText("O");

                moveMade = true;
                setPlayer();

                //checks who won and shows in the label who won
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                    checkLines();
                }
            }
        }
    }
}
