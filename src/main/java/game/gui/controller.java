package game.gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import game.collectibles.ArcaneBoost;
import game.collectibles.Bonus;
import game.collectibles.ElementalCrest;
import game.collectibles.Reward;
import game.creatures.Realm;
import game.engine.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import game.dice.*;
import game.engine.Move;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class controller extends CLIGameController {


//buttons

    @FXML
    private Button F1, F2, F3, F4, W1, W2, W3, W4, T1, T2, T3, T4, H1, H2, H3, H4, R1, R2, R3, R4, R5, f1,f2,f3,f4,w1,w2,w3,w4,t1,t2,t3,t4,h1,h2,h3,h4,r1,r2,r3,r4,r5;
    @FXML
    private Label SR1, SR2, SR3, SR4, sr1, sr2, sr3, sr4;
    @FXML
    private Label SB1, SB2, SB3, SB4, SB5, SB6, SB7, SB8, SB9, SB10, SB11, sb1, sb2, sb3, sb4, sb5, sb6, sb7, sb8, sb9, sb10, sb11;
    @FXML
    private Button RB1, RB2, RB3, RB4, RB5, RB6, RB7, RB8, RB9, RB10, RB11, rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11;
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
    private Label round1, round2, round3, round4, round, player1Turn, player2Turn, gameStat, player1Label, player2Label;

    private int [] scoreRed = new int[4];
    private String [] reward = new String[5];
    private int [][] dragonParts = new int[4][4];
    private  Image[][] diceImages = new Image[6][6]; // 6 types, 6 values each
    private  ImageView[][] diceViews = new ImageView[6][6]; // Assuming 6 dice views

    public void setPlayerNames(String player1, String player2) {
        player1Label.setText("Player 1: " + player1);
        player2Label.setText("Player 2: " + player2);
    }

    @FXML
    public void initialize() {
        // Load dice images
        for (int type = 0; type < 6; type++) {
            for (int value = 0; value < 6; value++) {
                String imagePath = String.format("/type%dvalue%d.png", type + 1, value + 1);
                diceImages[type][value] = new Image(getClass().getResourceAsStream(imagePath));
            }
        }

        Properties prop = new Properties();
        try {
            // Load scores
            prop.load(new FileInputStream("src/main/resources/config/EmberfallDominionScore.properties"));
            for (int i = 0; i < 4; i++) {
                scoreRed[i] = Integer.parseInt(prop.getProperty("col" + (i + 1) + "Score"));
            }

            // Load rewards
            prop.load(new FileInputStream("src/main/resources/config/EmberfallDominionRewards.properties"));
            for (int i = 0; i < 4; i++) {
                reward[i] = prop.getProperty("row" + (i + 1) + "Reward");
            }
            reward[4] = prop.getProperty("diagonalReward");

            // Load dice values
            prop.load(new FileInputStream("src/main/resources/config/EmberfallDominionDiceValue.properties"));
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    dragonParts[row][col] = Integer.parseInt(prop.getProperty("row" + (row + 1) + "col" + (col + 1) + "DiceValue"));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Button[] fields1 = { F1, F2, F3, F4 };
        Button[] fields11 = { f1, f2, f3, f4 };
        for (int i = 0; i < 4; i++) {
            String x = dragonParts[0][i] == 0 ? "X" : String.valueOf(dragonParts[0][i]);
            fields1[i].setText(x);
            fields11[i].setText(x);
        }
        Button[] fields2 = { W1, W2, W3, W4 };
        Button[] fields22 = { w1, w2, w3, w4 };
        for (int i = 0; i < 4; i++) {
            String x = dragonParts[1][i] == 0 ? "X" : String.valueOf(dragonParts[1][i]);
            fields2[i].setText(x);
            fields22[i].setText(x);
        }
        Button[] fields3 = { T1, T2, T3, T4 };
        Button[] fields33 = { t1, t2, t3, t4 };
        for (int i = 0; i < 4; i++) {
            String x = dragonParts[2][i] == 0 ? "X" : String.valueOf(dragonParts[2][i]);
            fields3[i].setText(x);
            fields33[i].setText(x);
        }
        Button[] fields4 = { H1, H2, H3, H4 };
        Button[] fields44 = { h1, h2, h3, h4 };
        for (int i = 0; i < 4; i++) {
            String x = dragonParts[3][i] == 0 ? "X" : String.valueOf(dragonParts[3][i]);
            fields4[i].setText(x);
            fields44[i].setText(x);
        }
        Label [] scoreLabel1 = {SR1, SR2, SR3, SR4};
        Label [] scoreLabel11 = {sr1, sr2, sr3, sr4};
        for (int i = 0; i < 4; i++) {
            String x = scoreRed[i] +"";
            scoreLabel1[i].setText(x);
            scoreLabel11[i].setText(x);
        }
        Button[] fields5 = { R1, R2, R3, R4, R5};
        Button[] fields55 = { r1, r2, r3, r4, r5 };
        for (int i = 0; i < 5; i++) {
            String s = reward[i];
            switch (s){
                case "GreenBonus":
                    s = "GB";
                    break;
                case "YellowBonus":
                    s = "YB";
                    break;
                case "BlueBonus":
                    s = "BB";
                    break;
                case "ElementalCrest":
                    s = "EC";
                    break;
                case "ArcaneBoost":
                    s = "AB";
                    break;
                default:
                    s = "";
            }
            fields5[i].setText(s);
            fields55[i].setText(s);
        }

        Label [] SB = {SB1, SB2, SB3, SB4, SB5, SB6, SB7, SB8, SB9, SB10, SB11};
        Label [] sb = {sb1, sb2, sb3, sb4, sb5, sb6, sb7, sb8, sb9, sb10, sb11};
        int [] scoreBlue = new int[11];
        try{

            Properties prop1 = new Properties();
            FileInputStream scoreConfig = new FileInputStream("src/main/resources/config/TideAbyssScore.properties");
            prop1.load(scoreConfig);
            int score1 = Integer.parseInt(prop1.getProperty("col1"));
            int score2 = Integer.parseInt(prop1.getProperty("col2"));
            int score3 = Integer.parseInt(prop1.getProperty("col3"));
            int score4 = Integer.parseInt(prop1.getProperty("col4"));
            int score5 = Integer.parseInt(prop1.getProperty("col5"));
            int score6 = Integer.parseInt(prop1.getProperty("col6"));
            int score7 = Integer.parseInt(prop1.getProperty("col7"));
            int score8 = Integer.parseInt(prop1.getProperty("col8"));
            int score9 = Integer.parseInt(prop1.getProperty("col9"));
            int score10 = Integer.parseInt(prop1.getProperty("col10"));
            int score11 = Integer.parseInt(prop1.getProperty("col11"));
            scoreBlue = new int[]{score1,score2,score3,score4,score5,score6,score7,score8,score9,score10,score11};

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 11; i++) {
            String x = scoreBlue[i] +"";
            SB[i].setText(x);
            sb[i].setText(x);
        }
        Button [] RB = new Button[]{RB1, RB2, RB3, RB4, RB5, RB6, RB7, RB8, RB9, RB10, RB11};
        Button [] rb = new Button[]{rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11};
        String[] rew = new String[11];
        try{
            Properties prop2 = new Properties();
            FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/TideAbyssRewards.properties");
            prop2.load(rewardConfig);

            for (int i=0;i<11;i++){
                String r=prop2.getProperty("hit"+(i+1)+"Reward");
                rew[i]=r;
                switch (r){
                    case "ArcaneBoost":
                        rew[i]="AB";
                        break;
                    case "BlueBonus":
                        rew[i]="BB";
                        break;
                    case "ElementalCrest":
                        rew[i]="EC";
                        break;
                    case "GreenBonus":
                        rew[i]="GB";
                        break;
                    case "MagentaBonus":
                        rew[i]="MB";
                        break;
                    case "RedBonus":
                        rew[i]="RB";
                        break;
                    case "TimeWarp":
                        rew[i]="TW";
                        break;
                    case "YellowBonus":
                        rew[i]="YB";
                        break;
                    default:
                        rew[i]="  ";
                        break;
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < 11; i++){
            RB[i].setText(rew[i]);
            rb[i].setText(rew[i]);
        }

    }


    @FXML
    // Method to change the text of buttons randomly
    public void rollButtons() {
        Random random = new Random();
        rollDice();
        rollButton.setDisable(true);
        // Iterate over your buttons and assign random values to them
        for (int i=0;i<6;i++) {
            getButtons()[i].setText(""+getAllDice()[i].getValue());
            getButtons()[i].getStyleClass().remove("selected-button");
            getButtons()[i].setDisable(false);
        }
        for(Button[] buttons : getPLayer1Buttons()){
            for(Button button : buttons) {
                button.setStyle("");
            }
        }
        for(Button[] buttons : getPLayer2Buttons()){
            for(Button button : buttons) {
                button.setStyle("");
            }
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
        //enablePlayer1();
        for(Button b:getButtons()){
            rollButton.setDisable(false);
        }
        for(Button b : getForgottenRealmButtons()){
            b.setVisible(false);
        }


    }
//    public void gameLoop(){
//        while (getGameStatus().getRound() <= 6) {
//            while(getGameStatus().getPartOfRound()<2) {
//                if(getGameStatus().getRound() == 7)
//                    break;
//                while (getGameStatus().getTurn() <= 3 && thereAreAvailableDice()) {
//
//                    rollButtons();
//                    //timeWarpPrompt();
//                }
//
//                endTurn();
//            }
//        }
//        endGame();
//    }
    public void startTurn(){
        for(Button b : getButtons()){
            b.setDisable(false);
            b.setVisible(true);
        }
        rollDice();
    }


    public void timeWarpPrompt(ActionEvent event){

            gameStat.setText("Available Time Warps: "+getActivePlayer().getTimeWarpCount());


    }
    public void useTimeWarp(ActionEvent event){
        getActivePlayer().subtractTimeWarpCount();
        rollButtons();
    }

    private void helper(){
        getGameStatus().incrementPartOfRound();
        gameStat.setText("Your turn has ended");
    }

    public void addTurn(int i){
        if(getGameStatus().getTurn()<4){
            if (getGameStatus().getPartOfRound() == 0) {
                player1Turn.setText("" + getGameStatus().getTurn());
                player2Turn.setText("--");
            } else {
                player1Turn.setText("--");
                player2Turn.setText("" + getGameStatus().getTurn());
            }
        }
        boolean flag = false;
        for(Button b : getButtons()){
            if(b.isVisible()){
                flag = true;
                break;
            }
        }
        if(getGameStatus().getTurn()==4 || !flag) {
            rollButton.setVisible(false);
            getGameStatus().setAv(false);
            gameStat.setText(getPassivePlayer().getPlayerName() + ": Choose a die from the forgotten realm");
            for (Button button : getButtons()) {
                button.setDisable(true);
            }
            for(int j=0;j<getForgottenRealmButtons().length;j++) {
                getForgottenRealmButtons()[j].setText(""+getForgottenRealmDice()[j].getValue());
                getForgottenRealmButtons()[j].setDisable(false);
            }
        }

        rollButton.setDisable(false);
    }





    @FXML
    private void handleButtonPress(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        for(Button[] buttons : getPLayer1Buttons()){
            for(Button b : buttons) {
                b.setStyle("");
                b.setDisable(true);
            }
        }
        for(Button[] buttons : getPLayer2Buttons()){
            for(Button b : buttons) {
                b.setStyle("");
                b.setDisable(true);
            }
        }
        button.setDisable(false);
        for(Button b : getButtons()){
            if(button.equals(b)) {
                continue;
            }
            button.setDisable(true);
        }
        // Get the button that was pressed
        if (selectedButton != null) {
            selectedButton.setDisable(false); // Re-enable previously selected button
            // Remove the selection class from the previously selected button
            selectedButton.getStyleClass().remove("selected-button");
        }
        // Select the new button and disable it
        selectedButton = button;
        selectedButton.setDisable(true); // Disable the selected button to prevent further clicks
        //int dieValue = Integer.parseInt(selectedButton.getText());
            if (button.equals(die1) || button.equals(forgottenRealm1))
                highlightRedPossibleMoves1();
            else if (button.equals(die2) || button.equals(forgottenRealm2))
                highlightGreenPossibleMoves1();
            else if (button.equals(die3) || button.equals(forgottenRealm3))
                highlightBluePossibleMoves1();
            else if (button.equals(die4) || button.equals(forgottenRealm4))
                highlightMagentaPossibleMoves1();
            else if (button.equals(die5) || button.equals(forgottenRealm5))
                highlightYellowPossibleMoves1();


//        else{
//            if(button.equals(die1))
//                highlightRedPossibleMoves2();
//            else if(button.equals(die2))
//                highlightGreenPossibleMoves2();
//            else if(button.equals(die3))
//                highlightBluePossibleMoves2();
//            else if(button.equals(die4))
//                highlightMagentaPossibleMoves2();
//            else if(button.equals(die5))
//                highlightYellowPossibleMoves2();
//        }
        // Add the selection class to the new button
        selectedButton.getStyleClass().add("selected-button");
    }

    public void highlightRedPossibleMoves1() {
        int i = getAllDice()[0].getValue();
        if ((getGameStatus().getPartOfRound() == 0 && getGameStatus().isAv()) || getGameStatus().getPartOfRound() == 1 && !getGameStatus().isAv()) {

            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), getAllDice()[0]);
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 12; x++) {
                if (i == Integer.parseInt(getPLayer1Buttons()[0][x].getText())) {
                    getPLayer1Buttons()[0][x].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer1Buttons()[0][x].setDisable(false);
                }
            }
        } else if ((getGameStatus().getPartOfRound() == 0 && !getGameStatus().isAv()) || getGameStatus().getPartOfRound() == 1 && getGameStatus().isAv()) {
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), getAllDice()[0]);
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 12; x++) {
                if (i == Integer.parseInt(getPLayer2Buttons()[0][x].getText())) {
                    getPLayer2Buttons()[0][x].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer2Buttons()[0][x].setDisable(false);
                }
            }
        }
    }
    public void highlightGreenPossibleMoves1(){
            int h = Integer.parseInt(die2.getText()) + Integer.parseInt(die6.getText());
            if((getGameStatus().getPartOfRound()==0 && getGameStatus().isAv()) || getGameStatus().getPartOfRound()==1 && !getGameStatus().isAv()) {
                Move[] m = getGameBoard().getPlayer1().getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(h));
                if (m.length == 0) {
                    return;
                }
                for (int i = 0; i < 11; i++) {
                    if (h == i + 2) {
                        getPLayer1Buttons()[1][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                        getPLayer1Buttons()[1][i].setDisable(false);
                    }
                }
            }
            else if((getGameStatus().getPartOfRound()==0 && !getGameStatus().isAv()) || getGameStatus().getPartOfRound()==1 && getGameStatus().isAv()) {
                Move[] m = getGameBoard().getPlayer2().getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(h));
                if (m.length == 0) {
                    return;
                }
                for (int i = 0; i < 11; i++) {
                    if (h == i + 2) {
                        getPLayer2Buttons()[1][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                        getPLayer2Buttons()[1][i].setDisable(false);
                    }
                }
            }
    }
    public void highlightBluePossibleMoves1(){
        int value = Integer.parseInt(die3.getText());
        if((getGameStatus().getPartOfRound()==0 && getGameStatus().isAv()) || getGameStatus().getPartOfRound()==1 && !getGameStatus().isAv()) {
            int i = getGameBoard().getPlayer1().getScoreSheet().getHydra().headsKilled();
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new BlueDice(value));
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 11; x++) {
                if (i == x) {
                    getPLayer1Buttons()[2][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer1Buttons()[2][i].setDisable(false);
                }
            }
        }
        else if((getGameStatus().getPartOfRound()==0 && !getGameStatus().isAv()) || getGameStatus().getPartOfRound()==1 && getGameStatus().isAv()){
                int i = getGameBoard().getPlayer2().getScoreSheet().getHydra().headsKilled();
                Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new BlueDice(value));
                if(m.length==0){
                    return;
                }
                for(int x=0;x<11;x++){
                    if(i == x){
                        getPLayer2Buttons()[2][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                        getPLayer2Buttons()[2][i].setDisable(false);
                    }
                }
        }
    }
    public void highlightMagentaPossibleMoves1(){
        int value = Integer.parseInt(die4.getText());
        if((getGameStatus().getPartOfRound()==0 && getGameStatus().isAv()) || getGameStatus().getPartOfRound()==1 && !getGameStatus().isAv()) {
            int i = getGameBoard().getPlayer1().getScoreSheet().getPhoenix().getCount();
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new MagentaDice(value));
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 11; x++) {
                if (i == x) {
                    getPLayer1Buttons()[3][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer1Buttons()[3][i].setDisable(false);
                }
            }
        }
        else if((getGameStatus().getPartOfRound()==0 && !getGameStatus().isAv()) || getGameStatus().getPartOfRound()==1 && getGameStatus().isAv()) {
            int i = getGameBoard().getPlayer2().getScoreSheet().getPhoenix().getCount();
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new MagentaDice(value));
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 11; x++) {
                if (i == x) {
                    getPLayer2Buttons()[3][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer2Buttons()[3][i].setDisable(false);
                }
            }
        }

        }
    public void highlightYellowPossibleMoves1() {
        int value = Integer.parseInt(die5.getText());
        if ((getGameStatus().getPartOfRound() == 0 && getGameStatus().isAv()) || getGameStatus().getPartOfRound() == 1 && !getGameStatus().isAv()) {

            int i = getGameBoard().getPlayer1().getScoreSheet().getLion().getHitNum();
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new YellowDice(value));
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 11; x++) {
                if (i == x) {
                    getPLayer1Buttons()[4][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer1Buttons()[4][i].setDisable(false);
                }
            }
        } else if ((getGameStatus().getPartOfRound() == 0 && !getGameStatus().isAv()) || getGameStatus().getPartOfRound() == 1 && getGameStatus().isAv()) {
            int i = getGameBoard().getPlayer2().getScoreSheet().getLion().getHitNum();
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new YellowDice(value));
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 11; x++) {
                if (i == x) {
                    getPLayer2Buttons()[4][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer2Buttons()[4][i].setDisable(false);
                }
            }
        }
    }


    public void attackRed1(ActionEvent event) {
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die1.getText());
        RedDice d = new RedDice(i);
        Move[] m = getGameBoard().getPlayer1().getScoreSheet().getDragon().getPossibleMovesForADie(d);
        boolean b;
        if(m.length==0){
            return;
        }
        for(int x=0;x<12;x++){
            if(i == Integer.parseInt(getPLayer1Buttons()[0][x].getText()) && button.equals(getPLayer1Buttons()[0][x]) ){
                String s = button.getId();
                if(s.charAt(1) == '1'){
                    d.selectsDragon(1);
                }
                else if (s.charAt(1) == '2'){
                    d.selectsDragon(2);
                }
                else if (s.charAt(1) == '3'){
                    d.selectsDragon(3);
                }
                else if (s.charAt(1) == '4'){
                    d.selectsDragon(4);
                }
                if(makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()))){
                    getPLayer1Buttons()[0][x].setText("X");
                    getPLayer1Buttons()[0][x].setDisable(true);
                    disablePlayer1();
                    break;
                }
            }
        }
        die1.setDisable(true);
        for (int h=0;h<6;h++) {
            if(getButtons()[h].isVisible() && Integer.parseInt(getButtons()[h].getText()) < i && !getForgottenRealmButtons()[h].isVisible()) {
                getForgottenRealmButtons()[h].setText(getButtons()[h].getText());
                getForgottenRealmButtons()[h].setVisible(true);
                getButtons()[h].setVisible(false);
            }
            getButtons()[h].setDisable(true);
        }
        die1.setVisible(false);
        if(getGameStatus().getPartOfRound()==1){
            getGameStatus().incrementRound();
            getGameStatus().incrementPartOfRound();
            getGameStatus().resetTurn();
            for(Button hds:getForgottenRealmButtons()){
                hds.setVisible(false);
            }
            for(Button hgf:getButtons()){
                hgf.setVisible(true);
            }
            rollButton.setVisible(true);
            switchPlayer();
            getGameStatus().setAv(true);
            return;

        }
            addTurn(1);

        }
    public void attackGreen1(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die2.getText())+Integer.parseInt(die6.getText());
        Dice d = new GreenDice(i);
        Dice h = getAllDice()[1];
        Move[] m = getGameBoard().getPlayer1().getScoreSheet().getGaia().getPossibleMovesForADie(d);
        if(m.length>0){
            for(int x=0;x<11;x++){
                if(button.equals(getPLayer1Buttons()[1][x])){
                    if(makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))) {
                        getPLayer1Buttons()[1][x].setText("X");
                        getPLayer1Buttons()[1][x].setDisable(true);
                    }
                }
            }
        }
        die2.setDisable(true);
        for (int r=0;r<6;r++) {
            if(getButtons()[r].isVisible() && (Integer.parseInt(getButtons()[r].getText()) < Integer.parseInt(die2.getText())) && !getForgottenRealmButtons()[r].isVisible()) {
                getForgottenRealmButtons()[r].setText(getButtons()[r].getText());
                getForgottenRealmButtons()[r].setVisible(true);
                getButtons()[r].setVisible(false);
            }
            getButtons()[r].setDisable(true);
        }
        die2.setVisible(false);
        if(getGameStatus().getPartOfRound()==1){
            getGameStatus().incrementRound();
            getGameStatus().incrementPartOfRound();
            getGameStatus().resetTurn();
            for(Button hds:getForgottenRealmButtons()){
                hds.setVisible(false);
            }
            for(Button hgf:getButtons()){
                hgf.setVisible(true);
            }
            rollButton.setVisible(true);
            switchPlayer();
            getGameStatus().setAv(true);
            return;

        }
        addTurn(1);
    }
    public void attackBlue1(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die3.getText());
        Dice d = new BlueDice(i);
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new BlueDice(i));
        if(m.length>0) {
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer1Buttons()[2][x])) {
                    if (makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getHydra()))) {
                        getPLayer1Buttons()[2][x].setText("X");
                        getPLayer1Buttons()[2][x].setDisable(true);
                    }
                }
            }

            for (int h = 0; h < 6; h++) {
                if (getButtons()[h].isVisible() && Integer.parseInt(getButtons()[h].getText()) < i && !getForgottenRealmButtons()[h].isVisible()) {
                    getForgottenRealmButtons()[h].setText(Integer.parseInt(getButtons()[h].getText()) + "");
                    getForgottenRealmButtons()[h].setVisible(true);
                    getButtons()[h].setVisible(false);
                }
                getButtons()[h].setDisable(true);
            }
            die3.setVisible(false);
            if(getGameStatus().getPartOfRound()==1){
                getGameStatus().incrementRound();
                getGameStatus().incrementPartOfRound();
                getGameStatus().resetTurn();
                for(Button hds:getForgottenRealmButtons()){
                    hds.setVisible(false);
                }
                for(Button hgf:getButtons()){
                    hgf.setVisible(true);
                }
                rollButton.setVisible(true);
                switchPlayer();
                getGameStatus().setAv(true);

                return;

            }
            addTurn(1);      }
    }
    public void attackMagenta1(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die4.getText());
        Dice d = new MagentaDice(i);
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new MagentaDice(i));
        if(m.length==0){
            return;
        }
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer1Buttons()[3][x])) {
                    if (makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getPhoenix()))) {
                        getPLayer1Buttons()[3][x].setText(""+i);
                        getPLayer1Buttons()[3][x].setDisable(true);
                    }
                }
            }
            for (int h = 0; h < 6; h++) {
                if (getButtons()[h].isVisible() && Integer.parseInt(getButtons()[h].getText()) < i && !getForgottenRealmButtons()[h].isVisible()) {
                    getForgottenRealmButtons()[h].setText(getButtons()[h].getText());
                    getForgottenRealmButtons()[h].setVisible(true);
                    getButtons()[h].setVisible(false);
                }
                getButtons()[h].setDisable(true);
            }
            die4.setVisible(false);
        if(getGameStatus().getPartOfRound()==1){
            getGameStatus().incrementRound();
            getGameStatus().incrementPartOfRound();
            getGameStatus().resetTurn();
            for(Button hds:getForgottenRealmButtons()){
                hds.setVisible(false);
            }
            for(Button hgf:getButtons()){
                hgf.setVisible(true);
            }
            rollButton.setVisible(true);
            switchPlayer();
            getGameStatus().setAv(true);

            return;

        }
        addTurn(1);  }
    public void attackYellow1(ActionEvent event) {
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die5.getText());
        Dice d = new YellowDice(i);
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new YellowDice(i));
        if (m.length > 0) {
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer1Buttons()[4][x])) {
                    if (makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getLion()))) {
                        getPLayer1Buttons()[4][x].setText("X");
                        getPLayer1Buttons()[4][x].setDisable(true);
                    }
                }
            }
            forgottenRealm5.setText(die5.getText());
            for (int h = 0; h < 6; h++) {
                if (getButtons()[h].isVisible() && Integer.parseInt(getButtons()[h].getText()) < i && !getForgottenRealmButtons()[h].isVisible()) {
                    getForgottenRealmButtons()[h].setText(getButtons()[h].getText());
                    getForgottenRealmButtons()[h].setVisible(true);
                    getButtons()[h].setVisible(false);
                }
                getButtons()[h].setDisable(true);
            }
            die5.setVisible(false);
            if(getGameStatus().getPartOfRound()==1){
                getGameStatus().incrementRound();
                getGameStatus().incrementPartOfRound();
                getGameStatus().resetTurn();
                for(Button hds:getForgottenRealmButtons()){
                    hds.setVisible(false);
                }
                for(Button hgf:getButtons()){
                    hgf.setVisible(true);
                }
                rollButton.setVisible(true);
                switchPlayer();
                getGameStatus().setAv(true);

                return;

            }
            addTurn(1);       }
    }
    //    public void highlightRedPossibleMoves2(){
