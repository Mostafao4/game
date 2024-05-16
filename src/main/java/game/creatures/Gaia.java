package game.creatures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;


import game.dice.*;
import game.engine.GameBoard;
import game.engine.Move;
import game.collectibles.*;
import game.exceptions.*;

public class Gaia extends Creature{

    private Reward[] rowRewards;
    private Reward[] columnRewards;
    private int defeatedGaias;
    private int totalGaias;
    private int[][] gaias = 
    {
        {0,2,3,4},
        {5,6,7,8},
        {9,10,11,12}
    };
    private boolean[] columnFlag = {true,true,true,true};
    private boolean[] rowFlag = {true,true,true,true};

    private boolean[] verticalBonus = new boolean[4];
    private boolean[] horizontalBonus = new boolean[3];
    private int[] scores;


    public Gaia(){
        this.defeatedGaias = 0;
        this.totalGaias = 11;
        rowRewards = new Reward[3];
        columnRewards = new Reward[4];
        readConfigRewards();
        readConfigScores();
    }

    private void readConfigRewards(){
        String[] rowStrings = new String[3];
        String[] colStrings = new String[4];

        Properties prop1 = new Properties();

        try{
            prop1.load(Gaia.class.getClassLoader().getResourceAsStream("TerrasHeartlandRewards.properties"));
            rowStrings[0] = prop1.getProperty("row1Reward");
            rowStrings[1] = prop1.getProperty("row2Reward");
            rowStrings[2] = prop1.getProperty("row3Reward");
            colStrings[0] = prop1.getProperty("column1Reward");
            colStrings[1] = prop1.getProperty("column2Reward");
            colStrings[2] = prop1.getProperty("column3Reward");
            colStrings[3] = prop1.getProperty("column4Reward");

            for(int i = 0; i < rowStrings.length; i++){
                Realm r;
                switch (rowStrings[i]){
                    case "YellowBonus":
                        r = Realm.YELLOW;
                        rowRewards[i] = new Bonus(r);
                        break;
                    case "RedBonus":
                        r = Realm.RED;
                        rowRewards[i] = new Bonus(r);
                        break;
                    case "ElementalCrest":
                        r = Realm.GREEN;
                        rowRewards[i] = new ElementalCrest(r);
                    default:
                        break;
                }
            }

            for(int i = 0; i < colStrings.length; i++){
            Realm r;
            switch (colStrings[i]){
                case "TimeWarp":
                    r = Realm.YELLOW;
                    columnRewards[i] = new Bonus(r);
                    break;
                case "BlueBonus":
                    r = Realm.BLUE;
                    columnRewards[i] = new Bonus(r);
                    break;
                case "MagentaBonus":
                    r = Realm.MAGENTA;
                    columnRewards[i] = new ElementalCrest(r);
                    break;
                case "ArcaneBoost":
                    columnRewards[i] = new ArcaneBoost();
                default:
                    break;
            }
        }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        

    }


    private void readConfigScores(){
        String scrs;
        Properties prop2 = new Properties();
        try{
            prop2.load(Gaia.class.getClassLoader().getResourceAsStream("TerrasHeartland.properties"));
            scrs = prop2.getProperty("scores");
            String[] scoreString = scrs.split(",");
            for(int i = 0; i < scores.length; i++){
                scores[i] = Integer.parseInt(scoreString[i]);
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void attackGaia(Dice combined) {
        boolean flag = false;
        int total = combined.getValue();

        for(int i = 0; i < gaias.length && !flag;i++){
            for(int j = 0; j < gaias[i].length && !flag;j++){
                if(gaias[i][j] == total){
                    gaias[i][j] = 0;
                    defeatedGaias++;
                    flag = true;
                    System.out.println("You have successfully attacked a Gaia Guardian!");
                }
            }
        }

        if(!flag){
            System.out.println("No valid moves!");
            return;
        }

        editRewards();
        useImmediateBonus();
        showAvailableRewards();
    }

    @Override
    public void makeMove(Move m){
        Dice dice = m.getDice();
        attackGaia(dice);
    }

    @Override
    public Move[] getAllPossibleMoves() {
        Move[] allPossibleMoves = new Move[11];
        int count = 0;

        for(int i = 0; i < this.gaias.length; i++){

            for(int j = 0; j < this.gaias[i].length; i++){
                if(gaias[i][j] != 0){
                    int val = gaias[i][j];
                    allPossibleMoves[count] = new Move(new GreenDice(val), this);
                }
                count++;
            }
        }

        return allPossibleMoves;
    }

    @Override
    public Move[] getPossibleMovesForADie(Dice die) {
        Move[] possibleMovesForADie = new Move[11];
        int val = die.getValue();
        possibleMovesForADie[val - 1] = new Move(new GreenDice(val), this);
        return possibleMovesForADie;
    }


    private void editRewards(){
        int count = 0;
        for(int j = 0; j < gaias[0].length;j++){
            for(int i = 0; i < gaias.length; i++){
                count+= gaias[i][j];
            }
            if(count == 0 && columnFlag[j]){
                verticalBonus[j] = true;
                columnFlag[j] = false;
            }
        }

        count = 0;
        for(int i = 0; i < gaias.length; i++){
            for(int j = 0; j < gaias[0].length;j++){
                count += gaias[i][j];
            }
            if(count == 0 && rowFlag[i]){
                rowFlag[i] = false;
                horizontalBonus[i] = true;
            }
        }
    }

    public void showAvailableRewards(){
        showHorizontalRewards();
        showVerticalRewards();
    }

    private Reward showHorizontalRewards(){
        for(int i = 0; i < horizontalBonus.length; i++){
            if(horizontalBonus[i]){
                switch (i) {
                    case 0:
                        return rowRewards[0];
                    case 1:
                        return rowRewards[1];
                    case 2:
                        return rowRewards[2];
                    default:
                        break;
                }
                horizontalBonus[i] = false;
            }
        }
        return null;
    }

    private Reward showVerticalRewards(){
        for(int i = 0; i < verticalBonus.length; i++){
            if(verticalBonus[i]){
                switch (i) {
                    case 0:
                        return columnRewards[0];
                    case 1:
                        return columnRewards[1];
                    case 2:
                        return columnRewards[2];
                    case 3:
                        return columnRewards[3];
                    default:
                        break;
                }
                verticalBonus[i] = false;
            }
        }
        return null;
    }

    public void useImmediateBonus(){
        useImmediateBonusHelper();
    }

    private Reward useImmediateBonusHelper(){
        if(horizontalBonus[0]){
            horizontalBonus[0] = false;
            return rowRewards[0];
        }
        else if(horizontalBonus[1]){
            horizontalBonus[1] = false;
            return rowRewards[1];
        }
        else if(verticalBonus[1]){
            verticalBonus[1] = false;
            return columnRewards[1];
        }
        else if(verticalBonus[2]){
            verticalBonus[2] = false;
            return columnRewards[2];
        }
        else
            return null;
    }


    public int getGreenRealmScore(){
        return scores[defeatedGaias - 1];
    }

    public int getDefeatedGaias(){
        return defeatedGaias;
    }

    public int getRemainingGaias(){
        return this.totalGaias - defeatedGaias;
    }

    public void scoreSheet(){
        System.out.println("Terra's Heartland: Gaia Guardians (GREEN REALM): \n\n"
        + "+-----------------------------------+ \n"
        + "|  #  |1    |2    |3    |4    |R    | \n"
        + "+-----------------------------------+ \n"
        // + "|  1  |X    |2    |3    |4    |YB   | \n"
        + "|  1  " + scoreSheetHelper(0) + "|" + (horizontalBonus[0]?"X":"YB") + "   |\n"
        // + "|  2  |5    |6    |7    |8    |RB   | \n"
        + "|  2  " + scoreSheetHelper(1) + "|" + (horizontalBonus[1]?"X":"RB") + "   |\n"
        // + "|  3  |9    |10   |11   |12   |EC   | \n"
        + "|  3  " + scoreSheetHelper(2) + "|" + (horizontalBonus[2]?"X":"EC") + "   |\n"
        + "+-----------------------------------+ \n"
        + "|  R  |TW   |BB   |MB   |AB   |     | \n"
        + "+-----------------------------------------------------------------------------+ \n"
        + "|  S  |0    |1    |2    |4    |7    |11   |16   |22   |29   |37   |46   |56   | \n"
        + "+-----------------------------------------------------------------------------+ \n\n");
    }
    
    private boolean[][] checkZero(){
        boolean[][] zeros = new boolean[3][4];
        for(int i = 0; i < gaias.length; i++){
            for(int j = 0; j < gaias[i].length; j++){
                if(gaias[i][j] == 0)
                    zeros[i][j] = true;
            }
        }
        return zeros;
    }
    private String scoreSheetHelper(int rowNum){
        boolean[][] scores = checkZero();
        String s = "";
        if(rowNum != 2){
            for(int i = 0; i < scores[rowNum].length; i++){
                if(scores[rowNum][i])
                    s+= "|X    ";
                else
                    s+= "|" + this.gaias[rowNum][i] + "    ";
            }
        } else {
            for(int i = 0; i < scores[rowNum].length; i++){
                if(scores[rowNum][i])
                    s+= "|X    ";
                else 
                    s+= "|" + this.gaias[rowNum][i] + ((this.gaias[rowNum][i] > 9)?"   ":"    ");;
            }
        }
        
        return s;
    }

    public static void main(String[] args) {
        
    }
}
