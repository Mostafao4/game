package game.creatures;

import game.collectibles.*;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.Move;
import java.io.FileInputStream;
import java. io.IOException;
import java.util.Properties;

public class Dragon extends Creature {

    private int [][] dragonParts;
    private int [] score;
    private String [] reward;
    private boolean [] bonusBoolean;
    private Reward [] rewardString;

    public Dragon() {
        bonusBoolean = new boolean[4];
        // config file to get the score for each column
        try {
            Properties prop1 = new Properties();
            FileInputStream scoreConfig = new FileInputStream("src/main/resources/config/EmberfallDominionScore.properties");
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
            FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/EmberfallDominionRewards.properties");
            prop2.load(rewardConfig);
            String Reward1 = prop2.getProperty("row1Reward");
            String Reward2 = prop2.getProperty("row2Reward");
            String Reward3 = prop2.getProperty("row3Reward");
            String Reward4 = prop2.getProperty("row4Reward");
            String Reward5 = prop2.getProperty("diagonalReward");
            reward = new String[]{Reward1, Reward2, Reward3, Reward4, Reward5};

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // config file to get the DiceValue for each row
        try {
            Properties prop3 = new Properties();
            FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/EmberfallDominionDiceValue.properties");
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

    public Reward []checkBonus (){
        Reward [] bonus = new Reward[2];
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++){
            int c = 0;
            for (int j = 0; j < dragonParts.length; j++){
                if (dragonParts[i][j] == 0){
                    c++;
                }
                if (dragonParts[i][j] == 0 && i == j){
                    x++;
                }
            }
            if (c == dragonParts.length){
                switch (i){
                    case 0:
                        if (bonusBoolean[0] == false){
                            bonus [0] = checkBonusHelper(reward[0]);
                            bonusBoolean[0] = true;
                            break;
                        }
                    case 1:
                        if (bonusBoolean[1] == false){
                            bonus [1] = checkBonusHelper(reward[1]);
                            bonusBoolean[1] = true;
                            break;
                        }
                    case 2:
                        if (bonusBoolean[2] == false){
                            bonus [2] = checkBonusHelper(reward[2]);
                            bonusBoolean[2] = true;
                            break;
                        }
                    case 3:
                        if (bonusBoolean[3] == false){
                            bonus [3] = checkBonusHelper(reward[3]);
                            bonusBoolean[3] = true;
                            break;
                        }
                    default:
                        break;
                }
            }
        }
        if (x == 4 && bonusBoolean[4] == false){
            bonus [1] = checkBonusHelper(reward[4]);
            bonusBoolean[4] = true;
        }
        return null;
    }

    private Reward checkBonusHelper (String s){
        switch (s){
            case "GreenBonus":
                return new Bonus(Realm.GREEN);
            case "YellowBonus":
                return new Bonus(Realm.YELLOW);
            case "BlueBonus":
                return new Bonus(Realm.BLUE);
            case "ElementalCrest":
                return new ElementalCrest(Realm.RED);
            case "ArcaneBoost":
                return new ArcaneBoost();
            default:
                return null;
        }
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

    public Move[] getPossibleMovesForADie (Dice dice) throws Exception {
        Move [] moves = new Move[2];
        int a = dice.getValue();
        boolean flag = false;
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++){
            for (int j = 0; j < dragonParts.length; j++){
                if (a == dragonParts [i][j] && dragonParts[i][j] != 0) {
                    moves[x] = new Move(dice, this, j);
                    x++;
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
//        Move [] b = getPossibleMovesForADie(a.getDice());
//        boolean flag = true;
//        int y = -1;
//        for (int i = 0; i < b.length; i++){
//            if (b[i].compareTo(a) == 0){
//                y = i;
//                break;
//            }
//        }
       // if (y != -1){
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
       // }
    }
//        if (b.length != 0 && flag == true){
//            for (int i = 0; i < dragonParts.length; i++){
//                for (int j = 0; j < dragonParts[i].length; j++){
//                    if (a.getDice().getValue() == dragonParts [i][j] && ((RedDice)(a.getDice())).selectsDragon(i)== j ) {
//                        if (dragonParts[i][j] != 0) {
//                            dragonParts[i][j] = 0;
//                            break;
//                        }
//                        else
//                            x++;
//                    }
//                }
//            }
//        }

    public String toString(){
        return "\n\nScoreSheet\n\n" +
                "Emberfall Dominion: Pyroclast Dragon (RED REALM):\n" +
                "+-----------------------------------+\n" +
                "|  #  |D1   |D2   |D3   |D4   |R    |\n" +
                "+-----------------------------------+\n" +
                "|  F  |" + scoreSheetHelperDiceValue(0,0)+ "|" + scoreSheetHelperDiceValue(0,1)+ "|" + scoreSheetHelperDiceValue(0,2)+ "|" + scoreSheetHelperDiceValue(0,3)+ "|"+ scoreSheetHelperBonus(0)  +"|\n" +
                "|  W  |" + scoreSheetHelperDiceValue(1,0)+ "|" + scoreSheetHelperDiceValue(1,1)+ "|" + scoreSheetHelperDiceValue(1,2)+ "|" + scoreSheetHelperDiceValue(1,3)+ "|"+ scoreSheetHelperBonus(1)  +"|\n" +
                "|  T  |" + scoreSheetHelperDiceValue(2,0)+ "|" + scoreSheetHelperDiceValue(2,1)+ "|" + scoreSheetHelperDiceValue(2,2)+ "|" + scoreSheetHelperDiceValue(2,3)+ "|"+ scoreSheetHelperBonus(2)  +"|\n" +
                "|  H  |" + scoreSheetHelperDiceValue(3,0)+ "|" + scoreSheetHelperDiceValue(3,1)+ "|" + scoreSheetHelperDiceValue(3,2)+ "|" + scoreSheetHelperDiceValue(3,3)+ "|"+ scoreSheetHelperBonus(3)  +"|\n" +
                "+-----------------------------------+\n" +
                "|  S  |" + scoreSheetHelperScore(0)+ "|" + scoreSheetHelperScore(1)+ "|" + scoreSheetHelperScore(2)+ "|" + scoreSheetHelperScore(3)+ "|"+ scoreSheetHelperBonus(4)  +"|\n" +
                "+-----------------------------------+\n\n" +
                "\n";
    }

    private String scoreSheetHelperDiceValue(int i, int j){
        if (dragonParts[i][j] == 0){
            return "X    ";
        }
        else {
            return dragonParts[i][j] + "    ";
        }
    }

    private String scoreSheetHelperScore (int j){
        if (score[j]< 9)
            return score[j] + "    ";
        return score[j] + "   ";
    }

    private String scoreSheetHelperBonus (int i){

        switch (reward[i]){
            case "GreenBonus":
                return "GB   ";
            case "YellowBonus":
                return "YB   ";
            case "BlueBonus":
                return "BB   ";
            case "ElementalCrest":
                return "EC   ";
            case "ArcaneBoost":
                return "AB   ";
            default:
                return null;
        }
    }

}
