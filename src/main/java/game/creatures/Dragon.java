package game.creatures;

import game.collectibles.*;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.Move;
import game.engine.Player;

import java.io.FileInputStream;
import java. io.IOException;
import java.util.Properties;

public class Dragon extends Creature {

    private int [][] dragonParts;
    private int [] score;
    private boolean [] bounsBoolean;
    private Reward [] rewardString;

    public Dragon() {

        // config file to get the score for each column
        try {
            Properties prop1 = new Properties();
            FileInputStream scoreConfig = new FileInputStream("config/EmberfallDominionScore.properties");
            prop1.load(scoreConfig);
            int score1 = Integer.parseInt(prop1.getProperty("col1Score"));
            int score2 = Integer.parseInt(prop1.getProperty("col2Score"));
            int score3 = Integer.parseInt(prop1.getProperty("col3Score"));
            int score4 = Integer.parseInt(prop1.getProperty("col4Score"));
            score = new int[]{score1, score2, score3,score4};
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // config file to get the reward for each row
        try {
            Properties prop2 = new Properties();
            FileInputStream rewardConfig = new FileInputStream("config/EmberfallDominionRewards.properties");
            prop2.load(rewardConfig);
            String Reward1 = prop2.getProperty("row1Reward");
            String Reward2 = prop2.getProperty("row2Reward");
            String Reward3 = prop2.getProperty("row3Reward");
            String Reward4 = prop2.getProperty("row4Reward");
            String Reward5 = prop2.getProperty("diagonalReward");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // config file to get the DiceValue for each row
        try {
            Properties prop3 = new Properties();
            FileInputStream rewardConfig = new FileInputStream("config/EmberfallDominionRewards.properties");
            prop3.load(rewardConfig);
            int row1col1DiceValue = Integer.parseInt(prop3.getProperty("row1col1DiceValue"));
            int row1col2DiceValue = Integer.parseInt(prop3.getProperty("row1col2DiceValue"));
            int row1col3DiceValue = Integer.parseInt(prop3.getProperty("row1col3DiceValue"));
            int row1col4DiceValue = Integer.parseInt(prop3.getProperty("row1col4DiceValue"));
            int row2col1DiceValue = Integer.parseInt(prop3.getProperty("row2col1DiceValue"));
            int row2col2DiceValue = Integer.parseInt(prop3.getProperty("row2col2DiceValue"));
            int row2col3DiceValue = Integer.parseInt(prop3.getProperty("row2col3DiceValue"));
            int row2col4DiceValue = Integer.parseInt(prop3.getProperty("row2col4DiceValue"));
            int row3col1DiceValue = Integer.parseInt(prop3.getProperty("row3col1DiceValue"));
            int row3col2DiceValue = Integer.parseInt(prop3.getProperty("row3col2DiceValue"));
            int row3col3DiceValue = Integer.parseInt(prop3.getProperty("row3col3DiceValue"));
            int row3col4DiceValue = Integer.parseInt(prop3.getProperty("row3col4DiceValue"));
            int row4col1DiceValue = Integer.parseInt(prop3.getProperty("row4col1DiceValue"));
            int row4col2DiceValue = Integer.parseInt(prop3.getProperty("row4col2DiceValue"));
            int row4col3DiceValue = Integer.parseInt(prop3.getProperty("row4col3DiceValue"));
            int row4col4DiceValue = Integer.parseInt(prop3.getProperty("row4col4DiceValue"));

            dragonParts = new int[][]{
                      {row1col1DiceValue, row1col2DiceValue, row1col3DiceValue, row1col4DiceValue}
                    , {row2col1DiceValue, row2col2DiceValue, row2col3DiceValue, row2col4DiceValue}
                    , {row3col1DiceValue, row3col2DiceValue, row3col3DiceValue, row3col4DiceValue}
                    , {row4col1DiceValue, row4col2DiceValue, row4col3DiceValue, row4col4DiceValue}
            };
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reward checkBonus (int[][] dragonParts){
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++){
            int c = 0;
            for (int j = 0; j < dragonParts.length; j++){
                if (dragonParts[i][j] == 0){
                    c++;
                    x++;
                }
            }
            if (c == dragonParts.length){
                switch (i){
                    case 0:
                        return new Bonus(Realm.GREEN);
                    case 1:
                        return new Bonus(Realm.YELLOW);
                    case 2:
                        return new Bonus(Realm.BLUE);
                    case 3:
                        return new ElementalCrest(Realm.RED);
                    default:
                        break;
                }
            }
        }
        if (x == 16)
            return new ArcaneBoost();
        return null;
    }

    public int getPoints (){
        int sum = 0;
        for (int j = 0; j < dragonParts.length; j++){
            int c = 0;
            for (int i = 0; i < dragonParts[i].length; i++){
                if (dragonParts[i][j] == 0){
                    c++;
                }
            }
            if (c == dragonParts[j].length){
                switch (j){
                    case 0:
                        sum = sum + score[0];
                        break;
                    case 1:
                        sum = sum + score[1];
                        break;
                    case 2:
                        sum = sum + score[2];
                        break;
                    case 3:
                        sum = sum + score[3];
                        break;
                }
            }
        }
        return sum;
    }

    public Move[] getPossibleMovesForADie (Dice dice) throws Exception {
        Move [] moves = new Move[2];
        int a = ((RedDice)dice).getValue();
        boolean flag = false;
        for (int i = 0; i < dragonParts.length; i++){
            for (int j = 0; j < dragonParts.length; j++){
                if (a == dragonParts [i][j] && dragonParts[i][j] != 0) {
                    moves[i] = new Move(dice, this, j);
                    flag = true;
                }
            }
        }
        if (flag == false){
            throw new Exception("There is no possible moves for this die value");
        }
        else {
            return moves;
        }
    }

    public void makeMove (Move a) throws Exception {
        int x = 0;
        Move [] b = getPossibleMovesForADie(a.getDice());
        boolean flag = true;
        for (int i = 0; i < b.length; i++){
            // make sure that the Move a is included in the array Move [] b
        }
        if (b.length != 0 && flag == true){
            for (int i = 0; i < dragonParts.length; i++){
                for (int j = 0; j < dragonParts[i].length; j++){
                    if (a.getDice().getValue() == dragonParts [i][j] && ((RedDice)(a.getDice())).selectsDragon(i)== j ) {
                        if (dragonParts[i][j] != 0) {
                            dragonParts[i][j] = 0;
                            break;
                        }
                        else
                            x++;
                    }
                }
            }
        }

        }

    public void scoreSheet(){
        String expectedScoreSheet = "\n\nScoreSheet\n\n" +
                "Emberfall Dominion: Pyroclast Dragon (RED REALM):\n" +
                "+-----------------------------------+\n" +
                "|  #  |D1   |D2   |D3   |D4   |R    |\n" +
                "+-----------------------------------+\n" +
                "|  F  |" + scoreSheetHelper(0,0)+ "|" + scoreSheetHelper(0,1)+ "|" + scoreSheetHelper(0,2)+ "|X    |GB   |\n" +
                "|  W  |" + scoreSheetHelper(1,0)+ "|" + scoreSheetHelper(1,1)+ "|X    |" + scoreSheetHelper(1,2)+ "|YB   |\n" +
                "|  T  |" + scoreSheetHelper(2,0)+ "|X    |" + scoreSheetHelper(2,1)+ "|" + scoreSheetHelper(2,2)+ "|BB   |\n" +
                "|  H  |X    |" + scoreSheetHelper(3,0)+ "|" + scoreSheetHelper(3,1)+ "|" + scoreSheetHelper(3,3)+ "|EC   |\n" +
                "+-----------------------------------+\n" +
                "|  S  |10   |14   |16   |20   |AB   |\n" +
                "+-----------------------------------+\n\n" +
                "\n";
    }

    private String scoreSheetHelper(int i, int j){
        if (dragonParts[i][j] == 0){
            return "X    ";
        }
        else {
            return dragonParts[i][j] + "    ";
        }
    }

    public Move[] getAllPossibleMoves(){
        int c = 0;

        for (int i = 0; i < dragonParts.length; i++) {
            for (int j = 0; j < dragonParts.length; j++) {
                if (dragonParts[i][j] != 0)
                    c++;
            }
        }
        Move [] moves = new Move[c];
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++) {
            for (int j = 0; j < dragonParts.length; j++) {
                if (dragonParts[i][j] != 0) {
                    Dice dice = new RedDice (dragonParts[i][j]);
                    moves[x] = new Move(dice, this, j);
                    x++;
                }
            }
        }
        return moves;
    }
}