//        int i = getAllDice()[0].getValue();
//        Move[] moves = getPossibleMovesForADie(getActivePlayer(), getAllDice()[0]);
//        if(moves.length>0){
//            if(f1.getText().equals(""+i)){
//                f1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(f2.getText().equals(""+i)){
//                f2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(f3.getText().equals(""+i)){
//                f3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(w1.getText().equals(""+i)){
//                w1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(w2.getText().equals(""+i)){
//                w2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(w4.getText().equals(""+i)){
//                w4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(t1.getText().equals(""+i)){
//                t1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(t3.getText().equals(""+i)){
//                t3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(t4.getText().equals(""+i)){
//                t4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(h2.getText().equals(""+i)){
//                h2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(h3.getText().equals(""+i)){
//                h3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//            if(h4.getText().equals(""+i)){
//                h4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//            }
//        }
//    }
//    public void highlightGreenPossibleMoves2(){
//        int h = Integer.parseInt(die2.getText()) + Integer.parseInt(die6.getText());
//        Move[] m = getActivePlayer().getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(h));
//        if(m.length==0){
//            return;
//        }
//        switch(h){
//            case 2:
//                g2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 3:
//                g3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 4:
//                g4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 5:
//                g5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 6:
//                g6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 7:
//                g7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 8:
//                g8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 9:
//                g9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 10:
//                g10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 11:
//                g11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//            case 12:
//                g12.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                break;
//        }
//    }
//    public void highlightBluePossibleMoves2(){
//        int i = getActivePlayer().getScoreSheet().getHydra().headsKilled();
//        int value = Integer.parseInt(die3.getText());
//        Move[] m = getPossibleMovesForADie(getActivePlayer(), new BlueDice(value));
//        if(m.length>0 ){
//            switch(i){
//                case 0:
//                    b1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 1:
//                    b2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 2:
//                    b3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 3:
//                    b4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 4:
//                    b5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 5:
//                    b6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 6:
//                    b7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 7:
//                    b8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 8:
//                    b9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 9:
//                    b10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 10:
//                    b11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//            }
//        }
//    }
//    public void highlightMagentaPossibleMoves2(){
//        int i = getActivePlayer().getScoreSheet().getPhoenix().getCount();
//        int value = Integer.parseInt(die4.getText());
//        Move[] m = getPossibleMovesForADie(getActivePlayer(), new MagentaDice(value));
//        if(m.length>0 ){
//            switch(i){
//                case 0:
//                    m1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 1:
//                    m2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 2:
//                    m3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 3:
//                    m4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 4:
//                    m5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 5:
//                    m6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 6:
//                    m7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 7:
//                    m8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 8:
//                    m9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 9:
//                    m10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 10:
//                    m11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//            }
//        }
//    }
//    public void highlightYellowPossibleMoves2(){
//        int i = getActivePlayer().getScoreSheet().getLion().getHitNum();
//        int value = Integer.parseInt(die5.getText());
//        Move[] m = getPossibleMovesForADie(getActivePlayer(), new YellowDice(value));
//        if(m.length>0 ){
//            switch(i){
//                case 0:
//                    y1.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 1:
//                    y2.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 2:
//                    y3.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 3:
//                    y4.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 4:
//                    y5.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 5:
//                    y6.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 6:
//                    y7.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 7:
//                    y8.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 8:
//                    y9.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 9:
//                    y10.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//                case 10:
//                    y11.setStyle("-fx-border-color: black; -fx-border-width: 3;");
//                    break;
//            }
//        }
//    }
//    public void attackRed2(ActionEvent event) {
//        Button button = (Button) event.getSource();
//        int i = Integer.parseInt(die1.getText());
//        RedDice d = new RedDice(i);
//        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), d);
//        boolean b = false;
//        if (m.length > 0) {
//            if (button.equals(f1)) {
//                d.selectsDragon(1);
//                b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                    f1.setText("X");
//                } else if (button.equals(f2)) {
//                    d.selectsDragon(2);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        f2.setText("X");
//                } else if (button.equals(f3)) {
//                    d.selectsDragon(3);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        f3.setText("X");
//                } else if (button.equals(w1)) {
//                    d.selectsDragon(1);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        w1.setText("X");
//                } else if (button.equals(w2)) {
//                    d.selectsDragon(2);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        w2.setText("X");
//                } else if (button.equals(w4)) {
//                    d.selectsDragon(4);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        w4.setText("X");
//                } else if (button.equals(t1)) {
//                    d.selectsDragon(1);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        t1.setText("X");
//                } else if (button.equals(t3)) {
//                    d.selectsDragon(3);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        t3.setText("X");
//                } else if (button.equals(t4)) {
//                    d.selectsDragon(4);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        t4.setText("X");
//                } else if (button.equals(h2)) {
//                    d.selectsDragon(2);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        h2.setText("X");
//                } else if (button.equals(h3)) {
//                    d.selectsDragon(3);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        h3.setText("X");
//                } else if (button.equals(h4)) {
//                    d.selectsDragon(4);
//                    b = makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()));
//                        h4.setText("X");
//                }
//            }
//        }
//    public void attackGreen2(ActionEvent event){
//        Button button = (Button) event.getSource();
//        int i = Integer.parseInt(die2.getText())+Integer.parseInt(die6.getText());
//        Dice d = new GreenDice(i);
//        Dice h = getAllDice()[1];
//        Move[] m = getGameBoard().getPlayer2().getScoreSheet().getGaia().getPossibleMovesForADie(d);
//        if(m.length>0){
//            if(button.equals(g2)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g2.setText("X");
//                }
//            }
//            else if(button.equals(g3)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia())))
//                {
//                    g3.setText("X");
//                }
//            }
//            else if(button.equals(g4)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g4.setText("X");
//                }
//            }
//            else if(button.equals(g5)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g5.setText("X");
//                }
//            }
//            else if(button.equals(g6)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g6.setText("X");
//                }
//            }
//            else if(button.equals(g7)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g7.setText("X");
//                }
//            }
//            else if(button.equals(g8)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g8.setText("X");
//                }
//            }
//            else if(button.equals(g9)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g9.setText("X");
//                }
//            }
//            else if(button.equals(g10)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g10.setText("X");
//                }
//            }
//            else if(button.equals(g11)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g11.setText("X");
//                }
//            }
//            else if(button.equals(g12)){
//                if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
//                    g12.setText("X");
//                }
//            }
//        }
//    }
//    public void attackBlue2(ActionEvent event){
//        Button button = (Button) event.getSource();
//        int i = Integer.parseInt(die3.getText());
//        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new BlueDice(i));
//        if(m.length>0){
//            if(button.equals(b1)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b1.setText("X");
//            }
//            else if(button.equals(b2)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b2.setText("X");
//            }
//            else if(button.equals(b3)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b3.setText("X");
//            }
//            else if(button.equals(b4)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b4.setText("X");
//            }
//            else if(button.equals(b5)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b5.setText("X");
//            }
//            else if(button.equals(b6)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b6.setText("X");
//            }
//            else if(button.equals(b7)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b7.setText("X");
//            }
//            else if(button.equals(b8)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b8.setText("X");
//            }
//            else if(button.equals(b9)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b9.setText("X");
//            }
//            else if(button.equals(b10)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b10.setText("X");
//            }
//            else if(button.equals(b11)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new BlueDice(i),getGameBoard().getPlayer2().getScoreSheet().getHydra()));
//                b11.setText("X");
//            }
//        }
//    }
//    public void attackMagenta2(ActionEvent event){
//        Button button = (Button) event.getSource();
//        int i = Integer.parseInt(die4.getText());
//        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new MagentaDice(i));
//        if(m.length>0){
//            if(button.equals(m1)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m1.setText(""+i);
//            }
//            else if(button.equals(m2)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m2.setText(""+i);
//            }
//            else if(button.equals(m3)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m3.setText(""+i);
//            }
//            else if(button.equals(m4)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m4.setText(""+i);
//            }
//            else if(button.equals(m5)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m5.setText(""+i);
//            }
//            else if(button.equals(m6)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m6.setText(""+i);
//            }
//            else if(button.equals(m7)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m7.setText(""+i);
//            }
//            else if(button.equals(m8)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m8.setText(""+i);
//            }
//            else if(button.equals(m9)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m9.setText(""+i);
//            }
//            else if(button.equals(m10)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m10.setText(""+i);
//            }
//            else if(button.equals(m11)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new MagentaDice(i),getGameBoard().getPlayer2().getScoreSheet().getPhoenix()));
//                m11.setText(""+i);
//            }
//        }
//    }
//    public void attackYellow2(ActionEvent event){
//        Button button = (Button) event.getSource();
//        int i = Integer.parseInt(die5.getText());
//        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new YellowDice(i));
//        if(m.length>0){
//            if(button.equals(y1)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y1.setText(""+i);
//            }
//            else if(button.equals(y2)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y2.setText(""+i);
//            }
//            else if(button.equals(y3)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y3.setText(""+i);
//            }
//            else if(button.equals(y4)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y4.setText(""+i*2);
//            }
//            else if(button.equals(y5)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y5.setText(""+i);
//            }
//            else if(button.equals(y6)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y6.setText(""+i);
//            }
//            else if(button.equals(y7)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y7.setText(""+i*2);
//            }
//            else if(button.equals(y8)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y8.setText(""+i);
//            }
//            else if(button.equals(y9)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y9.setText(""+i*2);
//            }
//            else if(button.equals(y10)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y10.setText(""+i);
//            }
//            else if(button.equals(y11)){
//                makeMove(getGameBoard().getPlayer2(), new Move(new YellowDice(i),getGameBoard().getPlayer2().getScoreSheet().getLion()));
//                y11.setText(""+i*3);
//            }
//        }
//    }

    public void attackRed2(ActionEvent event) {
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die1.getText());
        RedDice d = new RedDice(i);
        Move[] m = getGameBoard().getPlayer2().getScoreSheet().getDragon().getPossibleMovesForADie(d);
        boolean b;
        if(m.length==0){
            return;
        }
        for(int x=0;x<12;x++){
            if(i == Integer.parseInt(getPLayer2Buttons()[0][x].getText()) && button.equals(getPLayer2Buttons()[0][x]) ){
                String s = button.getId();
                if(s.charAt(1) == '1'){
                    d.selectsDragon(1);
                }
                else if (s.charAt(1) == '2'){
                    d.selectsDragon(2);
                }
                else if (s.charAt(1) == '3'){
                    d.selectsDragon(3);
                }
                else if (s.charAt(1) == '4'){
                    d.selectsDragon(4);
                }
                if(makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()))){
                    getPLayer2Buttons()[0][x].setText("X");
                    getPLayer2Buttons()[0][x].setDisable(true);
                    disablePlayer2();
                    break;
                }
            }
        }
        die1.setDisable(true);
        for (int h=0;h<6;h++) {
            if(getButtons()[h].isVisible() && Integer.parseInt(getButtons()[h].getText()) < i && !getForgottenRealmButtons()[h].isVisible()) {
                getForgottenRealmButtons()[h].setText(getButtons()[h].getText());
                getForgottenRealmButtons()[h].setVisible(true);
                getButtons()[h].setVisible(false);
            }
            getButtons()[h].setDisable(true);
        }
        die1.setVisible(false);
        if(getGameStatus().getPartOfRound()==0){
            getGameStatus().incrementPartOfRound();
            getGameStatus().resetTurn();

            for(Button hds:getForgottenRealmButtons()){
                hds.setVisible(false);
            }
            for(Button hgf:getButtons()){
                hgf.setVisible(true);
            }
            rollButton.setVisible(true);
            switchPlayer();
            getGameStatus().setAv(true);
            return;

        }
        addTurn(1);  }
    public void attackGreen2(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die2.getText())+Integer.parseInt(die6.getText());
        Dice d = new GreenDice(i);
        Dice h = getAllDice()[1];
        Move[] m = getGameBoard().getPlayer2().getScoreSheet().getGaia().getPossibleMovesForADie(d);
        if(m.length>0){
            for(int x=0;x<11;x++){
                if(button.equals(getPLayer2Buttons()[1][x])){
                    if(makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))) {
                        getPLayer2Buttons()[1][x].setText("X");
                        getPLayer2Buttons()[1][x].setDisable(true);
                    }
                }
            }
        }
        die2.setDisable(true);
        for (int r=0;r<6;r++) {
            if(getButtons()[r].isVisible() && (Integer.parseInt(getButtons()[r].getText()) < Integer.parseInt(die2.getText())) && !getForgottenRealmButtons()[r].isVisible()) {
                getForgottenRealmButtons()[r].setText(getButtons()[r].getText());
                getForgottenRealmButtons()[r].setVisible(true);
                getButtons()[r].setVisible(false);
            }
            getButtons()[r].setDisable(true);
        }
        die2.setVisible(false);
        if(getGameStatus().getPartOfRound()==0){
            getGameStatus().incrementPartOfRound();
            getGameStatus().resetTurn();

            for(Button hds:getForgottenRealmButtons()){
                hds.setVisible(false);
            }
            for(Button hgf:getButtons()){
                hgf.setVisible(true);
            }
            rollButton.setVisible(true);
            switchPlayer();
            getGameStatus().setAv(true);
            return;

        }
        addTurn(1);  }
    public void attackBlue2(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die3.getText());
        Dice d = new BlueDice(i);
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new BlueDice(i));
        if(m.length>0) {
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer2Buttons()[2][x])) {
                    if (makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getHydra()))) {
                        getPLayer2Buttons()[2][x].setText("X");
                        getPLayer2Buttons()[2][x].setDisable(true);
                    }
                }
            }

            for (int h = 0; h < 6; h++) {
                if (getButtons()[h].isVisible() && Integer.parseInt(getButtons()[h].getText()) < i && !getForgottenRealmButtons()[h].isVisible()) {
                    getForgottenRealmButtons()[h].setText(Integer.parseInt(getButtons()[h].getText()) + "");
                    getForgottenRealmButtons()[h].setVisible(true);
                    getButtons()[h].setVisible(false);
                }
                getButtons()[h].setDisable(true);
            }
            die3.setVisible(false);
            if(getGameStatus().getPartOfRound()==0){
                getGameStatus().incrementPartOfRound();
                getGameStatus().resetTurn();

                for(Button hds:getForgottenRealmButtons()){
                    hds.setVisible(false);
                }
                for(Button hgf:getButtons()){
                    hgf.setVisible(true);
                }
                rollButton.setVisible(true);
                switchPlayer();
                getGameStatus().setAv(true);

                return;

            }
            addTurn(1);   }
    }
    public void attackMagenta2(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die4.getText());
        Dice d = new MagentaDice(i);
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new MagentaDice(i));
        if(m.length==0){
            return;
        }
        for (int x = 0; x < 11; x++) {
            if (button.equals(getPLayer2Buttons()[3][x])) {
                if (makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getPhoenix()))) {
                    getPLayer2Buttons()[3][x].setText(""+i);
                    getPLayer2Buttons()[3][x].setDisable(true);
                }
            }
        }
        for (int h = 0; h < 6; h++) {
            if (getButtons()[h].isVisible() && Integer.parseInt(getButtons()[h].getText()) < i && !getForgottenRealmButtons()[h].isVisible()) {
                getForgottenRealmButtons()[h].setText(getButtons()[h].getText());
                getForgottenRealmButtons()[h].setVisible(true);
                getButtons()[h].setVisible(false);
            }
            getButtons()[h].setDisable(true);
        }
        die4.setVisible(false);
        if(getGameStatus().getPartOfRound()==0){
            getGameStatus().incrementPartOfRound();
            getGameStatus().resetTurn();

            for(Button hds:getForgottenRealmButtons()){
                hds.setVisible(false);
            }
            for(Button hgf:getButtons()){
                hgf.setVisible(true);
            }
            rollButton.setVisible(true);
            switchPlayer();
            getGameStatus().setAv(true);
            return;

        }
        addTurn(1);  }
    public void attackYellow2(ActionEvent event) {
        Button button = (Button) event.getSource();
        int i = Integer.parseInt(die5.getText());
        Dice d = new YellowDice(i);
        Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new YellowDice(i));
        if (m.length > 0) {
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer2Buttons()[4][x])) {
                    if (makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getLion()))) {
                        getPLayer2Buttons()[4][x].setText(""+i);
                        getPLayer2Buttons()[4][x].setDisable(true);
                    }
                }
            }
            forgottenRealm5.setText(die5.getText());
            for (int h = 0; h < 6; h++) {
                if (getButtons()[h].isVisible() && Integer.parseInt(getButtons()[h].getText()) < i && !getForgottenRealmButtons()[h].isVisible()) {
                    getForgottenRealmButtons()[h].setText(getButtons()[h].getText());
                    getForgottenRealmButtons()[h].setVisible(true);
                    getButtons()[h].setVisible(false);
                }
                getButtons()[h].setDisable(true);
            }
            die5.setVisible(false);
            if(getGameStatus().getPartOfRound()==0){
                getGameStatus().incrementPartOfRound();
                getGameStatus().resetTurn();
                for(Button hds:getForgottenRealmButtons()){
                    hds.setVisible(false);
                }
                for(Button hgf:getButtons()){
                    hgf.setVisible(true);
                }
                rollButton.setVisible(true);
                switchPlayer();
                getGameStatus().setAv(true);

                return;

            }
            addTurn(1);       }
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





    

   






