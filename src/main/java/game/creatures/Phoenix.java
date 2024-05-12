package game.creatures;
import game.dice.*;
import game.collectibles.*;

public class Phoenix extends Creature {
    
    int count=0;
    MagentaDice dice;
    int[] attack;
    Reward[] rewards;
    int score;

    public Phoenix(MagentaDice dice, Reward[] rewards){
        this.dice=dice;
        this.attack=new int[11];
        this.rewards=rewards;
        
    }

    public int Attack() {
        if((int)dice.getValue()>(int)attack[count]){
            attack[count+1]=(int)dice.getValue();
            count+=1;
            score+=(int)dice.getValue();
        }
        else if((int)attack[count]==6){
            attack[count+1]=(int)dice.getValue();
            count+=1;
            score+=(int)dice.getValue();
        }

    
    }
    public static Reward Rewards(int index ){
       return  rewards[index];
    }
    public static int getPoints(){
        return score;
    }
}
