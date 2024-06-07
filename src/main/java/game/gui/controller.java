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


//buttons
    @FXML
    private Button F1, F2, F3, W1, W2, W4, T1, T3, T4, H2, H3, H4;
    @FXML
    private Button Green2, Green3, Green4, Green5, Green6, Green7, Green8, Green9, Green10, Green11, Green12;
    @FXML
    private Button Blue1, Blue2, Blue3, Blue4, Blue5, Blue6, Blue7, Blue8, Blue9, Blue10, Blue11;
    @FXML
    private Button Magenta1, Magenta2, Magenta3, Magenta4, Magenta5, Magenta6, Magenta7, Magenta8, Magenta9, Magenta10, Magenta11;
    @FXML
    private Button Yellow1, Yellow2, Yellow3, Yellow4, Yellow5, Yellow6, Yellow7, Yellow8, Yellow9, Yellow10, Yellow11;
    @FXML
    private Button rollButton, die1, die2, die3, die4, die5, die6, selectedButton = null;
    @FXML
    private Label round1, round2, round3, round4;

    // Method to change the text of buttons randomly
    public void changeButtonsRandomly() {
        Random random = new Random();
        rollDice();
        // Iterate over your buttons and assign random values to them
        for (int i=0;i<6;i++) {
            getButtons()[i].setText(""+getAllDice()[i].getValue());
            getButtons()[i].setDisable(false);
            getButtons()[i].getStyleClass().remove("selected-button");
        }
        for(Button button : new Button[]{F1, F2, F3, W1, W2, W4, T1, T3, T4, H2, H3, H4}) {
            button.setStyle("");
        }
        for(Button button : new Button[]{Green2, Green3, Green4, Green5, Green6, Green7, Green8, Green9, Green10, Green11, Green12}) {
            button.setStyle("");
        }
        for(Button button : new Button[]{Blue1, Blue2, Blue3, Blue4, Blue5, Blue6, Blue7, Blue8, Blue9, Blue10, Blue11}) {
            button.setStyle("");
        }
        for(Button button : new Button[]{Magenta1, Magenta2, Magenta3, Magenta4, Magenta5, Magenta6, Magenta7, Magenta8, Magenta9, Magenta10, Magenta11}) {
            button.setStyle("");
        }
        for(Button button : new Button[]{Yellow1, Yellow2, Yellow3, Yellow4, Yellow5, Yellow6, Yellow7, Yellow8, Yellow9, Yellow10, Yellow11}) {
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
        if(button.equals(die1))
            highlightRedPossibleMoves();
        else if(button.equals(die2))
            highlightGreenPossibleMoves();
        else if(button.equals(die3))
            highlightBluePossibleMoves();
        else if(button.equals(die4))
            highlightMagentaPossibleMoves();
        else if(button.equals(die5))
            highlightYellowPossibleMoves();

        // Add the selection class to the new button
        selectedButton.getStyleClass().add("selected-button");
    }

    // Helper method to get all buttons
    private Button[] getButtons() {
        // Return an array of your buttons here
        return new Button[]{die1, die2, die3, die4, die5, die6}; // Add more buttons as needed...
    }

    private void highlightRedPossibleMoves(){

        // for(int i = 0; i < moves.length;i++){
        //     nums[i] = moves[i].getDice().getValue();
        // }
        int i = getAllDice()[0].getValue();
        Move[] moves = getPossibleMovesForADie(getActivePlayer(), getAllDice()[0]);
        if(moves.length>0){
            if(F1.getText().equals(""+i)){
                F1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(F2.getText().equals(""+i)){
                F2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(F3.getText().equals(""+i)){
                F3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(W1.getText().equals(""+i)){
                F2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(W2.getText().equals(""+i)){
                W2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(W4.getText().equals(""+i)){
                W4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(T1.getText().equals(""+i)){
                T1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(T3.getText().equals(""+i)){
                T3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(T4.getText().equals(""+i)){
                T4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(H2.getText().equals(""+i)){
                H2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(H3.getText().equals(""+i)){
                H3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(H4.getText().equals(""+i)){
                H4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
             if(F2.getText().equals(""+i)){
                F2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }

        }
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



     public void attackRed(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die5.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new RedDice(i));
        if(m.length>0){
            if(button.equals(F1)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                F1.setText("X");
            }
            else if(button.equals(F2)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                F2.setText("X");
            }
            else if(button.equals(F3)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                F3.setText("X");
            }
            else if(button.equals(W1)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                W1.setText("X");
            }
            else if(button.equals(W2)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                W2.setText("X");
            }
            else if(button.equals(W4)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                W4.setText("X");
            }
            else if(button.equals(T1)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                T1.setText("X");
            }
            else if(button.equals(T3)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                T3.setText("X");
            }
            else if(button.equals(T4)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                T4.setText("X");
            }
            else if(button.equals(H2)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                H2.setText("X");
            }
            else if(button.equals(H3)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                H3.setText("X");
            }
            else if(button.equals(H4)){
                makeMove(getActivePlayer(), new Move(new RedDice(i),getActivePlayer().getScoreSheet().getDragon()));
                H4.setText("X");
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





    

   






