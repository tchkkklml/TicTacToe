
package dk.easv.tictactoe.gui;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author EASV
 */
public class TicTacToe extends Application
{
    /**
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/TicTacView.fxml"));
        Parent scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.setResizable(false);
        stage.setTitle("Tic Tac Toe");
        stage.centerOnScreen();

        stage.show();
    }

    /**
     * Entry point of the application
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
