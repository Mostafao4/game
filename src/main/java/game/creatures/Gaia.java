package game.creatures;

import game.dice.*;
import game.collectibles.*;
import game.exceptions.*;

public class Gaia extends Creature{
    private int defeatedGaias;
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
    }

    public void attack(Dice first, Dice second) throws InvalidDiceSelectionException{
        if(!(first instanceof GreenDice) || !(second instanceof ArcanePrism)){
            throw new InvalidDiceSelectionException("Wrong Dice Selection");
        } else {
            for(int i = 0; i < gaias.length;i++){
                for(int j = 0; j < gaias[i].length;j++){
                    if(gaias[i][j] != ((GreenDice)first).getValue() + ((ArcanePrism)second).getValue()){
                        gaias[i][j] = 0;
                        defeatedGaias++;
                        break;
                    }
                }
            }
        }
        editBonuses();
        showAvailableBonuses();
    }

    public void editBonuses(){
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

    public void showAvailableBonuses(){
        showHorizontalBonus();
        showVerticalBonus();
    }

    public void showHorizontalBonus(){
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

    public void showVerticalBonus(){
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

    public int getPoints(){
        return scores[defeatedGaias - 1];
    }

    public int defeatedGaias(){
        return defeatedGaias;
    }

    public int aliveGaias(){
        return 11 - defeatedGaias;
    }

}
