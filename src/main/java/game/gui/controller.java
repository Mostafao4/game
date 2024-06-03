package game.gui;

import java.io.IOException;

import game.engine.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import game.engine.CLIGameController;


public class controller {

    private CLIGameController CliGameController; // Reference to the game controller

    public void setGameController() {
        this.CliGameController = DiceRealms.cliGameController;
    }

    @FXML
    private void handlePlayButtonAction() {
        // Call methods on the game controller when the play button is clicked
        if (CliGameController != null) {
            CliGameController.startGame();
        }
    }

    @FXML
    private Stage stage;
    public void switchToModescene(ActionEvent event) throws IOException{
        FXMLLoader loader2= new FXMLLoader(getClass().getResource("/modescene.fxml"));
        Scene scene3 = new Scene(loader2.load());
        Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene3);
    }

    public void switchToMainscene(ActionEvent event) throws IOException{
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("/mainscene.fxml"));
        Scene scene2 = new Scene(loader1.load());
        stage.setScene(scene2);
        stage.show();

    }


}
