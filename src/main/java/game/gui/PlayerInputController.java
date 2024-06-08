package game.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerInputController {

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;

    @FXML
    private void handleStartGame() throws IOException {
        String player1 = player1Name.getText();
        String player2 = player2Name.getText();
        Stage stage = (Stage) player1Name.getScene().getWindow();
        stage.close();
        DiceRealms.stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainscene.fxml"));
        Parent root = loader.load();

        controller controller = loader.getController();
        controller.setPlayerNames(player1, player2);

        Stage stage1 = (Stage) player1Name.getScene().getWindow();
        stage1.setScene(new Scene(root));
        stage1.setTitle("Dice Realms: Quest for the Elemental Crests!");
        stage1.show();

    }
}
