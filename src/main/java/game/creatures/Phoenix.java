package game.creatures;
import game.dice.*;
import game.engine.Move;
import game.collectibles.*;
public class Phoenix extends Creature {
    
    private int count=0;
    private MagentaDice dice;
    private int[] attack;
    private Reward[] rewards;
    private int score_int;
    private int[] score_array;
    public Phoenix(MagentaDice dice ,Reward[] rewards){
        this.dice=dice;
        this.attack=new int[11];
        this.rewards=new Reward[11];
        this.score_array=new int[11];
        
    }
    public void Attack() {
    if(count>=11){System.out.println("You reached the maximum possible times of plays"); return;}
    if(count==0){   
            attack[count]=(int)dice.getValue();
            score_int+=(int)dice.getValue();
            score_array[count]=(int)dice.getValue();
            count+=1;
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
        else{System.out.println("Dice value is less than last chosen value");  return;}



    }
    }
    public Reward Rewards(int index ){
       return  rewards[index];
    }
    public int getPoints(){
        return score_int;
    }


//Move[] getAllPossibleMoves
//Move[] getPossibleMovesForADie
//getMagentaRealmScore();
Dice dice = new Dice(2);
array[i] = new Move(dice,Phoenix);


}