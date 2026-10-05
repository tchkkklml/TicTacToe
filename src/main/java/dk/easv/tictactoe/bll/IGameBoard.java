
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public interface IGameBoard
{

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    int getNextPlayer();

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    boolean play(int col, int row);

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    boolean isGameOver();

    /**
     * Gets the id of the winner, -1 if its a draw or if the game is still running.
     *
     * @return int id of winner, or -1 if draw or if gameOver() == false.
     */
    int getWinner();

    /**
     * Resets the game to a new game state.
     */
    void newGame();
}
