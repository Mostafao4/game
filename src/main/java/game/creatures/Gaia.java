package game.creatures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import game.dice.*;
//import game.collectibles.*;
import game.exceptions.*;

public class Gaia extends Creature{

    InputStream inputStream;
    private String[] rowRewards;
    private String[] columnRewards;
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
        try {
            Properties prop = new Properties();
            String propFileName = "src/main/resources/config/TerrasHeartlandRewards.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            rowRewards = new String[]{prop.getProperty("row1Reward"),prop.getProperty("row2Reward"),prop.getProperty("row3Reward")};
            columnRewards = new String[]{prop.getProperty("column1Reward"),prop.getProperty("column2Reward"),prop.getProperty("column3Reward"),prop.getProperty("column3Reward")};
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }// } finally {
        //     inputStream.close();
        // }
        try {
            Properties prop = new Properties();
            String propFileName = "src/main/resources/config/TerrasHeartland.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            String sco = prop.getProperty("score");
            scores = Arrays.stream(sco.split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }// } finally {
        //     inputStream.close();
        // }
        // Properties prop = new Properties();
        // String fileName = "TerrasHeartlandRewards.properties";
        // String fileName2 = "TerrasHeartland.properties";
        // try (FileInputStream fis = new FileInputStream(fileName)) {
        //     prop.load(fis);
        //     rowRewards = new String[]{prop.getProperty("row1Reward"),prop.getProperty("row2Reward"),prop.getProperty("row3Reward")};
        //     columnRewards = new String[]{prop.getProperty("column1Reward"),prop.getProperty("column2Reward"),prop.getProperty("column3Reward"),prop.getProperty("column3Reward")};
        // } 
        // catch (FileNotFoundException e) {
        //     System.out.println("Config file was not found!"); // FileNotFoundException catch is optional and can be collapsed
        // } catch (IOException ex) {
        //     System.out.println(ex.getMessage());
        // }
        // try (FileInputStream fis2 = new FileInputStream(fileName2)){
        //     prop.load(fis2);
        //     String sco = prop.getProperty("score");
        //     scores = Arrays.stream(sco.split(","))
        //     .mapToInt(Integer::parseInt)
        //     .toArray();
        // }
        // catch (FileNotFoundException e) {
        //     System.out.println("Config file was not found!"); // FileNotFoundException catch is optional and can be collapsed
        // } catch (IOException ex) {
        //     System.out.println(ex.getMessage());
        // }
    }

