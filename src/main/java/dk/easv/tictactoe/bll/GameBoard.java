
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{
    //makes an array 3x3, where program keeps values of buttons
    private int[][] board = new int [3][3];

    private int currentPlayer;
    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        return currentPlayer;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        //allows to change value only if the game isn't over and button is empty
        if (isGameOver() || board[row][col] != -1)
            return false;
        board [row][col] = currentPlayer;


        // switches current player if game isn't over
        if (!isGameOver()) {
            // (if player is 0, it'll become 1, otherwise it'll become 0)
            currentPlayer = (currentPlayer == 0) ? 1 : 0;
        }
            return true;

    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver()
    {
        return getWinner() != -1;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        for(int i = 0; i < 3; i++){
            if (board[i][0] != -1 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
            return board[i][0];
            }
            if (board[0][i] != -1 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
            if (board[0][0] != -1 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                    return board[0][0];
                }
            if (board[0][2] != -1 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                return board[0][2];
            }
        }
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        //sets all values to -1 (empty)
        for(int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                board [r][c] = -1;
            }
        }

        currentPlayer = 0;
    }
}
