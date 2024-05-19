package game.creatures;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
    private String[] rowStrings;
    private String[] colStrings;


    public Gaia(){
        this.defeatedGaias = 0;
        this.totalGaias = 11;
        rowRewards = new Reward[3];
        columnRewards = new Reward[4];
        scores = new int[11];
        rowStrings = new String[3];
        colStrings = new String[4];
        readConfigRewards();
        readConfigScores();
    }

    private void readConfigRewards() {
        Properties prop = new Properties();
        try (FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/TerrasHeartlandRewards.properties")) {
            prop.load(rewardConfig);
            rowStrings[0] = prop.getProperty("row1Reward");
            rowStrings[1] = prop.getProperty("row2Reward");
            rowStrings[2] = prop.getProperty("row3Reward");
            colStrings[0] = prop.getProperty("column1Reward");
            colStrings[1] = prop.getProperty("column2Reward");
            colStrings[2] = prop.getProperty("column3Reward");
            colStrings[3] = prop.getProperty("column4Reward");

            loadRewards(rowStrings, rowRewards);
            loadRewards(colStrings, columnRewards);
        } catch (IOException e) {
            System.out.println("Error loading reward configuration: " +  e.getMessage());
        }
    }

    private void loadRewards(String[] rewardStrings, Reward[] rewards) {
        for (int i = 0; i < rewardStrings.length; i++) {
            if (rewardStrings[i] == null) continue;
            Realm realm;
            switch (rewardStrings[i]) {
                case "YellowBonus":
                    realm = Realm.YELLOW;
                    rewards[i] = new Bonus(realm);
                    break;
                case "RedBonus":
                    realm = Realm.RED;
                    rewards[i] = new Bonus(realm);
                    break;
                case "ElementalCrest":
                    realm = Realm.GREEN;
                    rewards[i] = new ElementalCrest(realm);
                    break;
                case "TimeWarp":
                    realm = Realm.YELLOW;
                    rewards[i] = new Bonus(realm);
                    break;
                case "BlueBonus":
                    realm = Realm.BLUE;
                    rewards[i] = new Bonus(realm);
                    break;
                case "MagentaBonus":
                    realm = Realm.MAGENTA;
                    rewards[i] = new ElementalCrest(realm);
                    break;
                case "ArcaneBoost":
                    rewards[i] = new ArcaneBoost();
                    break;
                default:
                    System.out.println("Unknown reward type: " + rewardStrings[i]);
                    break;
            }
        }
    }

    private void readConfigScores(){
        Properties prop2 = new Properties();
        try{
            String scrs = "";
            FileInputStream scoreConfig = new FileInputStream("src/main/resources/config/TerrasHeartland.properties");
            prop2.load(scoreConfig);
            scrs = prop2.getProperty("score");
            String[] scoreString = scrs.split(", ");
            for(int i = 0; i < scores.length; i++){
                scores[i] = Integer.parseInt(scoreString[i]);
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private boolean attackGaia(Dice combined) {//throws InvalidMoveException{
        boolean flag = false;
        int total = combined.getValue();

        for(int i = 0; i < gaias.length && !flag;i++){
            for(int j = 0; j < gaias[i].length && !flag;j++){
                if(gaias[i][j] == total){
                    gaias[i][j] = 0;
                    defeatedGaias++;
                    flag = true;
                    System.out.println("You have successfully attacked the " + (total - 1) + "th Gaia Guardian!");
                }
            }
        }

        editRewards();
        checkBonus();
        return flag;
    }

    @Override
    public boolean makeMove(Move m){
        Dice dice = m.getDice();
        return attackGaia(dice);
    }

    @Override
    public Move[] getAllPossibleMoves() {
        //Move[] allPossibleMoves = new Move[11];
        List<Move> allPossible = new LinkedList<>();
        //int count = 0;

        for(int i = 0; i < 3; i++){

            for(int j = 0; j < 4; j++){
                if(gaias[i][j] != 0){
                    int val = gaias[i][j];
                    //allPossibleMoves[count] = new Move(new GreenDice(val), this);
                    allPossible.add(new Move(new GreenDice(val), this));
                }
                //count++;
            }
        }
        Move[] allPossibleMoves = new Move[allPossible.size()];
        allPossibleMoves = allPossible.toArray(allPossibleMoves);

        return allPossibleMoves;
    }

    @Override
    public Move[] getPossibleMovesForADie(Dice die) {
        List<Move> possibleMoves = new LinkedList<>();
        int val = die.getValue();

        possibleMoves.add(new Move(new GreenDice(val), this));

        Move[] possibleMovesForADie = new Move[possibleMoves.size()];
        possibleMovesForADie = possibleMoves.toArray(possibleMovesForADie);

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
                horizontalBonus[i] = true;
                rowFlag[i] = false;
            }
        }
    }

    public void checkBonus(){
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


    public int getGreenRealmScore(){
        return scores[defeatedGaias - 1];
    }

    public int getDefeatedGaias(){
        return defeatedGaias;
    }

    public int getRemainingGaias(){
        return this.totalGaias - defeatedGaias;
    }

    public String toString(){
        String s = 
        "Terra's Heartland: Gaia Guardians (GREEN REALM):\n"
        + "+-----------------------------------+\n"
        + "|  #  |1    |2    |3    |4    |R    |\n"
        + "+-----------------------------------+\n"
        // + "|  1  |X    |2    |3    |4    |YB   | \n"
        + "|  1  " + scoreSheetHelper(0) + "|" + (horizontalBonus[0]?"X ":initialsRows(0)) + "   |\n"
        // + "|  2  |5    |6    |7    |8    |RB   | \n"
        + "|  2  " + scoreSheetHelper(1) + "|" + (horizontalBonus[1]?"X ":initialsRows(1)) + "   |\n"
        // + "|  3  |9    |10   |11   |12   |EC   | \n"
        + "|  3  " + scoreSheetHelper(2) + "|" + (horizontalBonus[2]?"X ":initialsRows(2)) + "   |\n"
        + "+-----------------------------------+\n"
        //+ "|  R  |TW   |BB   |MB   |AB   |     | \n"
        + "|  R  " + scoreSheetHelper3() + "|\n"
        + "+-----------------------------------------------------------------------+\n"
        //+ "|  S  |0    |1    |2    |4    |7    |11   |16   |22   |29   |37   |46   |56   | \n"
        + "|  S  " + scoreSheetHelper2() + "|\n"
        + "+-----------------------------------------------------------------------+\n\n\n";
        return s;
    }
    
    private String scoreSheetHelper2(){
        String s = "";
        for(int i = 0; i < scores.length; i++){
            s+= "|" + scores[i];
            s+= (scores[i] < 10)?"    ":"   ";
        }
        return s;
    }

    private String scoreSheetHelper3(){
        String s = "";
        for(int i = 0; i < verticalBonus.length; i++){
            if(!verticalBonus[i]){
                switch(i){
                    case 0:
                        s+="|" + initialsColumns(0) + "   ";
                        break;
                    case 1:
                        s+="|" + initialsColumns(1) + "   ";
                        break;
                    case 2:
                        s+="|" + initialsColumns(2) + "   ";
                        break;
                    case 3:
                        s+="|" + initialsColumns(3) + "   ";
                        break;
                    default:
                        break;
                }
            }
            else
                s+= "|X    ";
        }
        s+="|     ";
        return s;
    }

    private String initialsRows(int num) {
        return getInitials(rowStrings[num]);
    }

    private String initialsColumns(int num) {
        return getInitials(colStrings[num]);
    }

    private String getInitials(String reward) {
        switch (reward) {
            case "RedBonus": return "RB";
            case "YellowBonus": return "YB";
            case "BlueBonus": return "BB";
            case "MagentaBonus": return "MB";
            case "ElementalCrest": return "EC";
            case "ArcaneBoost": return "AB";
            case "TimeWarp": return "TW";
            default: return "";
        }
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
        boolean[][] scoresBool = checkZero();
        String s = "";
            for(int i = 0; i < scoresBool[rowNum].length; i++){
                if(scoresBool[rowNum][i])
                    s+= "|X    ";
                else 
                    s+= "|" + this.gaias[rowNum][i] + ((this.gaias[rowNum][i] > 9)?"   ":"    ");;
            }
        
        return s;
    }


    // public static void main(String[] args){
    //     Gaia g = new Gaia();
    //     System.out.println(g);
    //     g.makeMove(new Move(new GreenDice(5),g));
    //     g.makeMove(new Move(new GreenDice(2),g));
    //     g.makeMove(new Move(new GreenDice(3),g));
    //     g.makeMove(new Move(new GreenDice(4),g));

    //     System.out.println(g);
    //     g.makeMove(new Move(new GreenDice(5),g));
    //     g.makeMove(new Move(new GreenDice(6),g));
    //     g.makeMove(new Move(new GreenDice(7),g));
    //     g.makeMove(new Move(new GreenDice(8),g));
    //     System.out.println(g);
    //     g.makeMove(new Move(new GreenDice(9),g));

    //     g.makeMove(new Move(new GreenDice(10),g));

    //     g.makeMove(new Move(new GreenDice(11),g));
    //     //g.makeMove(new Move(new GreenDice(12),g));
    //     System.out.println(g);

    // }
}
