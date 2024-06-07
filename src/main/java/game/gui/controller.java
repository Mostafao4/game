package game.gui;

import java.io.IOException;
import java.util.Random;

import game.engine.*;
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
import game.dice.*;
import game.engine.Move;


public class controller extends CLIGameController {


//buttons
    @FXML
    private Button F1, F2, F3, W1, W2, W4, T1, T3, T4, H2, H3, H4, f1,f2,f3,w1,w2,w4,t1,t3,t4,h2,h3,h4;
    @FXML
    private Button Green2, Green3, Green4, Green5, Green6, Green7, Green8, Green9, Green10, Green11, Green12, g2,g3,g4,g5,g6,g7,g8,g9,g10,g11,g12;
    @FXML
    private Button Blue1, Blue2, Blue3, Blue4, Blue5, Blue6, Blue7, Blue8, Blue9, Blue10, Blue11,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
    @FXML
    private Button Magenta1, Magenta2, Magenta3, Magenta4, Magenta5, Magenta6, Magenta7, Magenta8, Magenta9, Magenta10, Magenta11,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11;
    @FXML
    private Button Yellow1, Yellow2, Yellow3, Yellow4, Yellow5, Yellow6, Yellow7, Yellow8, Yellow9, Yellow10, Yellow11,y1,y2,y3,y4,y5,y6,y7,y8,y9,y10,y11;
    @FXML
    private Button rollButton, die1, die2, die3, die4, die5, die6, selectedButton = null, forgottenRealm1, forgottenRealm2, forgottenRealm3, forgottenRealm4, forgottenRealm5, forgottenRealm6;
    @FXML
    private Label round1, round2, round3, round4, round, player1Turn, player2Turn, gameStat;

