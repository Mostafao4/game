package game.gui;

import java.io.IOException;
import java.util.Random;

import game.engine.GameController;
import game.engine.Move;
import game.engine.Player;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import game.engine.CLIGameController;
import game.dice.*;
import game.engine.Move;


public class controller extends CLIGameController {
//     private final Random random = new Random();
//     private final DiceFace[] diceFaces = new DiceFace[6];
//     private int turnCounter = 1;

//     private CLIGameController cli; // Reference to the game controller

//     public void setGameController() {
//         cli = DiceRealms.cliGameController;
//     }

//     // @FXML
//     // private Button rollButton;
//     // @FXML
//     // private void rollDice(ActionEvent event){
//     //     CliGameController.rollDice();
//     // }

//     // @FXML
//     // private void handlePlayButtonAction() {
//     //     // Call methods on the game controller when the play button is clicked
//     //     if (CliGameController != null) {
//     //         CliGameController.startGame();
//     //     }
//     // }


//     @FXML
//     private Label player1turn;
//     private int currentTurn = 1;
//     @FXML
//     private Button rollButton;
//     @FXML
//     private HBox diceBox;
//     private DiceFace selectedDiceFace = null;
//     private Dice[] dice = new Dice[]{new RedDice(0), new GreenDice(0), new BlueDice(0), new MagentaDice(0), new YellowDice(0), new ArcanePrism(0)};
    
    
//     @FXML
//     public void initialize() {
        
//         for (int i = 0; i < dice.length; i++) {
//             diceFaces[i] = new DiceFace(dice[i].getValue(), getColor(i));
//             diceBox.getChildren().add(diceFaces[i]);
//         }
//     }
    

//     @FXML
//     private void handleRollButtonClick() {
//         rollDice();
//         // incrementTurn();
//         // updateTurnLabel();
//     }

//     public Dice[] rollDice() {
//         for (int i = 0; i < dice.length; i++) {
//             dice[i].roll();
//             diceFaces[i].updateFace(dice[i].getValue());
//         }
//         return null;
//     }

//     private Color getColor(int index) {
//         Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.YELLOW, Color.WHITE};
//         return colors[index % colors.length];
//     }
    

//     private void handleDiceSelection(DiceFace selectedDice) {
//         if (selectedDiceFace != null) {
//             selectedDiceFace.deselect();
//         }
//         selectedDiceFace = selectedDice;
//         selectedDiceFace.select();
//     }


//     // private void incrementTurn() {
//     //     currentTurn++;  // Increment the turn counter
//     // }

//     // private void updateTurnLabel() {
//     //     player1turn.setText("Turn: " + currentTurn);
//     // }





//   //Scoresheets 
  
  
//     @FXML
//     private Button Green1;
//     private Button Green2;
//     private Button Green3;   
//     private Button Green4;
//     private Button Green5;
//     private Button Green6;
//     private Button Green7;
//     private Button Green8;
//     private Button Green9;
//     private Button Green10;
//     private Button Green11;
//     private Button[] GreenButtons = new Button[]{Green1,Green2,Green3,Green4,Green5,Green6,Green7,Green8,Green9,Green10,Green11};
    
//     private void possibleGreen(){
//         int[] buttonValues = new int[11];
//         for(int i = 0; i<GreenButtons.length;i++){
//             buttonValues[i] = Integer.parseInt(GreenButtons[i].getText());
//         }
//         Move[] moves = cli.getPossibleMovesForADie(getActivePlayer(), cli.getAllDice()[1]);
//         int x = moves[0].getDice().getValue();
//         for(int j =0;j<buttonValues.length;j++){
//             if(x == buttonValues[j]){
//                 GreenButtons[j].setText(String.valueOf(buttonValues[j]));
//                 Button btn = GreenButtons[j];
//                 btn.setOnAction(e -> btn.setStyle("-fx-background-color: yellow; -fx-border-color: black; -fx-border-width: 2;"));
//             }
//         }
//     }

//     public void highlight(){
//         possibleGreen();
//     }

    @FXML
    private Label player1Label;

    @FXML
    private Label player2Label;

    public void setPlayerNames(String player1, String player2) {
        player1Label.setText("Player 1: " + player1);
        player2Label.setText("Player 2: " + player2);
    }

    @FXML
    private Button rollButton;    
    @FXML
    private Button die1;
    @FXML
    private Button die2;
    @FXML
    private Button die3;
    @FXML
    private Button die4;
    @FXML
    private Button die5;
    @FXML
    private Button die6;

    private Button selectedButton = null;

    private final Image[][] diceImages = new Image[6][6]; // 6 types, 6 values each
    private final ImageView[] diceViews = new ImageView[6]; // Assuming 6 dice views

    // Method to change the text of buttons randomly
    public void roll() {
        Random random = new Random();
        int counter = 0;
        // Iterate over your buttons and assign random values to them
        for (Button button : getButtons()) {
            int newValue = random.nextInt(6) + 1; // Random value between 1 and 6
            button.setText(String.valueOf(newValue));
            button.setDisable(false);
            button.getStyleClass().remove("selected-button");
            counter++;
        }
        selectedButton = null;
    }

    @FXML
    private void handleButtonPress(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource(); // Get the button that was pressed

        if (selectedButton != null) {
            selectedButton.setDisable(false); // Re-enable previously selected button

            // Remove the selection class from the previously selected button
            selectedButton.getStyleClass().remove("selected-button");
        }

        // Select the new button and disable it
        selectedButton = button;
        selectedButton.setDisable(true); // Disable the selected button to prevent further clicks

        // Add the selection class to the new button
        selectedButton.getStyleClass().add("selected-button");
    }

    // Helper method to get all buttons
    private Button[] getButtons() {
        // Return an array of your buttons here
        return new Button[]{die1, die2, die3, die4, die5, die6}; // Add more buttons as needed...
    }

   
 }





    

   






