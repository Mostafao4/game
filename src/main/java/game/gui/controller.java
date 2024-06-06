package game.gui;

import java.io.IOException;
import java.util.Random;

import game.engine.GameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import game.engine.CLIGameController;


public class controller {
    private final Image[] diceImages = new Image[6];
    private final ImageView[] diceViews = new ImageView[6];
    private final Random random = new Random();

    private CLIGameController cliGameController; // Reference to the game controller

    public void setGameController() {
        cliGameController = DiceRealms.cliGameController;
    }

    // @FXML
    // private Button rollButton;
    // @FXML
    // private void rollDice(ActionEvent event){
    //     CliGameController.rollDice();
    // }

    @FXML
    private void handlePlayButtonAction() {
        // Call methods on the game controller when the play button is clicked
        if (cliGameController != null) {
            cliGameController.startGame();
        }
    }

    @FXML
    private Button rollButton;
    @FXML
    private HBox diceBox;

    

    @FXML
    public void initialize() {
        // Load dice images
        for (int i = 0; i < 6; i++) {
            diceImages[i] = new Image(getClass().getResourceAsStream("/die" + (i + 1) + ".png"));
        }

        // Initialize ImageViews and add to diceBox
        for (int i = 0; i < 6; i++) {
            diceViews[i] = new ImageView(diceImages[0]); // Initialize with the first image
            diceViews[i].setFitWidth(50); // Set image size (optional)
            diceViews[i].setFitHeight(50); // Set image size (optional)
            diceBox.getChildren().add(diceViews[i]);
        }

        // Set roll button action
        rollButton.setOnAction(e -> rollDice());
    }

    private void rollDice() {
        for (ImageView diceView : diceViews) {
            int rollResult = random.nextInt(6); // Get a random number between 0 and 5
            diceView.setImage(diceImages[rollResult]);
        }
    }




}
