package game.creatures;
import game.dice.*;
import game.engine.*;
import game.collectibles.*;
import game.exceptions.*;
import java.io.*;
import java.util.Properties;
public class Phoenix extends Creature {
    
    private int count=0;
    private int[] attack;
    private Reward[] rewards;
    private int score_int;
    private int[] score_array;
    private int player;
    public Phoenix(){
        // this.player=numberOfPlayer;
        this.attack=new int[11];
        this.rewards=new Reward[11];
        this.score_array=new int[11];

        
    }
    public void Attack(MagentaDice dice) {
    if(count>=11){System.out.println("You reached the maximum possible times of plays"); return;}
    if(count==0){   
            attack[count]=(int)dice.getValue();
            score_int+=(int)dice.getValue();
            score_array[count]=(int)dice.getValue();
            count++;
    }
    if(count!=0){
        if((int)dice.getValue()>(int)attack[count]){
            
            attack[count+1]=(int)dice.getValue();
            score_int+=(int)dice.getValue();
            score_array[count]=(int)dice.getValue();
            count+=1;
        }
        if((int)attack[count]==6){
            attack[count+1]=(int)dice.getValue();
            count+=1;
            score_int+=(int)dice.getValue();
        }
        else{System.out.println("Dice value is not more than the last chosen value");  return;}

    }
    }
    //////Done

    public Reward Rewards(int index ){
       return  rewards[index];
    }
    ////////Process

    public int getMagentaRealmScore(){
        return score_int;
    }
    ///////Done
    



public Move[] getAllPossibleMoves(){
    Move[] possibleMoves = new Move[6];
    int lastNumber= (int)attack[count];
    // Dice Temp_dice=new MagentaDice(0);
    if(lastNumber==6){
        for(int j=0; j<6;j++){
            // Temp_dice.setValue(j+1);
            possibleMoves[j]=new Move(new MagentaDice(j+1),Realm.MAGENTA);
        } 
        return possibleMoves;
    }
    else{
    for(int i=0;lastNumber<=6;i++){
        // Temp_dice.setValue(lastNumber);
        possibleMoves[i]=new Move(new MagentaDice(lastNumber),Realm.MAGENTA);
        lastNumber+=1;
    }
    return possibleMoves;
}

}
///////Done

// public Move[] getPossibleMovesForADie (Player player, Dice dice){
    
// }
/////process
public String scoreSheet_method(){
    return  "    Mystical Sky: Majestic Phoenix (MAGENTA REALM):\n" +
    "            +-----------------------------------------------------------------------+\n" +
    "            |  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
    "            +-----------------------------------------------------------------------+\n" +
    "            |  H  |"+attack[0]+"|"+attack[1]+" |"+attack[2]+"|"+attack[3]+"|"+attack[4]+"|"+attack[5]+"|"+attack[6]+"|"+attack[7]+"|"+attack[8]+"|"+attack[9]+"|"+attack[10]+"|\n" +
    "            |  C  |<    |<    |<    |<    |<    |<    |<    |<    |<    |<    |<    |\n" +
    "            |  R  |     |     |TW   |GB   |AB   |RB   |EC   |TW   |BB   |YB   |AB   |\n" +
    "            +-----------------------------------------------------------------------+\n";
}
//process


}
//
