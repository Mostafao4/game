package game.creatures;

import game.dice.*;
//import game.collectibles.*;
//import game.exceptions.*;

public class Gaia extends Creature{
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

    private boolean[] verticalBonus = {false,false,false,false};
    private boolean[] horizontalBonus = {false,false,false};
    private int[] scores = {1, 2, 4, 7, 11, 16, 22, 29, 37, 46, 56};


    public Gaia(){
        this.defeatedGaias = 0;
        this.totalGaias = 11;
    }

    public void attack(Dice first, Dice second) {//throws InvalidDiceSelectionException{
        // if(!(first instanceof GreenDice) || !(second instanceof ArcanePrism) ||
        //      !(second instanceof GreenDice) || !(first instanceof ArcanePrism)){
        //     throw new InvalidDiceSelectionException("Wrong Dice Selection!");
        // } else {
            int total = first.getValue() + second.getValue();
            for(int i = 0; i < gaias.length;i++){
                for(int j = 0; j < gaias[i].length;j++){
                    if(gaias[i][j] == total){
                        gaias[i][j] = 0;
                        defeatedGaias++;
                        break;
                    }
                }
            }
        // }
        editRewards();
        //showAvailableRewards();
        useImmediateBonus();
    }

    public void editRewards(){
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

    public void showHorizontalRewards(){
        for(int i = 0; i < horizontalBonus.length; i++){
            if(horizontalBonus[i]){
                switch (i) {
                    case 0:
                        System.out.println("Yellow Bonus \n");
                    case 1:
                        System.out.println("Red Bonus \n");
                    case 2:
                        System.out.println("Elemental Crest \n");
                        break;
                    default:
                        break;
                }
            }
            horizontalBonus[i] = false;
        }
    }

    public void showVerticalRewards(){
        for(int i = 0; i < verticalBonus.length; i++){
            if(verticalBonus[i]){
                switch (i) {
                    case 0:
                        System.out.println("Time Warp Power \n");
                    case 1:
                        System.out.println("Blue Bonus \n");
                    case 2:
                        System.out.println("Magenta Bonus \n");
                    case 3:
                        System.out.println("Arcane Boost Power \n");
                        break;
                    default:
                        break;
                }
            }
            horizontalBonus[i] = false;
        }
    }

    public void useImmediateBonus(){
        System.out.println(
            horizontalBonus[0]?"Use the yellow bonus!": //TODO: Make it false after it is used.
            horizontalBonus[1]?"Use the red bonus!":
            verticalBonus[1]?"Use the blue bonus!":
            verticalBonus[2]?"Use the magenta bonus!":
            "No immediate bonuses available!"
        );
    }

    public void useRewards(){
        for(boolean f:horizontalBonus){
            if(f)
                System.out.println("Use the reward!");
        }
    }

    public int getPoints(){
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
        g.attack(die1, die2);
        g.attack(die7, die8);
        g.attack(die3, die4);
        g.scoreSheet();
        g.attack(die5, die6);
        g.scoreSheet();
    }
}
