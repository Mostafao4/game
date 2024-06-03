package game.gui;

    import javafx.fxml.FXML;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXMLLoader;
    import java.io.IOException;

public class modesceneController {
    @FXML
    private Button multiplayer;
       @FXML
        private void switchToScene3(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/mainscene.fxml"));
            Scene scene = new Scene(root);
            DiceRealms.stage.setScene(scene);
    }
}
    

