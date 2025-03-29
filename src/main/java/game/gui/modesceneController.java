package game.gui;

    import javafx.fxml.FXML;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
import javafx.scene.control.Button;
    import javafx.stage.Modality;
    import javafx.stage.Stage;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXMLLoader;
    import java.io.IOException;

public class modesceneController {
    @FXML
    private Button multiplayer;
    @FXML
    private void handleMultiplayerButtonAction(ActionEvent event) throws IOException {
        // Load the player input window
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PlayerInput.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Input");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
    

