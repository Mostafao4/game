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
    public Phoenix(MagentaDice dice ){
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
    public int getMagentaRealmScore(){
        return score_int;
    }
    



public Move[] getAllPossibleMoves(){
    Move[] possibleMoves = new Move[6];
    int lastNumber= (int)attack[count];
    if(lastNumber==6){
        for(int j;j<6;j++){
            possibleMoves[j]=new Move(new MagentaDice(j+1),new Phoenix(dice));
        }
        return possibleMoves;
    }
    for(int i;lastNumber<6;i++){
        lastNumber+=1;
        possibleMoves[i]=new Move(new MagentaDice(lastNumber),new Phoenix(dice));
    }
    return possibleMoves;

}
// public Move[] getPossibleMovesForADie(Dice die){
    
// }
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



}
