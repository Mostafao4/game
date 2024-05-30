package game.creatures;
import java.io.FileInputStream;
import java.util.*;
import game.collectibles.*;
import game.dice.Dice;
import game.dice.DiceStatus;
import game.dice.YellowDice;
import game.engine.Move;
import game.engine.Player;
import game.exceptions.InvalidMoveException;

import java.io.IOException;

public class Lion extends Creature{
   


    private int hitNum=-1;
    private int[] diceNum={0,0,0,0,0,0,0,0,0,0,0};
    private int [] multipliers={0,0,0,0,0,0,0,0,0,0,0};
    private int totalScore=0; 
    private String[] rew=new String[11]; 
    private String[] mult=new String[11];
    //creates string for rewards & multipliers
    public Lion( ) {
        editReward();
        editMultipliers();
    }
 //creates array rew type string from config for rewards
public void editReward(){
        Properties prop = new Properties();
    try{

        FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/RadiantSvannaRewards.properties");
        prop.load(rewardConfig);
        
        for (int i=0;i<11;i++){
            String r=prop.getProperty("hit"+(i+1)+"Reward");
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
}
//creates array mult type string from config for multipliers and edits multiplier array type int
public void editMultipliers(){
        Properties prop = new Properties();
    try{

        FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/RadiantSvannaMultipliers.properties");
        prop.load(rewardConfig);
        for (int i=0;i<11;i++){
            String m=prop.getProperty("hit"+(i+1)+"multiplier");
            mult[i]=m;
        } 
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }
    for (int i=0;i<11;i++){

            multipliers[i]=Integer.parseInt(mult[i]);
            mult[i]="x"+mult[i];
            if (multipliers[i]==1){
                mult[i]="  ";
        }


    }
}


//increments hitnum(counter) & adds dice value to array diceNum 
public boolean makeMove(Move move) {
    if (hitNum<10){
        hitNum++;
        diceNum[hitNum]=move.getDice().getValue();
        System.out.println("You have succesfully attacked the Solar Lion!");
        return true;}
    else
    return false;    
   
}


//returns total score of lion class
public int getYellowRealmScore(){
    int score = 0;
    for(int i = 0; i < diceNum.length; i++){
        score+= (multipliers[i] * diceNum[i]);
    }

    return score;
}

 // checks rewards and returns the supposed reward & makes used reward an x
public Reward[] checkReward(){

    switch (rew[hitNum]){
    case "AB":
            rew[hitNum]="X ";
            return new Reward[]{new ArcaneBoost()};
    case "BB": 
            rew[hitNum]="X ";
            return new Reward[]{new Bonus(Realm.BLUE)};
    case "EC": 
            rew[hitNum]="X ";
            return new Reward[]{new ElementalCrest(Realm.YELLOW)};
    case "GB":
            rew[hitNum]="X ";
            return new Reward[]{new Bonus(Realm.GREEN)};
    case "MB": 
            rew[hitNum]="X ";
            return new Reward[]{new Bonus(Realm.MAGENTA)};
    case "RB": 
            rew[hitNum]="X ";
            return new Reward[]{new Bonus(Realm.RED)};
    case "TW":
            rew[hitNum]="X ";
            return new Reward[]{new TimeWarp()};
    case "YB": 
            rew[hitNum]="X ";
            return new Reward[]{new Bonus(Realm.YELLOW)};
    default: return null;
    }
 }
 // returns an array type move with all the possible moves before rolling the dice
 public Move[] getAllPossibleMoves(){
    if (hitNum<11){
    Move[] pm1=new Move[6];
    for(int i=0;i<pm1.length;i++){
        pm1[i]=new Move(new YellowDice(i+1),this);
    }
    return pm1;
}
    else{
    Move[] pm2=new Move[0];
    return pm2;
    }
 }
 //returns possible moves when dice is rolled (dicenumber)
 public Move[] getPossibleMovesForADie(Dice dice){
        if (hitNum<11){
            Move[] pmd=new Move[1];
            pmd[0]=new Move(dice,this);
            return pmd;
        }
        else{
            Move[] pm2=new Move[0];
            return pm2;
        }
 }
//returns the scoresheet  
 public String toString(){
    
    return "Radiant Savanna: Solar Lion (YELLOW REALM):\n" +
    "+-----------------------------------------------------------------------+\n" +
    "|  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
    "+-----------------------------------------------------------------------+\n" +
    "|  H  |"+diceNum[0]*multipliers[0]+( diceNum[0]*multipliers[0]>9?"   |":"    |")+diceNum[1]*multipliers[1]+( diceNum[1]*multipliers[1]>9?"   |":"    |")+diceNum[2]*multipliers[2]+( diceNum[2]*multipliers[2]>9?"   |":"    |")+diceNum[3]*multipliers[3]+( diceNum[3]*multipliers[3]>9?"   |":"    |")+diceNum[4]*multipliers[4]+( diceNum[4]*multipliers[4]>9?"   |":"    |")+diceNum[5]*multipliers[5]+( diceNum[5]*multipliers[5]>9?"   |":"    |")+diceNum[6]*multipliers[6]+( diceNum[6]*multipliers[6]>9?"   |":"    |")+diceNum[7]*multipliers[7]+( diceNum[7]*multipliers[7]>9?"   |":"    |")+diceNum[8]*multipliers[8]+( diceNum[8]*multipliers[8]>9?"   |":"    |")+diceNum[9]*multipliers[9]+( diceNum[9]*multipliers[9]>9?"   |":"    |")+diceNum[10]*multipliers[10]+( diceNum[10]*multipliers[10]>9?"   |\n":"    |\n") +
    "|  M  |"+mult[0]+"   |"+mult[1]+"   |"+mult[2]+"   |"+mult[3]+"   |"+mult[4]+"   |"+mult[5]+"   |"+mult[6]+"   |"+mult[7]+"   |"+mult[8]+"   |"+mult[9]+"   |"+mult[10]+"   |\n" +
    "|  R  |"+rew[0]+"   |"+rew[1]+"   |"+rew[2]+"   |"+rew[3]+"   |"+rew[4]+"   |"+rew[5]+"   |"+rew[6]+"   |"+rew[7]+"   |"+rew[8]+"   |"+rew[9]+"   |"+rew[10]+"   |\n" +
    "+-----------------------------------------------------------------------+\n\n";

 }
 
}

