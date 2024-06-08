package game.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import game.collectibles.*;
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
    private GameBoard gameBoard;
    public controller() {
        gameBoard = new GameBoard();
    }

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
    private Button RG1, RG2, RG3, RG4, RG5, RG6, RG7, rg1, rg2, rg3, rg4, rg5, rg6, rg7;
    @FXML
    private Label SG1,SG2,SG3,SG4,SG5,sg1,sg2,sg3,sg4,sg5;
    @FXML
    private Button Blue1, Blue2, Blue3, Blue4, Blue5, Blue6, Blue7, Blue8, Blue9, Blue10, Blue11,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
    @FXML
    private Button Magenta1, Magenta2, Magenta3, Magenta4, Magenta5, Magenta6, Magenta7, Magenta8, Magenta9, Magenta10, Magenta11,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11;
    @FXML
    private Button RM1,RM2, RM3, RM4, RM5, RM6, RM7, RM8, RM9, RM10, RM11, rm1, rm2, rm3, rm4, rm5, rm6, rm7, rm8, rm9, rm10, rm11;
    @FXML
    private Button Yellow1, Yellow2, Yellow3, Yellow4, Yellow5, Yellow6, Yellow7, Yellow8, Yellow9, Yellow10, Yellow11,y1,y2,y3,y4,y5,y6,y7,y8,y9,y10,y11;
    @FXML
    private Button RY1, RY2, RY3, RY4, RY5, RY6, RY7, RY8, RY9, RY10, RY11, ry1, ry2, ry3, ry4, ry5, ry6, ry7, ry8, ry9, ry10, ry11;
    @FXML
    private Button startGameButton, timeWarpButton, rollButton, die1, die2, die3, die4, die5, die6, selectedButton = null, forgottenRealm1, forgottenRealm2, forgottenRealm3, forgottenRealm4, forgottenRealm5, forgottenRealm6;
    @FXML
    private Label round1, round2, round3, round4, round, player1Turn, player2Turn, gameStat, player1Label, player2Label, tw1, tw2;

    @FXML
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;

    private Label score1,score2;



    private int [] scoreRed = new int[4];
    private String [] reward = new String[5];
    private int [][] dragonParts = new int[4][4];

    private  Image[][] diceImages = new Image[6][6]; // 6 types, 6 values each
    private  ImageView[][] diceViews = new ImageView[6][6]; // Assuming 6 dice views

    private String[] horizontalRewards = new String[3];
    private String[] verticalRewards = new String[4];
    private int[] greenScores = new int[11];
    private String s;

    String[] yellowRewards = new String[11];
    int[] yellowMultipliers = new int[11];

    public void setPlayerNames(String player1, String player2) {
        player1Label.setText("Player 1: " + player1);
        player2Label.setText("Player 2: " + player2);
        gameBoard.setPlayer1(new HumanPlayer(player1, PlayerStatus.ACTIVE));
        gameBoard.setPlayer2(new HumanPlayer(player2, PlayerStatus.PASSIVE));
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

        try{
            Properties prop3 = new Properties();
            prop3.load(new FileInputStream("src/main/resources/config/TerrasHeartlandRewards.properties"));
            horizontalRewards[0] = prop3.getProperty("row1Reward");
            horizontalRewards[1] = prop3.getProperty("row2Reward");
            horizontalRewards[2] = prop3.getProperty("row3Reward");
            verticalRewards[0] = prop3.getProperty("column1Reward");
            verticalRewards[1] = prop3.getProperty("column2Reward");
            verticalRewards[2] = prop3.getProperty("column3Reward");
            verticalRewards[3] = prop3.getProperty("column4Reward");
            //Load Green scores
            prop3.load(new FileInputStream("src/main/resources/config/TerrasHeartland.properties"));
            //String scrs = prop2.getProperty("score");
            String[] scoreString = prop3.getProperty("score").split(", ");
            for(int i = 0; i < greenScores.length; i++){
                greenScores[i] = Integer.parseInt(scoreString[i]);
            }
        }
        catch (IOException e){
            e.printStackTrace();

        }
        Button[] rewardsGreen = {RG1, RG2, RG3, RG4};
        Button[] rewardsGreen1 = {rg1, rg2, rg3, rg4};
        String s;
        for (int i = 0; i < 4; i++) {
            s = verticalRewards[i];
            switch (s){
                case "RedBonus":
                    s = "RB";
                    break;
                case "TimeWarp":
                    s = "TW";
                    break;
                case "MagentaBonus":
                    s = "MB";
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
            rewardsGreen[i].setText(s);
            rewardsGreen1[i].setText(s);
        }
        Button[] rewardsGreen2 = {RG5, RG6, RG7};
        Button[] rewardsGreen3 = {rg5, rg6, rg7};
        for (int i = 0; i < 3; i++) {
            s = horizontalRewards[i];
            switch (s){
                case "RedBonus":
                    s = "RB";
                    break;
                case "TimeWarp":
                    s = "TW";
                    break;
                case "MagentaBonus":
                    s = "MB";
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
            rewardsGreen2[i].setText(s);
            rewardsGreen3[i].setText(s);
        }
        SG1.setText(greenScores[0] + "," + greenScores[1] + "," + greenScores[2]);
        sg1.setText(greenScores[0] + "," + greenScores[1] + "," + greenScores[2]);
        SG2.setText(greenScores[3] + "," + greenScores[4]);
        sg2.setText(greenScores[3] + "," + greenScores[4]);
        SG3.setText(greenScores[5] + "," + greenScores[6]);
        sg3.setText(greenScores[5] + "," + greenScores[6]);
        SG4.setText(greenScores[7] + "," + greenScores[8]);
        sg4.setText(greenScores[7] + "," + greenScores[8]);
        SG5.setText(greenScores[9] + "," + greenScores[10]);
        sg5.setText(greenScores[9] + "," + greenScores[10]);

        try{
            Properties prop4 = new Properties();
            FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/MysticalSkyRewards.properties");
            prop4.load(rewardConfig);
            for (int i=0;i<11;i++){
                String r=prop4.getProperty("hit"+(i+1)+"Reward");
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
        Button [] RM = {RM1, RM2, RM3, RM4, RM5, RM6, RM7, RM8, RM9, RM10, RM11};
        Button [] rm = {rm1, rm2, rm3, rm4, rm5, rm6, rm7, rm8, rm9, rm10, rm11};
        for (int i = 0; i < 11; i++){
            RM[i].setText(rew[i]);
            rm[i].setText(rew[i]);
        }

        try{
            Properties prop5 = new Properties();
            prop5.load(new FileInputStream("src/main/resources/config/RadiantSvannaRewards.properties"));
            for(int i = 0; i < 11; i++) {
                yellowRewards[i] = prop5.getProperty("hit" + (i + 1) + "Reward");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Button[] RY = {RY1, RY2, RY3, RY4, RY5, RY6, RY7, RY8, RY9, RY10, RY11};
        Button[] ry = {ry1, ry2, ry3, ry4, ry5, ry6, ry7, ry8, ry9, ry10, ry11};
        String strYellow = "";
        for(int i = 0; i < 11; i++){

                switch(yellowRewards[i]){
                    case "TimeWarp":
                        strYellow = "TW";
                        break;
                    case "RedBonus":
                        strYellow = "RB";
                        break;
                    case "ArcaneBoost":
                        strYellow = "AB";
                        break;
                    case "ElementalCrest":
                        strYellow = "EC";
                        break;
                    case "MagentaBonus":
                        strYellow = "MB";
                        break;
                    case "BlueBonus":
                        strYellow = "YB";
                        break;
                    case "GreenBonus":
                        strYellow = "GB";
                        break;
                    default:
                        strYellow = "";

                }

            RY[i].setText(strYellow);
            ry[i].setText(strYellow);
        }

        try{
            Properties prop6 = new Properties();
            prop6.load(new FileInputStream("src/main/resources/config/RadiantSvannaMultipliers.properties"));
            for(int i = 0; i < 11; i++) {
                yellowMultipliers[i] = Integer.parseInt(prop6.getProperty("hit" + (i + 1) + "multiplier"));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Button[] yellowButtons3 = {Yellow1,Yellow2,Yellow3,Yellow4,Yellow5,Yellow6,Yellow7,Yellow8,Yellow9,Yellow10,Yellow11};
        Button[] yellowButtons4 = {y1, y2, y3, y4, y5, y6, y7, y8, y9, y10, y11};
        for(int i = 0; i < yellowButtons3.length; i++){
            if(yellowMultipliers[i] == 1){
                yellowButtons3[i].setText("");
                yellowButtons4[i].setText("");
                continue;
            }
            else {
                yellowButtons3[i].setText("x" + yellowMultipliers[i]);
                yellowButtons4[i].setText("x" + yellowMultipliers[i]);
            }
        }
        die1.setDisable(true);
        die2.setDisable(true);
        die3.setDisable(true);
        die4.setDisable(true);
        die5.setDisable(true);
        die6.setDisable(true);

    }

    @FXML
    // Method to change the text of buttons randomly
    public void rollButtons() {
        Random random = new Random();
        rollDice();
        rollButton.setDisable(true);
        int [] value = new int [6];
        // Iterate over your buttons and assign random values to them
        for (int i=0;i<6;i++) {
            getButtons()[i].setText(""+getAllDice()[i].getValue());
            getButtons()[i].getStyleClass().remove("selected-button");
            getButtons()[i].setDisable(false);
            value[i] = getAllDice()[i].getValue();
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
        imageView1.setImage(diceImages[0][value[0]-1]);
        imageView2.setImage(diceImages[1][value[1]-1]);
        imageView3.setImage(diceImages[2][value[2]-1]);
        imageView4.setImage(diceImages[3][value[3]-1]);
        imageView5.setImage(diceImages[4][value[4]-1]);
        imageView6.setImage(diceImages[5][value[5]-1]);
        selectedButton = null;
    }



    // Helper method to get all buttons
    private Button[] getButtons() {
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
    private void disablePlayer1(){
        for(int i=0;i<getPLayer1Buttons().length;i++) {
            for(int j=0;j<getPLayer1Buttons()[i].length;j++) {
                getPLayer1Buttons()[i][j].setDisable(true); getPLayer1Buttons()[i][j].setStyle("");
            }
        }
    }
    private void disablePlayer2(){
        for(int i=0;i<getPLayer2Buttons().length;i++) {
            for(int j=0;j<getPLayer2Buttons()[i].length;j++) {
                getPLayer2Buttons()[i][j].setDisable(true); getPLayer2Buttons()[i][j].setStyle("");
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
    private void disableButtons(){
        for(Button b : getButtons()){
            b.setDisable(true);
        }
    }
    private void hideButtons(){
        for(Button b : getButtons()){
            b.setVisible(false);
        }
    }
    private void hideFRButtons(){
        for(Button b : getForgottenRealmButtons()){
            b.setVisible(false);
        }
    }
    private void showFRButtons(){
        for(Button b : getForgottenRealmButtons()){
            b.setVisible(true);
            b.setDisable(false);
        }
    }
    private void showButtons(){
        for(Button b : getButtons()){
            b.setVisible(true);
        }
    }
    private boolean noButtons(){
        for(Button b : getButtons()){
            if(b.isVisible())
                return false;
        }
        return true;
    }
    @FXML
    public void startGame(ActionEvent event){
        gameStat.setText("Welcome to Dice Realms!!!\nPlayer 1's turn");
        player1Turn.setText("" + getGameStatus().getTurn());
        startGameButton.setVisible(false);
        rollButton.setDisable(false);
        hideFRButtons();
        tw1.setText("TW: 1");


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

    @FXML
    public void timeWarpPrompt(ActionEvent event){

            gameStat.setText("Available Time Warps: "+getActivePlayer().getTimeWarpCount());


    }
    @FXML
    public void useTimeWarp(ActionEvent event){
        getActivePlayer().subtractTimeWarpCount();
        rollButtons();
    }




    private void fRealm(){
        gameStat.setText(getPassivePlayer().getPlayerName() + ": Choose a die from the forgotten realm");
        showFRButtons();
        player1Turn.setText("--");
        player2Turn.setText("--");
        for(Dice d : getForgottenRealmDice()){
            Realm r = d.getRealm();
            int i = r==Realm.RED?0:r==Realm.GREEN?1:r==Realm.BLUE?2:r==Realm.MAGENTA?3:r==Realm.YELLOW?4:5;
            getForgottenRealmButtons()[i].setText(""+d.getValue());
        }
        for(Button b : getForgottenRealmButtons()){
            if(b.getText().equals("0")){
                b.setVisible(false);
            }
        }
    }
    private void checkStatus(){
        if (getGameStatus().getTurn() == 4 || noButtons()) {
            rollButton.setVisible(false);
            hideButtons();
            fRealm();
        }
        else if(getGameStatus().getPartOfRound() == 2){
            getGameStatus().incrementRound();
            getGameStatus().resetPartofRound();
            round.setText("Round: "+getGameStatus().getRound());
        }
        else if(getGameStatus().getPartOfRound() == 7){
            gameStat.setText("Game is over");

        }
        else{
            setPlayerTurns();
        }
    }

    private void setPlayerTurns(){
        if(getGameStatus().getPartOfRound()==0){

            player1Turn.setText(getGameStatus().getTurn()+"");
            player2Turn.setText("--");
        }
        else{
            player2Turn.setText(getGameStatus().getTurn()+"");
            player1Turn.setText("--");
        }
    }

    private void afterAttack(Button button, Realm r){
        checkReward(chooseDie(button, r), getActivePlayer());
        checkStatus();
    }

    public void useReward(Reward reward, Player player){

        if(reward instanceof TimeWarp){
            player.addTimeWarpCount();
            gameStat.setText(player.getPlayerName()+", you received a Time Warp! You now have: "+player.getTimeWarpCount());
            System.out.println(player.getPlayerName()+", you received a Time Warp! You now have: "+player.getTimeWarpCount());
        }
        else if(reward instanceof ArcaneBoost){
            player.addArcaneBoostCount();
            gameStat.setText(player.getPlayerName()+", you received an Arcane Boost! You now have: "+player.getArcaneBoostCount());
            System.out.println(player.getPlayerName()+", you received an Arcane Boost! You now have: "+player.getArcaneBoostCount());
        }
        else if(reward instanceof ElementalCrest) {
            player.getGameScore().addElementalCrest((ElementalCrest) reward);
            gameStat.setText(player.getPlayerName() + ", you received an Elemental Crest! You now have: " + player.getElementalCrest().length);
            System.out.println(player.getPlayerName() + ", you received an Elemental Crest! You now have: " + player.getElementalCrest().length);
        }
        else if(reward instanceof Bonus){
            Realm r = ((Bonus) reward).getRealm();
            System.out.println("\n"+player.getPlayerName()+", you received a " + r + " Bonus!");
            useBonusHelper(reward,player);
        }
    }

    private Move chooseDie(Button button, Realm r) {
        int v = r==Realm.RED?0:r==Realm.GREEN?1:r==Realm.BLUE?2:r==Realm.MAGENTA?3:r==Realm.YELLOW?4:5;
        Move m;
        if(button.getId().charAt(0)=='f'){
            getGameStatus().resetTurn();
            getGameStatus().incrementPartOfRound();
            Dice d = getAllDice()[v];
            m = new Move(d,getPassivePlayer().getScoreSheet().getCreatureByRealm(d));
            hideFRButtons();
            showButtons();
            disableButtons();
            rollButton.setVisible(true);
            gameStat.setText(getPassivePlayer().getPlayerName()+"'s Turn");
            switchPlayer();
            resetDice();
        }
        else {
            int i = 0;
            for (int x = 0; x < getAvailableDice().length; x++) {
                if (getAvailableDice()[x].getRealm() == r) {
                    i = x;
                    break;
                }
            }
            getActivePlayer().setSelectedDice(getAvailableDice()[i]);
            getActivePlayer().getSelectedDice().setDiceStatus(DiceStatus.TURN_SELECTED);
            for (Dice value : getAvailableDice()) {
                if (value.getValue() < getActivePlayer().getSelectedDice().getValue())
                    value.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
            }
            Dice d = getActivePlayer().getSelectedDice();
            button.setVisible(false);
            for (Button b : getButtons()) {
                if (Integer.parseInt(b.getText()) < d.getValue()) {
                    b.setVisible(false);
                } else {
                    b.setDisable(true);
                }
            }
            m = new Move(d,getActivePlayer().getScoreSheet().getCreatureByRealm(d));
            getGameStatus().incrementTurn();
        }
        getReward(checkReward(m,getActivePlayer()),getActivePlayer());
        tw1.setText(""+getActivePlayer().getTimeWarpCount());
        tw2.setText(""+getPassivePlayer().getTimeWarpCount());
        rollButton.setDisable(false);
        return m;

    }

    @FXML
    public void handleButtonPress(ActionEvent event) {
        Button button = (Button) event.getSource();
        disablePlayer1(); disablePlayer2(); //disableButtons(button);
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
                highlightRedPossibleMoves(button);
            else if (button.equals(die2) || button.equals(forgottenRealm2))
                highlightGreenPossibleMoves(button);
            else if (button.equals(die3) || button.equals(forgottenRealm3))
                highlightBluePossibleMoves(button);
            else if (button.equals(die4) || button.equals(forgottenRealm4))
                highlightMagentaPossibleMoves(button);
            else if (button.equals(die5) || button.equals(forgottenRealm5))
                highlightYellowPossibleMoves(button);
            else if (button.equals(die6) || button.equals(forgottenRealm6))
                highlightArcanePrismPossibleMoves(button);

        // Add the selection class to the new button
        selectedButton.getStyleClass().add("selected-button");
    }
    private void highlightRedPossibleMoves(Button button) {
        int i = Integer.parseInt(button.getText());
        int pr = getGameStatus().getPartOfRound();
        boolean fr = button.getId().charAt(0)=='f';
        boolean av = button.getId().charAt(0)=='d';
        boolean p0fr = (fr && pr==0), p1fr = (fr && pr==1), p0av = (av && pr==0), p1av = (av && pr==1);
        if (p0fr || p1av) {
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new RedDice(Integer.parseInt(button.getText())));
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 12; x++) {
                if (!getPLayer2Buttons()[0][x].getText().equals("X") && i == Integer.parseInt(getPLayer2Buttons()[0][x].getText())) {
                    getPLayer2Buttons()[0][x].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer2Buttons()[0][x].setDisable(false);
                }
            }
        }
        else if(p1fr || p0av){
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer1(), new RedDice(Integer.parseInt(button.getText())));
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 12; x++) {
                if (!getPLayer1Buttons()[0][x].getText().equals("X") && i == Integer.parseInt(getPLayer1Buttons()[0][x].getText())) {
                    getPLayer1Buttons()[0][x].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer1Buttons()[0][x].setDisable(false);
                }
            }
        }
    }
    private void highlightGreenPossibleMoves(Button button){
        int h = getAllDice()[1].getValue() + getAllDice()[5].getValue();
        int pr = getGameStatus().getPartOfRound();
        boolean fr = button.getId().charAt(0)=='f';
        boolean av = button.getId().charAt(0)=='d';
        boolean p0fr = (fr && pr==0), p1fr = (fr && pr==1), p0av = (av && pr==0), p1av = (av && pr==1);
        if (p0fr || p1av) {
            if (getGameStatus().getPartOfRound() == 1) {
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
        else if(p1fr || p0av) {
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
    }
    private void highlightBluePossibleMoves(Button button){
        int value = Integer.parseInt(button.getText());
        int pr = getGameStatus().getPartOfRound();
        System.out.println(button.getId());
        boolean fr = button.getId().charAt(0)=='f';
        boolean av = button.getId().charAt(0)=='d';
        boolean p0fr = (fr && pr==0), p1fr = (fr && pr==1), p0av = (av && pr==0), p1av = (av && pr==1);
        if (p0fr || p1av) {
            int i = getGameBoard().getPlayer2().getScoreSheet().getHydra().headsKilled();
            Move[] m = getPossibleMovesForADie(getGameBoard().getPlayer2(), new BlueDice(value));
            if (m.length == 0) {
                return;
            }
            for (int x = 0; x < 11; x++) {
                if (i == x) {
                    getPLayer2Buttons()[2][i].setStyle("-fx-border-color: black; -fx-border-width: 3;");
                    getPLayer2Buttons()[2][i].setDisable(false);
                }
            }
        }
        else if (p1fr || p0av) {
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
    }
    private void highlightMagentaPossibleMoves(Button button){
        int value = Integer.parseInt(button.getText());
        int pr = getGameStatus().getPartOfRound();
        boolean fr = button.getId().charAt(0)=='f';
        boolean av = button.getId().charAt(0)=='d';
        boolean p0fr = (fr && pr==0), p1fr = (fr && pr==1), p0av = (av && pr==0), p1av = (av && pr==1);
        if(p0fr || p1av) {
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
        else if(p1fr || p0av) {
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
    }
    private void highlightYellowPossibleMoves(Button button) {
        int value = Integer.parseInt(button.getText());
        int pr = getGameStatus().getPartOfRound();
        boolean fr = button.getId().charAt(0)=='f';
        boolean av = button.getId().charAt(0)=='d';
        boolean p0fr = (fr && pr==0), p1fr = (fr && pr==1), p0av = (av && pr==0), p1av = (av && pr==1);
        if (p0fr || p1av) {
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
        else if (p1fr || p0av) {
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
        }
    }
    private void highlightArcanePrismPossibleMoves(Button button){
        highlightRedPossibleMoves(button);
        highlightGreenPossibleMoves(button);
        highlightBluePossibleMoves(button);
        highlightMagentaPossibleMoves(button);
        highlightYellowPossibleMoves(button);
    }
    @FXML
    public void attackRed(ActionEvent event) {
        Button button = (Button) event.getSource();
        RedDice d;
        boolean flag = false;
        if(selectedButton.equals(die6) || selectedButton.equals(forgottenRealm6)) {
            int a = getAllDice()[5].getValue();
            d = new RedDice(a);
            flag = true;
        }
        else
            d = (RedDice)getAllDice()[0];
        int i = d.getValue();
        d.selectsDragon(button.getId().charAt(1)-48);
        if(Character.isUpperCase(button.getId().charAt(0))){
            for(int x=0;x<12;x++){
                if(!getPLayer1Buttons()[0][x].getText().equals("X") && i == Integer.parseInt(getPLayer1Buttons()[0][x].getText()) && button.equals(getPLayer1Buttons()[0][x]) && makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getDragon()))){
                    getPLayer1Buttons()[0][x].setText("X");
                    getPLayer1Buttons()[0][x].setDisable(true);
                    disablePlayer1();
                    break;
                }
            }
        }
        else{
            for(int x=0;x<12;x++){
                if(!getPLayer2Buttons()[0][x].getText().equals("X") && i == Integer.parseInt(getPLayer2Buttons()[0][x].getText()) && button.equals(getPLayer2Buttons()[0][x]) && makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getDragon()))){
                    getPLayer2Buttons()[0][x].setText("X");
                    getPLayer2Buttons()[0][x].setDisable(true);
                    disablePlayer2();
                    break;
                }
            }
        }
        Realm r = flag ? Realm.WHITE : Realm.RED;
        afterAttack(selectedButton, r);
        }
    @FXML
    public void attackGreen(ActionEvent event){
        Button button = (Button) event.getSource();
        int i = getAllDice()[1].getValue()+getAllDice()[5].getValue();
        Dice d = new GreenDice(i);
        Dice h = getAllDice()[1];
        boolean flag = (selectedButton.equals(die6) || selectedButton.equals(forgottenRealm6));
        if(Character.isUpperCase(button.getId().charAt(0))){
            for(int x=0;x<11;x++){
                if(button.equals(getPLayer1Buttons()[1][x]) && makeMove(getGameBoard().getPlayer1(), new Move(h,getGameBoard().getPlayer1().getScoreSheet().getGaia()))){
                    getPLayer1Buttons()[1][x].setText("X");
                    disablePlayer1();
                break;
                }
            }
        }
        else{
            for(int x=0;x<11;x++){
                if(button.equals(getPLayer2Buttons()[1][x]) && makeMove(getGameBoard().getPlayer2(), new Move(h,getGameBoard().getPlayer2().getScoreSheet().getGaia()))){
                        getPLayer2Buttons()[1][x].setText("X");
                        disablePlayer2();
                        break;
                }
            }
        }
        Realm r = flag ? Realm.WHITE : Realm.GREEN;
        afterAttack(selectedButton, r);
    }
    @FXML
    public void attackBlue(ActionEvent event){
        Button button = (Button) event.getSource();
        BlueDice d;
        boolean flag = false;
        if(selectedButton.equals(die6) || selectedButton.equals(forgottenRealm6)) {
            int a = Integer.parseInt(selectedButton.getText());
            d = new BlueDice(a);
            flag = true;
        }
        else
            d = (BlueDice)getAllDice()[2];
        int i = d.getValue();
        if(Character.isUpperCase(button.getId().charAt(0))) {
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer1Buttons()[2][x]) && makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getHydra()))) {
                    getPLayer1Buttons()[2][x].setText("X");
                    disablePlayer1();
                    break;
                }
            }
        }
        else{
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer2Buttons()[2][x]) && makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getHydra()))) {
                        getPLayer2Buttons()[2][x].setText("X");
                        disablePlayer2();
                        break;
                }
            }
        }
        Realm r = flag ? Realm.WHITE : Realm.BLUE;
        afterAttack(selectedButton, Realm.BLUE);
    }
    @FXML
    public void attackMagenta(ActionEvent event){
        Button button = (Button) event.getSource();
        MagentaDice d;
        boolean flag = false;
        if(selectedButton.equals(die6) || selectedButton.equals(forgottenRealm6)) {
            int a = Integer.parseInt(selectedButton.getText());
            d = new MagentaDice(a);
            flag = true;
        }
        else
            d = (MagentaDice)getAllDice()[3];
        int i = d.getValue();
        if(Character.isUpperCase(button.getId().charAt(0))) {
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer1Buttons()[3][x]) && makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getPhoenix()))) {
                    getPLayer1Buttons()[3][x].setText("" + i);
                    disablePlayer1();
                    break;
                }
            }
        }
        else{
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer2Buttons()[3][x]) && makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getPhoenix()))) {
                    getPLayer2Buttons()[3][x].setText("" + i);
                    disablePlayer2();
                    break;
                }
            }
        }
        Realm r = flag ? Realm.WHITE : Realm.MAGENTA;
        afterAttack(selectedButton, r);
    }
    @FXML
    public void attackYellow(ActionEvent event) {
        Button button = (Button) event.getSource();
        YellowDice d;
        boolean flag = false;
        if(selectedButton.equals(die6) || selectedButton.equals(forgottenRealm6)) {
            int a = Integer.parseInt(selectedButton.getText());
            d = new YellowDice(a);
            flag = true;
        }
        else
            d = (YellowDice)getAllDice()[4];
        int i = d.getValue();
        if(Character.isUpperCase(button.getId().charAt(0))) {
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer1Buttons()[4][x]) && makeMove(getGameBoard().getPlayer1(), new Move(d, getGameBoard().getPlayer1().getScoreSheet().getLion()))) {
                    getPLayer1Buttons()[4][x].setText(""+i);
                    disablePlayer1();
                    break;
                }
            }
        }
        else {
            for (int x = 0; x < 11; x++) {
                if (button.equals(getPLayer2Buttons()[4][x]) && makeMove(getGameBoard().getPlayer2(), new Move(d, getGameBoard().getPlayer2().getScoreSheet().getLion()))) {
                    getPLayer2Buttons()[4][x].setText(""+i);
                    disablePlayer2();
                    break;
                }
            }
        }
        Realm r = flag ? Realm.WHITE : Realm.YELLOW;
        afterAttack(selectedButton, r);
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



 }





    

   