    public void attackGaia(Dice first, Dice second) {//throws InvalidDiceSelectionException{
        // if(!(first instanceof GreenDice) || !(second instanceof ArcanePrism) ||
        //      !(second instanceof GreenDice) || !(first instanceof ArcanePrism)){
        //     throw new InvalidDiceSelectionException("Wrong Dice Selection!");
        // } else {
            boolean flag = false;
            int total = first.getValue() + second.getValue();
            for(int i = 0; i < gaias.length && !flag;i++){
                for(int j = 0; j < gaias[i].length && !flag;j++){
                    if(gaias[i][j] == total){
                        gaias[i][j] = 0;
                        defeatedGaias++;
                        flag = true;
                    }
                }
            }
            if(!flag){
                System.out.println("No valid moves!");
                return;
            }

        // }
        editRewards();
        // showAvailableRewards();
        useImmediateBonus();
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

    private void showHorizontalRewards(){
        for(int i = 0; i < horizontalBonus.length; i++){
            if(horizontalBonus[i]){
                switch (i) {
                    case 0:
                        System.out.println(rowRewards[0]);
                    case 1:
                        System.out.println(rowRewards[1]);
                    case 2:
                        System.out.println(rowRewards[2]);
                    default:
                        break;
                }
            }
            horizontalBonus[i] = false;
        }
    }

    private void showVerticalRewards(){
        for(int i = 0; i < verticalBonus.length; i++){
            if(verticalBonus[i]){
                switch (i) {
                    case 0:
                        System.out.println(columnRewards[0]);
                    case 1:
                        System.out.println(columnRewards[1]);
                    case 2:
                        System.out.println(columnRewards[2]);
                    case 3:
                        System.out.println(columnRewards[3]);
                    default:
                        break;
                }
            }
            horizontalBonus[i] = false;
        }
    }

    private void useImmediateBonus(){
        useImmediateBonusHelper();
    }

    private void useImmediateBonusHelper(){
        if(horizontalBonus[0]){
            System.out.println("Use the yellow bonus! ");
            horizontalBonus[0] = false;
        }
        else if(horizontalBonus[1]){
            System.out.println("Use the red bonus!");
            horizontalBonus[1] = false;
        }
        else if(verticalBonus[1]){
            System.out.println("Use the blue bonus!");
            verticalBonus[1] = false;
        }
        else if(verticalBonus[2]){
            System.out.println("Use the magenta bonus");
            verticalBonus[2] = false;
        }
        else
            System.out.println("No immediate bonuses available!");
    }

    private void useRewards(){
        for(boolean f:horizontalBonus){
            if(f)
                System.out.println("Use the reward!");
        }
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
        System.out.println("Terra's Heartland: Gaia Guardians (GREEN REALM): \n"
        + "+-----------------------------------+ \n"
        + "|  #  |1    |2    |3    |4    |R    | \n"
        + "+-----------------------------------+ \n"
        // + "|  1  |X    |2    |3    |4    |YB   | \n"
        + "|  1  " + scoreSheetHelper(0) + "|" + (horizontalBonus[0]?"X":"YB") + "    |\n"
        // + "|  2  |5    |6    |7    |8    |RB   | \n"
        + "|  2  " + scoreSheetHelper(1) + "|" + (horizontalBonus[1]?"X":"RB") + "   |\n"
        // + "|  3  |9    |10   |11   |12   |EC   | \n"
        + "|  3  " + scoreSheetHelper(2) + "|" + (horizontalBonus[2]?"X":"EC") + "   |\n"
        + "+-----------------------------------+ \n"
        + "|  R  |TW   |BB   |MB   |AB   |     | \n"
        + "+-----------------------------------------------------------------------------+ \n"
        + "|  S  |0    |1    |2    |4    |7    |11   |16   |22   |29   |37   |46   |56   | \n"
        + "+-----------------------------------------------------------------------------+");
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

    public void getPropValues() throws IOException {
        // try {
        //     Properties prop = new Properties();
        //     String propFileName = "TerrasHeartlandRewards.properties";
        //     inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        //     if (inputStream != null) {
        //         prop.load(inputStream);
        //     } else {
        //         throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        //     }
        //     rowRewards = new String[]{prop.getProperty("row1Reward"),prop.getProperty("row2Reward"),prop.getProperty("row3Reward")};
        //     columnRewards = new String[]{prop.getProperty("column1Reward"),prop.getProperty("column2Reward"),prop.getProperty("column3Reward"),prop.getProperty("column3Reward")};
        // } catch (IOException e) {
        //     System.out.println("Exception: " + e);
        // } finally {
        //     inputStream.close();
        // }
        // try {
        //     Properties prop = new Properties();
        //     String propFileName = "TerrasHeartland.properties";
        //     inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        //     if (inputStream != null) {
        //         prop.load(inputStream);
        //     } else {
        //         throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        //     }
        //     String sco = prop.getProperty("score");
        //     scores = Arrays.stream(sco.split(","))
        //     .mapToInt(Integer::parseInt)
        //     .toArray();
        // } catch (IOException e) {
        //     System.out.println("Exception: " + e);
        // } finally {
        //     inputStream.close();
        // }
    }

    public static void main(String[] args){
        Gaia g = new Gaia();
        GreenDice die1 = new GreenDice(4);
        ArcanePrism die2 = new ArcanePrism(3);
        GreenDice die3 = new GreenDice(1);
        ArcanePrism die4 = new ArcanePrism(1);
        GreenDice die5 = new GreenDice(2);
        ArcanePrism die6 = new ArcanePrism(1);
        GreenDice die7 = new GreenDice(3);
        ArcanePrism die8 = new ArcanePrism(1);
        GreenDice die9 = new GreenDice(3);
        ArcanePrism die10 = new ArcanePrism(1);
        g.attackGaia(die1, die2);
        g.attackGaia(die7, die8);
        g.attackGaia(die3, die4);
        g.scoreSheet();
        g.attackGaia(die5, die6);
        g.attackGaia(die9, die10);
        g.scoreSheet();
    }
}
