package game.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class PlayerInputController {

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;

    @FXML
    private void handleStartGame() throws IOException {
        // Get player names
        String player1 = player1Name.getText().trim();
        String player2 = player2Name.getText().trim();

        // Check if either player name is empty
        if (player1.isEmpty() || player2.isEmpty()) {
            // Show error alert
            showErrorAlert("Name Required", "Please enter names for both players.");
            return;
        }

        Stage stage = (Stage) player1Name.getScene().getWindow();
        stage.close();
        DiceRealms.stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainscene.fxml"));
        Parent root = loader.load();

        controller controller = loader.getController();
        controller.setPlayerNames(player1, player2);

        Stage stage1 = (Stage) player1Name.getScene().getWindow();
        stage1.setTitle("Dice Realms: Quest for the Elemental Crests!");
        Image icon = new Image("/Dice realms.jpeg");
        stage1.getIcons().add(icon);
        stage1.setScene(new Scene(root));
        stage1.setTitle("Dice Realms: Quest for the Elemental Crests!");
        stage1.show();
    }

    // Helper method to show an error alert
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
