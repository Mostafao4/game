package game.gui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class MainmenuController {
    @FXML
    private Button startButton;
    @FXML
    private Button Exit;
    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/modescene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = DiceRealms.stage;
        stage.setScene(scene);
    }
    public void exitGame(ActionEvent event) {
        System.exit(0);
    }
}
