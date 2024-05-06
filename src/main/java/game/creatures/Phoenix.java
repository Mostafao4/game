package game.creatures;
import game.dice.*;
import game.collectibles.*;

public class Phoenix extends Creature {
    int health;
    int count=0;
    MagentaDice dice;
    int[] attack;
    Reward[] rewards;
    int score;

    public Phoenix(int health,MagentDice dice,int[] attack, Reward[] rewards){
        this.health=health;
        this.dice=dice;
        this.attack=attack;
        this.rewards=rewards;
        
    }

    public int Attack() {
        if((int)dice.getValue()>(int)attack[count]){
            attack[count+1]=(int)dice.getValue();
            count+=1;
            score+=(int)dice.getValue()+ this.Rewards(count);
        }
        else if((int)attack[count]==6){
            attack[count+1]=(int)dice.getValue();
            count+=1;
            score+=(int)dice.getValue()+this.Rewards(count);
        }

    
    }
    public static collectibles Rewards(int index ){
       return  rewards[index];
    }
    public static int getPoints(){
        return score;
    }
}
