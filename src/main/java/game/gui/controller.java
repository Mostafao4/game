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
  
  
    @FXML
    private Button Green2;
    @FXML
    private Button Green3; 
    @FXML  
    private Button Green4;
    @FXML
    private Button Green5;
    @FXML
    private Button Green6;
    @FXML
    private Button Green7;
    @FXML
    private Button Green8;
    @FXML
    private Button Green9;
    @FXML
    private Button Green10;
    @FXML
    private Button Green11;
    @FXML
    private Button Green12;
    private Button[] GreenButtons = new Button[]{Green2,Green3,Green4,Green5,Green6,Green7,Green8,Green9,Green10,Green11,Green12};
    
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


    // Method to change the text of buttons randomly
    public void changeButtonsRandomly() {
        Random random = new Random();

        // Iterate over your buttons and assign random values to them
        for (Button button : getButtons()) {
            int newValue = random.nextInt(6) + 1; // Random value between 1 and 6
            button.setText(String.valueOf(newValue));
            button.setDisable(false);
            button.getStyleClass().remove("selected-button");
        }
        for(Button button : new Button[]{Green2,Green3,Green4,Green5,Green6,Green7,Green8,Green9,Green10,Green11,Green12}){
            button.setStyle("");;
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
        //int dieValue = Integer.parseInt(selectedButton.getText());
        highlightGreenPossibleMoves(getAllDice()[1]);


        // Add the selection class to the new button
        selectedButton.getStyleClass().add("selected-button");
    }

    // Helper method to get all buttons
    private Button[] getButtons() {
        // Return an array of your buttons here
        return new Button[]{die1, die2, die3, die4, die5, die6}; // Add more buttons as needed...
    }


    

    private void highlightGreenPossibleMoves(Dice d){

            Move[] moves = getPossibleMovesForADie(getActivePlayer(), d);
            // for(int i = 0; i < moves.length;i++){
            //     nums[i] = moves[i].getDice().getValue();
            // } 
            int h = Integer.parseInt(die2.getText()) + Integer.parseInt(die6.getText());
            switch(h){
                case 2:
                    Green2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 3:
                    Green3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 4:
                    Green4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 5:
                    Green5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 6:
                    Green6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 7:
                    Green7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 8:
                    Green8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 9:
                    Green9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 10:
                    Green10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 11:
                    Green11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    case 12:
                    Green12.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                    
            }
    }

    public void attackGreen2(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(2),getActivePlayer().getScoreSheet().getGaia()));
        Green2.setText("X");
    }
    public void attackGreen3(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(3),getActivePlayer().getScoreSheet().getGaia()));
        Green3.setText("X");
    }
    public void attackGreen4(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(4),getActivePlayer().getScoreSheet().getGaia()));
        Green4.setText("X");
    }
    public void attackGreen5(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(5),getActivePlayer().getScoreSheet().getGaia()));
        Green5.setText("X");
    }
    public void attackGreen6(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(6),getActivePlayer().getScoreSheet().getGaia()));
        Green6.setText("X");
    }
    public void attackGreen7(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(7),getActivePlayer().getScoreSheet().getGaia()));
        Green7.setText("X");
    }
    public void attackGreen8(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(8),getActivePlayer().getScoreSheet().getGaia()));
        Green8.setText("X");
    }
    public void attackGreen9(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(9),getActivePlayer().getScoreSheet().getGaia()));
        Green9.setText("X");
    }
    public void attackGreen10(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(10),getActivePlayer().getScoreSheet().getGaia()));
        Green10.setText("X");
    }
    public void attackGreen11(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(11),getActivePlayer().getScoreSheet().getGaia()));
        Green11.setText("X");
    }
    public void attackGreen12(ActionEvent event){
        boolean b = makeMove(getActivePlayer(), new Move(new GreenDice(12),getActivePlayer().getScoreSheet().getGaia()));
        Green12.setText("X");
    }
    




    @FXML
    private Button Blue1;
    @FXML
    private Button Blue2; 
    @FXML  
    private Button Blue3;
    @FXML
    private Button Blue4;
    @FXML
    private Button Blue5;
    @FXML
    private Button Blue6;
    @FXML
    private Button Blue7;
    @FXML
    private Button Blue8;
    @FXML
    private Button Blue9;
    @FXML
    private Button Blue10;
    @FXML
    private Button Blue11;

    private void highlightBluePossibleMoves(ActionEvent event){
        int i = getActivePlayer().getScoreSheet().getHydra().headsKilled();
        int value = Integer.parseInt(die3.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(value));
        if(m.length>0 ){
            switch(i){
                case 0:
                    Blue1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 1:
                    Blue2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 2:
                    Blue3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 3:
                    Blue4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 4:
                    Blue5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 5:
                    Blue6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 6:
                    Blue7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 7:
                    Blue8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 8:
                    Blue9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 9:
                    Blue10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 10:
                    Blue11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
            }
        }
    }

    public void attackBlue1(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(1));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue2(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(2));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue3(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(3));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue4(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(4));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue5(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(5));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue6(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(1));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue7(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(2));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue8(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(3));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue9(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(4));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue10(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(5));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }
     public void attackBlue11(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(6));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(1),getActivePlayer().getScoreSheet().getHydra()));
        }
     }

   
 }





    

   






