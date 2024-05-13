package game.creatures;

import game.collectibles.*;
import game.dice.RedDice;
import game.engine.Move;
import game.engine.Player;
import game.collectibles.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java. io.IOException;
import java.util.Properties;

public class Dragon extends Creature {

    private int [][] dragonParts;

    public Dragon() {

        try {
            String filePath = "config/EmberfallDominionRewards.properties";
            Properties pros = new Properties();
            FileInputStream ip = new FileInputStream(filePath);
            pros.load(ip);
            String url = pros.getProperty("row1Reward");
            String browser = pros.getProperty("browser");
        }
        catch(Exception e) {

        }
        this.dragonParts = new int[4][4];
        dragonParts[0][0] = 3; dragonParts[0][1] = 6; dragonParts[0][2] = 5; dragonParts[0][3] = 0;
        dragonParts[1][0] = 2; dragonParts[1][1] = 1; dragonParts[1][2] = 0; dragonParts[1][3] = 5;
        dragonParts[2][0] = 1; dragonParts[2][1] = 0; dragonParts[2][2] = 2; dragonParts[2][3] = 4;
        dragonParts[3][0] = 0; dragonParts[3][1] = 3; dragonParts[3][2] = 4; dragonParts[3][3] = 6;
    }

    public Reward checkBonus (int[][] dragonParts){
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++){
            int c = 0;
            for (int j = 0; j < dragonParts[i].length; j++){
                if (dragonParts[i][j] == 0){
                    c++;
                    x++;
                }
            }
            if (c == dragonParts[i].length){
                switch (i){
                    case 0:
                        return new GreenBonus();
                        break;
                    case 1:
                        return  new YellowBonus();
                        break;
                    case 2:
                        return new BlueBonus();
                        break;
                    case 3:
                        return new ElementalCrest();
                        break;
                }
            }
        }
        if (x == 16){

            return ArcaneBoost;

        }
        return null;
    }

    public int getPoints (int[][] dragonParts){
        int score = 0;
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
                        score = score +  10;
                        break;
                    case 1:
                        score = score + 14;
                        break;
                    case 2:
                        score = score + 16;
                        break;
                    case 3:
                        score = score + 20;
                        break;
                }
            }
        }
        return score;
    }

    public void makeMove (Player player, Move[] a) throws Exception{
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++){
            for (int j = 0; j < dragonParts[i].length; j++){
                if (player.getSelectedDice().getValue() == dragonParts [i][j] && /*selectsDragon*/x == j ) {
                    if (dragonParts[i][j] != 0) {
                        dragonParts[i][j] = 0;
                        break;
                    } else {
                        x++;
                    }
                }
            }
        }
        if (c == 16){
            throw new Exception("This dragon has been attacked!");
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



    /*
    comment: add this class in RedDice
    "please select a dragon to attack, choose from 1 to 4"
    public int selectsDragon (int dragonNumber[i]){
        return dragonNumber[i];
    }
    public Move[] getPossibleMovesForADie (Player player, Dice dice){
        Move move = new Move();
        int a = dice.getValue();
        Dice player = player.getSelectedDice();
        for (int i = 0; i < dragonParts.length; i++){
            for (int j = 0; j < dragonParts[i].length && move.length < 2; j++){
                if (a == dragonParts [i][j] && dragonParts[i][j] != 0)
                    move =
                if (move.length < 2)
                break
            }
            if (move.length < 2)
                break
        }
        return move;
    }
    */
}
