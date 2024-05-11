package game.creatures;
import java.util.*;
import game.collectibles.*;
import game.engine.Move;
import game.engine.Player;
public class Lion {
   


    private static int hitNum=-1;
    private static int[] diceNum={0,0,0,0,0,0,0,0,0,0,0};
    private  int dice;
    private int [] multipliers={1,1,1,2,1,1,2,1,2,1,3};
    private int score;
    private static int totalScore=0; 
    private String[] rewards= {"" , "", "TimeWarp","" , "RedBonus", "ArcaneBoost","" , "ElementalCrest", "",  "MagnetaBonus",  ""}; ;
    private Move[] x =new Move[1];

    public Lion( int dice) {
        
        this.dice = dice;  
    }

public static void move(){
    hitNum++;
    diceNum[hitNum]=dice;
}
public void attack(){
int score= multiplyScore(hitNum,diceNum, multipliers);
getScore( score, totalScore);
}

public static int multiplyScore(int hitNum,int diceNum[],int[] multipliers){
    int score=multipliers[hitNum]*diceNum[hitNum];
     return score;
 }
 public int getScore(int score, int totalScore){
     totalScore+=score;
     return totalScore;
 }
public String getReward(int hitNum, String[] rewards){
    return rewards[hitNum];
 }
 public Move[] getAllPossibleMoves(){
    if (hitNum<11)
    this.x[0]=6;
    else
     x[0]=1;
     return x;

 }
 public String getsScoreSheet(){

   return "Radiant Savanna: Solar Lion (YELLOW REALM):\n" +
    "+-----------------------------------------------------------------------+\n" +
    "|  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
    "+-----------------------------------------------------------------------+\n" +
    "|  H  |"+diceNum[0]*multipliers[0]+"    |"+diceNum[1]*multipliers[1]+"    |"+diceNum[2]*multipliers[2]+"    |"+diceNum[3]*multipliers[3]+"    |"+diceNum[4]*multipliers[4]+"    |"+diceNum[5]*multipliers[5]+"    |"+diceNum[6]*multipliers[6]+"    |"+diceNum[7]*multipliers[7]+"    |"+diceNum[8]*multipliers[8]+"    |"+diceNum[9]*multipliers[9]+"    |"+diceNum[10]*multipliers[10]+"    |\n" +
    "|  M  |     |     |     |x2   |     |     |x2   |     |x2   |     |x3   |\n" +
    "|  R  |     |     |TW   |     |RB   |AB   |     |EC   |     |MB   |     |\n" +
    "+-----------------------------------------------------------------------+\n\n";

 }
 
 }

