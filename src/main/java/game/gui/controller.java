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
        for(Button button : new Button[]{Green2,Green3,Green4,Green5,Green6,Green7,Green8,Green9,Green10,Green11,Green12}){
            button.setStyle("");;
        }
        for(Button button : new Button[]{Blue1,Blue2,Blue3,Blue4,Blue5,Blue6,Blue7,Blue8,Blue9,Blue10,Blue11}){
            button.setStyle("");
        }
        for(Button button : new Button[]{Magenta1,Magenta2,Magenta3,Magenta4,Magenta5,Magenta6,Magenta7,Magenta8,Magenta9,Magenta10,Magenta11}){
            button.setStyle("");
        }
        for(Button button : new Button[]{Yellow1,Yellow2,Yellow3,Yellow4,Yellow5,Yellow6,Yellow7,Yellow8,Yellow9,Yellow10,Yellow11}){
            button.setStyle("");
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
        if(button.equals(die2)){
            highlightGreenPossibleMoves();
        }
        else if(button.equals(die3)){
            highlightBluePossibleMoves();
        }
        else if(button.equals(die4)){
            highlightMagentaPossibleMoves();
        }
        else if(button.equals(die5)){
            highlightYellowPossibleMoves();
        }

        // Add the selection class to the new button
        selectedButton.getStyleClass().add("selected-button");
    }

    // Helper method to get all buttons
    private Button[] getButtons() {
        // Return an array of your buttons here
        return new Button[]{die1, die2, die3, die4, die5, die6}; // Add more buttons as needed...
    }


    

    private void highlightGreenPossibleMoves(){

            // for(int i = 0; i < moves.length;i++){
            //     nums[i] = moves[i].getDice().getValue();
            // } 
            int h = Integer.parseInt(die2.getText()) + Integer.parseInt(die6.getText());
            Move[] moves = getPossibleMovesForADie(getActivePlayer(), new GreenDice(h));
            if(moves.length>0){
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

    @FXML
    private Button Magenta1;
    @FXML
    private Button Magenta2; 
    @FXML  
    private Button Magenta3;
    @FXML
    private Button Magenta4;
    @FXML
    private Button Magenta5;
    @FXML
    private Button Magenta6;
    @FXML
    private Button Magenta7;
    @FXML
    private Button Magenta8;
    @FXML
    private Button Magenta9;
    @FXML
    private Button Magenta10;
    @FXML
    private Button Magenta11;

    @FXML
    private Button Yellow1;
    @FXML
    private Button Yellow2; 
    @FXML  
    private Button Yellow3;
    @FXML
    private Button Yellow4;
    @FXML
    private Button Yellow5;
    @FXML
    private Button Yellow6;
    @FXML
    private Button Yellow7;
    @FXML
    private Button Yellow8;
    @FXML
    private Button Yellow9;
    @FXML
    private Button Yellow10;
    @FXML
    private Button Yellow11;

    private void highlightBluePossibleMoves(){
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
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue1.setText("X");
        }
     }
     public void attackBlue2(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue2.setText("X");
        }
     }
     public void attackBlue3(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue3.setText("X");
        }
     }
     public void attackBlue4(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue4.setText("X");
        }
     }
     public void attackBlue5(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue5.setText("X");
        }
     }
     public void attackBlue6(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue6.setText("X");
        }
     }
     public void attackBlue7(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue7.setText("X");
        }
     }
     public void attackBlue8(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue8.setText("X");
        }
     }
     public void attackBlue9(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue9.setText("X");
        }
     }
     public void attackBlue10(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue10.setText("X");
        }
     }
     public void attackBlue11(ActionEvent event){   
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(Integer.parseInt(die3.getText())));
        if(m.length>0){
            makeMove(getActivePlayer(), new Move(new BlueDice(6),getActivePlayer().getScoreSheet().getHydra()));
            Blue11.setText("X");
        }
     }


     public void highlightMagentaPossibleMoves(){
        int i = getActivePlayer().getScoreSheet().getPhoenix().getCount();
        int value = Integer.parseInt(die4.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new MagentaDice(value));
        if(m.length>0 ){
            switch(i){
                case 0:
                    Magenta1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 1:
                    Magenta2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 2:
                    Magenta3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 3:
                    Magenta4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 4:
                    Magenta5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 5:
                    Magenta6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 6:
                    Magenta7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 7:
                    Magenta8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 8:
                    Magenta9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 9:
                    Magenta10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 10:
                    Magenta11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
            }
        }
     }


     public void highlightYellowPossibleMoves(){
        int i = getActivePlayer().getScoreSheet().getLion().getHitNum();
        int value = Integer.parseInt(die5.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new YellowDice(value));
        if(m.length>0 ){
            switch(i){
                case 0:
                    Yellow1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 1:
                    Yellow2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 2:
                    Yellow3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 3:
                    Yellow4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 4:
                    Yellow5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 5:
                    Yellow6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 6:
                    Yellow7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 7:
                    Yellow8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break; 
                case 8:
                    Yellow9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 9:
                    Yellow10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 10:
                    Yellow11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
            }
        }
     }

     public void attackMagenta(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die4.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new MagentaDice(i));
        if(m.length>0){
            if(button.equals(Magenta1)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta1.setText(""+i);
            }
            else if(button.equals(Magenta2)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta2.setText(""+i);
            }
            else if(button.equals(Magenta3)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta3.setText(""+i);
            }
            else if(button.equals(Magenta4)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta4.setText(""+i);
            }
            else if(button.equals(Magenta5)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta5.setText(""+i);
            }
            else if(button.equals(Magenta6)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta6.setText(""+i);
            }
            else if(button.equals(Magenta7)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta7.setText(""+i);
            }
            else if(button.equals(Magenta8)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta8.setText(""+i);
            }
            else if(button.equals(Magenta9)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta9.setText(""+i);
            }
            else if(button.equals(Magenta10)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta10.setText(""+i);
            }
            else if(button.equals(Magenta11)){
                makeMove(getActivePlayer(), new Move(new MagentaDice(i),getActivePlayer().getScoreSheet().getPhoenix()));
                Magenta11.setText(""+i);
            }
        }
     }

     public void yellowAttack(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die5.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new YellowDice(i));
        if(m.length>0){
            if(button.equals(Yellow1)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow1.setText(""+i);
            }
            else if(button.equals(Yellow2)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow2.setText(""+i);
            }
            else if(button.equals(Yellow3)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow3.setText(""+i);
            }
            else if(button.equals(Yellow4)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow4.setText(""+i);
            }
            else if(button.equals(Yellow5)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow5.setText(""+i);
            }
            else if(button.equals(Yellow6)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow6.setText(""+i);
            }
            else if(button.equals(Yellow7)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow7.setText(""+i);
            }
            else if(button.equals(Yellow8)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow8.setText(""+i);
            }
            else if(button.equals(Yellow9)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow9.setText(""+i);
            }
            else if(button.equals(Yellow10)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow10.setText(""+i);
            }
            else if(button.equals(Yellow11)){
                makeMove(getActivePlayer(), new Move(new YellowDice(i),getActivePlayer().getScoreSheet().getLion()));
                Yellow11.setText(""+i);
            }
        }
     }

   
 }





    

   






