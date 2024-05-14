package game.creatures;
import java.util.*;
import game.collectibles.*;
import game.dice.Dice;
import game.dice.DiceStatus;
import game.dice.YellowDice;
import game.engine.Move;
import game.engine.Player;
import java.io.IOException;

public class Lion extends Creature{
   


    private static int hitNum=-1;
    private static int[] diceNum={0,0,0,0,0,0,0,0,0,0,0};
    private static int [] multipliers={0,0,0,0,0,0,0,0,0,0,0};
    private static int totalScore=0; 
    private static String[] rew=new String[11]; 
    private static String[] mult=new String[11];
    public Lion( ) {
       
    }
 //creates array rew type string from config for rewards
public static void editReward(){
        Properties prop = new Properties();
    try{
        prop.load(Lion.class.getClassLoader().getResourceAsStream("RadiantSvannaRewards.Properties"));
        
        for (int i=0;i<11;i++){
            String r=prop.getProperty("hit"+i+1+"Reward");
            rew[i]=r;
        } 
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }
}
//creates array mult type string from config for multipliers and edits multiplier array type int
public static void editMultipliers(){
        Properties prop = new Properties();
    try{
        prop.load(Lion.class.getClassLoader().getResourceAsStream("RadiantSvannaMultipliers.Properties"));
        
        for (int i=0;i<11;i++){
            String m=prop.getProperty("hit"+i+1+"multiplier");
            mult[i]=m;
        } 
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }
    for (int i=0;i<11;i++){
        if (mult[i]=="null"){
            multipliers[i]=1;
            mult[i]="";
        }
        else{
            multipliers[i]=Integer.parseInt(mult[i]);
            mult[i]="x"+mult[i];
        }
    }
}


//increments hitnum(counter) & adds dice value to array diceNum 
public static void move(YellowDice dice){
    hitNum++;
    diceNum[hitNum]=(int)dice.getValue();
}


//uses multiplyScore & getScore methods to return total score of lion class
public int getYellowRealmScore(){
    int score= multiplyScore(hitNum,diceNum, multipliers);
    return getScore( score, totalScore);
}
// gets the new score of the round after multiplying it
public static int multiplyScore(int hitNum,int diceNum[],int[] multipliers){
    int score=multipliers[hitNum]*diceNum[hitNum];
     return score;
 }
 //calculates total score
 public int getScore(int score, int totalScore){
     totalScore+=score;
     return totalScore;
 }
 // checks rewards and returns the supposed reward & makes used reward an x
public Reward getReward(){
    switch (rew[hitNum]){
    case "ArcaneBoost":
            rew[hitNum]="X";
            return ArcaneBoost;
    case "BlueBonus": 
            rew[hitNum]="X";
            return BlueBonus;
    case "ElementalCrest": 
            rew[hitNum]="X";
            return ElementalCrest;
    case "EssenceBonus":
            rew[hitNum]="X";
            return EssenceBonus;
    case "GreenBonus":
            rew[hitNum]="X";
            return GreenBonus;
    case "MagentaBonus": 
            rew[hitNum]="X";
            return MagentaBonus;
    case "RedBonus": 
            rew[hitNum]="X";
            return RedBonus;
    case "TimeWarp":
            rew[hitNum]="X";
            return TimeWarp;
    case "YellowBonus": 
            rew[hitNum]="X";
            return YellowBonus;
    default: return null;
    }
 }
 // returns an array type move with all the possible moves before rolling the dice
 public Move[] getAllPossibleMoves(){
    if (hitNum<11){
    Move[] pm1=new Move[6];
    for(int i=0;i<pm1.length;i++){
        pm1[i]=new Move(new YellowDice(i+1),Realm.YELLOW);
    }
    return pm1;
}
    else{
    Move[] pm2=new Move[0];
    return pm2;
    }
 }
 //returns possible moves when dice is rolled (dicenumber)
 public Move[] getPossibleMovesForADie(YellowDice dice){
    Move[] pmd=new Move[1];
    pmd[0]=new Move(dice,Realm.YELLOW);
    return pmd;
 }
//returns the scoresheet & creates string for rewards & multipliers
 public String getScoreSheet(){
    editReward();
    editMultipliers();
   return "Radiant Savanna: Solar Lion (YELLOW REALM):\n" +
    "+-----------------------------------------------------------------------+\n" +
    "|  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
    "+-----------------------------------------------------------------------+\n" +
    "|  H  |"+diceNum[0]*multipliers[0]+"    |"+diceNum[1]*multipliers[1]+"    |"+diceNum[2]*multipliers[2]+"    |"+diceNum[3]*multipliers[3]+"    |"+diceNum[4]*multipliers[4]+"    |"+diceNum[5]*multipliers[5]+"    |"+diceNum[6]*multipliers[6]+"    |"+diceNum[7]*multipliers[7]+"    |"+diceNum[8]*multipliers[8]+"    |"+diceNum[9]*multipliers[9]+"    |"+diceNum[10]*multipliers[10]+"    |\n" +
    "|  M  |"+mult[0]+     "|"+mult[1]+     "|"+mult[2]+     "|"+mult[3]+    "|"+mult[4]+     "|"+mult[5]+     "|"+mult[6]+    "|"+mult[7]+     "|"+mult[8]+     "|"+mult[9]+     "|"+mult[10]+    "|\n" +
    "|  R  |"+rew[0]+"    |"+rew[1]+"     |"+rew[2]+"     |"+rew[3]+"    |"+rew[4]+"   |"+rew[5]+"    |"+rew[6]+"    |"+rew[7]+"    |"+rew[8]+"    |"+rew[9]+"    |"+rew[10]+"   |\n" +
    "+-----------------------------------------------------------------------+\n\n";

 }
 
 }