    @FXML
    // Method to change the text of buttons randomly
    public void rollButtons() {
        Random random = new Random();
        rollDice();
        // Iterate over your buttons and assign random values to them
        for (int i=0;i<6;i++) {
            if(getButtons()[i].isDisable()){
                continue;
            }
            getButtons()[i].setText(""+getAllDice()[i].getValue());
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


    // Helper method to get all buttons
    public Button[] getButtons() {
        // Return an array of your buttons here
        return new Button[]{die1, die2, die3, die4, die5, die6}; // Add more buttons as needed...
    }
    private Button[] getForgottenRealmButtons() {
        return new Button[]{forgottenRealm1, forgottenRealm2, forgottenRealm3, forgottenRealm4, forgottenRealm5, forgottenRealm6};
    }
    private Button[][] getPLayer1Buttons(){
        return new Button[][]{
                {F1, F2, F3, W1, W2, W4, T1, T3, T4, H2, H3, H4},
                {Green2, Green3, Green4, Green5, Green6, Green7, Green8, Green9, Green10, Green11, Green12},
                {Blue1, Blue2, Blue3, Blue4, Blue5, Blue6, Blue7, Blue8, Blue9, Blue10, Blue11},
                {Magenta1, Magenta2, Magenta3, Magenta4, Magenta5, Magenta6, Magenta7, Magenta8, Magenta9, Magenta10, Magenta11},
                {Yellow1, Yellow2, Yellow3, Yellow4, Yellow5, Yellow6, Yellow7, Yellow8, Yellow9, Yellow10, Yellow11}
        };
    }
    private Button[][] getPLayer2Buttons(){
        return new Button[][]{
                {f1,f2,f3,w1,w2,w4,t1,t3,t4,h2,h3,h4},
                {g2,g3,g4,g5,g6,g7,g8,g9,g10,g11,g12},
                {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11},
                {m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11},
                {y1,y2,y3,y4,y5,y6,y7,y8,y9,y10,y11}
        };
    }

    public void startGame(ActionEvent event){
        gameStat.setText("Welcome to Dice Realms!!!\nPlayer 1's turn");
        if(getGameStatus().getPartOfRound()==0) {
            player1Turn.setText(""+getGameStatus().getTurn());
            player2Turn.setText("--");
        }
        else {
            player1Turn.setText("--");
            player2Turn.setText(""+getGameStatus().getTurn());
        }
        enablePlayer1();
        for(Button b:getButtons()){
            b.setDisable(false);
            rollButton.setDisable(false);
        }
        for(Button b : getForgottenRealmButtons()){
            b.setVisible(false);
        }


    }
    public void gameLoop(){
        while (getGameStatus().getRound() <= 6) {
            while(getGameStatus().getPartOfRound()<2) {
                if(getGameStatus().getRound() == 7)
                    break;
                while (getGameStatus().getTurn() <= 3 && thereAreAvailableDice()) {

                    rollButtons();
                    //timeWarpPrompt();
                }

                endTurn();
            }
        }
        endGame();
    }
    public void startTurn(){
        rollDice();
    }


    public void timeWarpPrompt(ActionEvent event){

            gameStat.setText("Available Time Warps: "+getActivePlayer().getTimeWarpCount());


    }
    public void useTimeWarp(ActionEvent event){
        getActivePlayer().subtractTimeWarpCount();
        rollButtons();
    }



    public void addTurn(int i){
        getGameStatus().incrementTurn();
        if(getGameStatus().getPartOfRound()==0) {
            player1Turn.setText(""+getGameStatus().getTurn());
            player2Turn.setText("--");
        }
        else {
            player1Turn.setText("--");
            player2Turn.setText(""+getGameStatus().getTurn());
        }




        if(getGameStatus().getTurn()==4) {
            gameStat.setText(getPassivePlayer().getPlayerName() + ": Choose a die from the forgotten realm");
            for (Button button : getButtons()) {
                button.setDisable(true);
                rollButton.setDisable(true);
            }
            for(int j=0;j<getForgottenRealmButtons().length;j++) {
                getForgottenRealmButtons()[j].setText(""+getForgottenRealmDice()[j].getValue());
                getForgottenRealmButtons()[j].setDisable(false);
            }
        }
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
        if(getGameStatus().getPartOfRound()==0) {
            if (button.equals(die1))
                highlightRedPossibleMoves1();
            else if (button.equals(die2))
                highlightGreenPossibleMoves1();
            else if (button.equals(die3))
                highlightBluePossibleMoves1();
            else if (button.equals(die4))
                highlightMagentaPossibleMoves1();
            else if (button.equals(die5))
                highlightYellowPossibleMoves1();
        }
        else{
            if(button.equals(die1))
                highlightRedPossibleMoves2();
            else if(button.equals(die2))
                highlightGreenPossibleMoves2();
            else if(button.equals(die3))
                highlightBluePossibleMoves2();
            else if(button.equals(die4))
                highlightMagentaPossibleMoves2();
            else if(button.equals(die5))
                highlightYellowPossibleMoves2();
        }
        // Add the selection class to the new button
        selectedButton.getStyleClass().add("selected-button");
    }
    public void highlightRedPossibleMoves1(){
        int i = getAllDice()[0].getValue();
        Move[] moves = getPossibleMovesForADie(getGameBoard().getPlayer1(), getAllDice()[0]);
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
                W1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
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
        }
}
    public void highlightGreenPossibleMoves1(){
            int h = Integer.parseInt(die2.getText()) + Integer.parseInt(die6.getText());
            Move[] m = getGameBoard().getPlayer1().getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(h));
            if(m.length==0){
                return;
            }
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
    public void highlightBluePossibleMoves1(){
        int i = getGameBoard().getPlayer1().getScoreSheet().getHydra().headsKilled();
        int value = Integer.parseInt(die3.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new BlueDice(value));
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
    public void highlightMagentaPossibleMoves1(){
        int i = getGameBoard().getPlayer1().getScoreSheet().getPhoenix().getCount();
        int value = Integer.parseInt(die4.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new MagentaDice(value));
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
    public void highlightYellowPossibleMoves1(){
        int i = getGameBoard().getPlayer1().getScoreSheet().getLion().getHitNum();
        int value = Integer.parseInt(die5.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new YellowDice(value));
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
    public void attackRed1(ActionEvent event) {
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die1.getText());
        RedDice d = new RedDice(i);
        Move[] m = getGameBoard().getPlayer1().getScoreSheet().getDragon().getPossibleMovesForADie(d);
        boolean b;
        if (m.length > 0) {
            if (button.equals(F1)) {
                d.selectsDragon(1);
                b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                F1.setText("X");
            }
                else if (button.equals(F2)) {
                    d.selectsDragon(2);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                    F2.setText("X");
                } else if (button.equals(F3)) {
                    d.selectsDragon(3);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        F3.setText("X");
                } else if (button.equals(W1)) {
                    d.selectsDragon(1);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        W1.setText("X");
                } else if (button.equals(W2)) {
                    d.selectsDragon(2);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        W2.setText("X");
                } else if (button.equals(W4)) {
                    d.selectsDragon(4);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        W4.setText("X");
                } else if (button.equals(T1)) {
                    d.selectsDragon(1);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        T1.setText("X");
                } else if (button.equals(T3)) {
                    d.selectsDragon(3);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        T3.setText("X");
                } else if (button.equals(T4)) {
                    d.selectsDragon(4);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        T4.setText("X");
                } else if (button.equals(H2)) {
                    d.selectsDragon(2);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        H2.setText("X");
                } else if (button.equals(H3)) {
                    d.selectsDragon(3);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        H3.setText("X");
                } else if (button.equals(H4)) {
                    d.selectsDragon(4);
                    b = makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()));
                        H4.setText("X");
                }
            }
        die1.setDisable(true);
        forgottenRealm1.setText(die1.getText());
        for (int h=0;h<6;h++) {
            if(Integer.parseInt(getButtons()[h].getText()) < i) {
                getButtons()[h].setDisable(true);
                getForgottenRealmButtons()[h].setText(Integer.parseInt(getButtons()[h].getText())+"");
                getForgottenRealmButtons()[h].setVisible(true);
                getButtons()[h].setVisible(false);
            }
        }
        die1.setVisible(false);
        forgottenRealm1.setVisible(true);
        addTurn(1);
        }
    public void attackGreen1(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die2.getText())+Integer.parseInt(die6.getText());
        Dice d = new GreenDice(i);
        Dice h = getAllDice()[1];
        Move[] m = getGameBoard().getPlayer1().getScoreSheet().getGaia().getPossibleMovesForADie(d);
        if(m.length>0){
            if(button.equals(Green2)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green2.setText("X");
                }
            }
            else if(button.equals(Green3)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia())))
                {
                    Green3.setText("X");
                }
            }
            else if(button.equals(Green4)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green4.setText("X");
                }
            }
            else if(button.equals(Green5)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green5.setText("X");
                }
            }
            else if(button.equals(Green6)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green6.setText("X");
                }
            }
            else if(button.equals(Green7)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green7.setText("X");
                }
            }
            else if(button.equals(Green8)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green8.setText("X");
                }
            }
            else if(button.equals(Green9)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green9.setText("X");
                }
            }
            else if(button.equals(Green10)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green10.setText("X");
                }
            }
            else if(button.equals(Green11)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green11.setText("X");
                }
            }
            else if(button.equals(Green12)){
                if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                    Green12.setText("X");
                }
            }
        }
        forgottenRealm2.setText(die2.getText());
        for (int r=0;r<6;r++) {
            if(Integer.parseInt(getButtons()[r].getText()) < i) {
                getButtons()[r].setDisable(true);
                getForgottenRealmButtons()[r].setText(Integer.parseInt(getButtons()[r].getText())+"");
                getForgottenRealmButtons()[r].setVisible(true);
                getButtons()[r].setVisible(false);
            }
        }
        die2.setVisible(false);
        forgottenRealm2.setVisible(true);
        addTurn(2);

    }
    public void attackBlue1(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die3.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new BlueDice(i));
        if(m.length>0){
            if(button.equals(Blue1)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue1.setText("X");
            }
            else if(button.equals(Blue2)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue2.setText("X");
            }
            else if(button.equals(Blue3)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue3.setText("X");
            }
            else if(button.equals(Blue4)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue4.setText("X");
            }
            else if(button.equals(Blue5)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue5.setText("X");
            }
            else if(button.equals(Blue6)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue6.setText("X");
            }
            else if(button.equals(Blue7)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue7.setText("X");
            }
            else if(button.equals(Blue8)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue8.setText("X");
            }
            else if(button.equals(Blue9)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue9.setText("X");
            }
            else if(button.equals(Blue10)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue10.setText("X");
            }
            else if(button.equals(Blue11)){
                makeMove(getGameBoard().getPlayer1(), new Move(new BlueDice(i),getGameBoard().getPlayer1().getScoreSheet().getHydra()));
                Blue11.setText("X");
            }
        }
        forgottenRealm3.setText(die3.getText());
        for (int h=0;h<6;h++) {
            if(Integer.parseInt(getButtons()[h].getText()) < i) {
                getButtons()[h].setDisable(true);
                getForgottenRealmButtons()[h].setText(Integer.parseInt(getButtons()[h].getText())+"");
                getForgottenRealmButtons()[h].setVisible(true);
                getButtons()[h].setVisible(false);
            }
        }
        die3.setVisible(false);
        forgottenRealm3.setVisible(true);
        addTurn(3);
    }
    public void attackMagenta1(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die4.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new MagentaDice(i));
        if(m.length>0){
            if(button.equals(Magenta1)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta1.setText(""+i);
            }
            else if(button.equals(Magenta2)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta2.setText(""+i);
            }
            else if(button.equals(Magenta3)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta3.setText(""+i);
            }
            else if(button.equals(Magenta4)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta4.setText(""+i);
            }
            else if(button.equals(Magenta5)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta5.setText(""+i);
            }
            else if(button.equals(Magenta6)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta6.setText(""+i);
            }
            else if(button.equals(Magenta7)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta7.setText(""+i);
            }
            else if(button.equals(Magenta8)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta8.setText(""+i);
            }
            else if(button.equals(Magenta9)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta9.setText(""+i);
            }
            else if(button.equals(Magenta10)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta10.setText(""+i);
            }
            else if(button.equals(Magenta11)){
                makeMove(getGameBoard().getPlayer1(), new Move(new MagentaDice(i),getGameBoard().getPlayer1().getScoreSheet().getPhoenix()));
                Magenta11.setText(""+i);
            }
        }
        forgottenRealm4.setText(die4.getText());
        for (int h=0;h<6;h++) {
            if(Integer.parseInt(getButtons()[h].getText()) < i) {
                getButtons()[h].setDisable(true);
                getForgottenRealmButtons()[h].setText(Integer.parseInt(getButtons()[h].getText())+"");
                getForgottenRealmButtons()[h].setVisible(true);
                getButtons()[h].setVisible(false);
            }
        }
        die4.setVisible(false);
        forgottenRealm4.setVisible(true);
        addTurn(4);

    }
    public void attackYellow1(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die5.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new YellowDice(i));
        if(m.length>0){
            if(button.equals(Yellow1)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow1.setText(""+i);
            }
            else if(button.equals(Yellow2)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow2.setText(""+i);
            }
            else if(button.equals(Yellow3)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow3.setText(""+i);
            }
            else if(button.equals(Yellow4)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow4.setText(""+i*2);
            }
            else if(button.equals(Yellow5)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow5.setText(""+i);
            }
            else if(button.equals(Yellow6)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow6.setText(""+i);
            }
            else if(button.equals(Yellow7)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow7.setText(""+i*2);
            }
            else if(button.equals(Yellow8)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow8.setText(""+i);
            }
            else if(button.equals(Yellow9)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow9.setText(""+i*2);
            }
            else if(button.equals(Yellow10)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow10.setText(""+i);
            }
            else if(button.equals(Yellow11)){
                makeMove(getGameBoard().getPlayer1(), new Move(new YellowDice(i),getGameBoard().getPlayer1().getScoreSheet().getLion()));
                Yellow11.setText(""+i*3);
            }
        }
        forgottenRealm5.setText(die5.getText());
        for (int h=0;h<6;h++) {
            if(Integer.parseInt(getButtons()[h].getText()) < i) {
                getButtons()[h].setDisable(true);
                getForgottenRealmButtons()[h].setText(Integer.parseInt(getButtons()[h].getText())+"");
                getForgottenRealmButtons()[h].setVisible(true);
                getButtons()[h].setVisible(false);
            }
        }
        die5.setVisible(false);
        forgottenRealm5.setVisible(true);
        addTurn(5);

    }


    public void highlightRedPossibleMoves2(){
        int i = getAllDice()[0].getValue();
        Move[] moves = getPossibleMovesForADie(getActivePlayer(), getAllDice()[0]);
        if(moves.length>0){
            if(f1.getText().equals(""+i)){
                f1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(f2.getText().equals(""+i)){
                f2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(f3.getText().equals(""+i)){
                f3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(w1.getText().equals(""+i)){
                w1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(w2.getText().equals(""+i)){
                w2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(w4.getText().equals(""+i)){
                w4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(t1.getText().equals(""+i)){
                t1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(t3.getText().equals(""+i)){
                t3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(t4.getText().equals(""+i)){
                t4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(h2.getText().equals(""+i)){
                h2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(h3.getText().equals(""+i)){
                h3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
            if(h4.getText().equals(""+i)){
                h4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            }
        }
    }
    public void highlightGreenPossibleMoves2(){
        int h = Integer.parseInt(die2.getText()) + Integer.parseInt(die6.getText());
        Move[] m = getActivePlayer().getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(h));
        if(m.length==0){
            return;
        }
        switch(h){
            case 2:
                g2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 3:
                g3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 4:
                g4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 5:
                g5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 6:
                g6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 7:
                g7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 8:
                g8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 9:
                g9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 10:
                g10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 11:
                g11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
            case 12:
                g12.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                break;
        }
    }
    public void highlightBluePossibleMoves2(){
        int i = getActivePlayer().getScoreSheet().getHydra().headsKilled();
        int value = Integer.parseInt(die3.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(value));
        if(m.length>0 ){
            switch(i){
                case 0:
                    b1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 1:
                    b2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 2:
                    b3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 3:
                    b4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 4:
                    b5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 5:
                    b6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 6:
                    b7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 7:
                    b8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 8:
                    b9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 9:
                    b10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 10:
                    b11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
            }
        }
    }
    public void highlightMagentaPossibleMoves2(){
        int i = getActivePlayer().getScoreSheet().getPhoenix().getCount();
        int value = Integer.parseInt(die4.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new MagentaDice(value));
        if(m.length>0 ){
            switch(i){
                case 0:
                    m1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 1:
                    m2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 2:
                    m3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 3:
                    m4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 4:
                    m5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 5:
                    m6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 6:
                    m7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 7:
                    m8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 8:
                    m9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 9:
                    m10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 10:
                    m11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
            }
        }
    }
    public void highlightYellowPossibleMoves2(){
        int i = getActivePlayer().getScoreSheet().getLion().getHitNum();
        int value = Integer.parseInt(die5.getText());
        Move[] m = getPossibleMovesForADie(getActivePlayer(), new YellowDice(value));
        if(m.length>0 ){
            switch(i){
                case 0:
                    y1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 1:
                    y2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 2:
                    y3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 3:
                    y4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 4:
                    y5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 5:
                    y6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 6:
                    y7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 7:
                    y8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 8:
                    y9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 9:
                    y10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
                case 10:
                    y11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    break;
            }
        }
    }
    public void attackRed2(ActionEvent event) {
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die1.getText());
        RedDice d = new RedDice(i);
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), d);
        boolean b = false;
        if (m.length > 0) {
            if (button.equals(f1)) {
                d.selectsDragon(1);
                b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                    f1.setText("X");
                } else if (button.equals(f2)) {
                    d.selectsDragon(2);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        f2.setText("X");
                } else if (button.equals(f3)) {
                    d.selectsDragon(3);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        f3.setText("X");
                } else if (button.equals(w1)) {
                    d.selectsDragon(1);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        w1.setText("X");
                } else if (button.equals(w2)) {
                    d.selectsDragon(2);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        w2.setText("X");
                } else if (button.equals(w4)) {
                    d.selectsDragon(4);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        w4.setText("X");
                } else if (button.equals(t1)) {
                    d.selectsDragon(1);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        t1.setText("X");
                } else if (button.equals(t3)) {
                    d.selectsDragon(3);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        t3.setText("X");
                } else if (button.equals(t4)) {
                    d.selectsDragon(4);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        t4.setText("X");
                } else if (button.equals(h2)) {
                    d.selectsDragon(2);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        h2.setText("X");
                } else if (button.equals(h3)) {
                    d.selectsDragon(3);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        h3.setText("X");
                } else if (button.equals(h4)) {
                    d.selectsDragon(4);
                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
                        h4.setText("X");
                }
            }
        }
    public void attackGreen2(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die2.getText())+Integer.parseInt(die6.getText());
        Dice d = new GreenDice(i);
        Dice h = getAllDice()[1];
        Move[] m = getGameBoard().getPlayer2().getScoreSheet().getGaia().getPossibleMovesForADie(d);
        if(m.length>0){
            if(button.equals(g2)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g2.setText("X");
                }
            }
            else if(button.equals(g3)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia())))
                {
                    g3.setText("X");
                }
            }
            else if(button.equals(g4)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g4.setText("X");
                }
            }
            else if(button.equals(g5)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g5.setText("X");
                }
            }
            else if(button.equals(g6)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g6.setText("X");
                }
            }
            else if(button.equals(g7)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g7.setText("X");
                }
            }
            else if(button.equals(g8)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g8.setText("X");
                }
            }
            else if(button.equals(g9)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g9.setText("X");
                }
            }
            else if(button.equals(g10)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g10.setText("X");
                }
            }
            else if(button.equals(g11)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g11.setText("X");
                }
            }
            else if(button.equals(g12)){
                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                    g12.setText("X");
                }
            }
        }
    }
    public void attackBlue2(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die3.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new BlueDice(i));
        if(m.length>0){
            if(button.equals(b1)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b1.setText("X");
            }
            else if(button.equals(b2)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b2.setText("X");
            }
            else if(button.equals(b3)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b3.setText("X");
            }
            else if(button.equals(b4)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b4.setText("X");
            }
            else if(button.equals(b5)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b5.setText("X");
            }
            else if(button.equals(b6)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b6.setText("X");
            }
            else if(button.equals(b7)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b7.setText("X");
            }
            else if(button.equals(b8)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b8.setText("X");
            }
            else if(button.equals(b9)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b9.setText("X");
            }
            else if(button.equals(b10)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b10.setText("X");
            }
            else if(button.equals(b11)){
                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
                b11.setText("X");
            }
        }
    }
    public void attackMagenta2(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die4.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new MagentaDice(i));
        if(m.length>0){
            if(button.equals(m1)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m1.setText(""+i);
            }
            else if(button.equals(m2)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m2.setText(""+i);
            }
            else if(button.equals(m3)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m3.setText(""+i);
            }
            else if(button.equals(m4)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m4.setText(""+i);
            }
            else if(button.equals(m5)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m5.setText(""+i);
            }
            else if(button.equals(m6)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m6.setText(""+i);
            }
            else if(button.equals(m7)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m7.setText(""+i);
            }
            else if(button.equals(m8)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m8.setText(""+i);
            }
            else if(button.equals(m9)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m9.setText(""+i);
            }
            else if(button.equals(m10)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m10.setText(""+i);
            }
            else if(button.equals(m11)){
                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
                m11.setText(""+i);
            }
        }
    }
    public void attackYellow2(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die5.getText());
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new YellowDice(i));
        if(m.length>0){
            if(button.equals(y1)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y1.setText(""+i);
            }
            else if(button.equals(y2)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y2.setText(""+i);
            }
            else if(button.equals(y3)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y3.setText(""+i);
            }
            else if(button.equals(y4)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y4.setText(""+i*2);
            }
            else if(button.equals(y5)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y5.setText(""+i);
            }
            else if(button.equals(y6)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y6.setText(""+i);
            }
            else if(button.equals(y7)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y7.setText(""+i*2);
            }
            else if(button.equals(y8)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y8.setText(""+i);
            }
            else if(button.equals(y9)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y9.setText(""+i*2);
            }
            else if(button.equals(y10)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y10.setText(""+i);
            }
            else if(button.equals(y11)){
                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
                y11.setText(""+i*3);
            }
        }
    }

    private void disablePlayer1(){
        for(int i=0;i<getPLayer1Buttons().length;i++) {
            for(int j=0;j<getPLayer1Buttons()[i].length;j++) {
                getPLayer1Buttons()[i][j].setDisable(true);
            }
        }
    }
    private void disablePlayer2(){
        for(int i=0;i<getPLayer2Buttons().length;i++) {
            for(int j=0;j<getPLayer2Buttons()[i].length;j++) {
                getPLayer2Buttons()[i][j].setDisable(true);
            }
        }
    }
    private void enablePlayer1(){
        for(int i=0;i<getPLayer1Buttons().length;i++) {
            for(int j=0;j<getPLayer1Buttons()[i].length;j++) {
                getPLayer1Buttons()[i][j].setDisable(false);
            }
        }
    }
    private void enablePlayer2(){
        for(int i=0;i<getPLayer2Buttons().length;i++) {
            for(int j=0;j<getPLayer2Buttons()[i].length;j++) {
                getPLayer2Buttons()[i][j].setDisable(false);
            }
        }
    }
   
 }





    

   






