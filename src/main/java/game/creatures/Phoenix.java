package game.creatures;
import game.dice.*;
import game.engine.*;
import game.collectibles.*;
// import game.exceptions.*;
import java.io.*;
import java.util.Properties;
public class Phoenix extends Creature {
    
    private int count=0;
    private int[] attack;
    private Reward[] rewards;
    private int score_int;
    private int[] score_array;
    // private int player;
    public Phoenix(){
        // this.player=numberOfPlayer;
        this.attack=new int[11];
        this.rewards=new Reward[11];
        this.score_array=new int[11];

        
    }
    public void makeMove(Move move) {
    if(count>=11){System.out.println("You reached the maximum possible times of plays"); return;}
    if(count==0){   
            attack[count]=(int)move.getDice().getValue();
            score_int+=(int)move.getDice().getValue();
            score_array[count]=(int)move.getDice().getValue();
            count++;
            rewards[count-1]= getReward();
            
            
    }
    else{
        if((int)move.getDice().getValue()>(int)attack[count]){
            
            attack[count+1]=(int)move.getDice().getValue();
            score_int+=(int)move.getDice().getValue();
            score_array[count]=(int)move.getDice().getValue();
            count++;
            rewards[count-1]= getReward();
            
            
        }
        if((int)attack[count]==6){
            attack[count+1]=(int)move.getDice().getValue();
            score_int+=(int)move.getDice().getValue();
            count+=1;
            rewards[count-1]= getReward();
            
        }
        else{System.out.println("Dice value is not more than the last chosen value");  return;}

    }
    }
    //////Done

    public Reward getRewards(int index ){
       return  rewards[index];
    }
    private Reward rewardFromString(String N){
        switch (N) {
            case "TimeWarp": return new TimeWarp(); 
            case "GreenBonus": return new Bonus(Realm.GREEN);
            case "ArcaneBoost": return new ArcaneBoost();
            case "RedBonus": return new Bonus(Realm.RED);
            case "ElementalCrest": return new ElementalCrest(Realm.MAGENTA);
            case "BlueBonus":return new Bonus(Realm.BLUE);
            case "YellowBonus": return new Bonus(Realm.YELLOW);
            default: return null;

        }
    }
    private Reward getReward(){
        Properties prop= new Properties();
        int switching=count;
        String reward_String;
        try {
            prop.load(Phoenix.class.getClassLoader().getResourceAsStream("MysticalSkyRewards.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch (switching) {
            case 1:reward_String=prop.getProperty("hit1Reward"); return rewardFromString(reward_String);
            case 2:reward_String=prop.getProperty("hit2Reward");return rewardFromString(reward_String);
            case 3:reward_String=prop.getProperty("hit3Reward");return rewardFromString(reward_String);
            case 4:reward_String=prop.getProperty("hit4Reward");return rewardFromString(reward_String);
            case 5:reward_String=prop.getProperty("hit5Reward");return rewardFromString(reward_String);
            case 6:reward_String=prop.getProperty("hit6Reward");return rewardFromString(reward_String);
            case 7:reward_String=prop.getProperty("hit7Reward");return rewardFromString(reward_String);
            case 8:reward_String=prop.getProperty("hit8Reward");return rewardFromString(reward_String);
            case 9:reward_String=prop.getProperty("hit9Reward");return rewardFromString(reward_String);
            case 10:reward_String=prop.getProperty("hit10Reward");return rewardFromString(reward_String);
            case 11:reward_String=prop.getProperty("hit11Reward");return rewardFromString(reward_String);
            default: return null;

        }
    }
    ////////Done

    public int getMagentaRealmScore(){
        return score_int;
    }
    ///////Done
    



public Move[] getAllPossibleMoves(){
    Move[] possibleMoves = new Move[6];
    int lastNumber= (int)attack[count];
    // Dice Temp_dice=new MagentaDice(0);
    if((lastNumber)==6){
        for(int j=0; j<6;j++){
            // Temp_dice.setValue(j+1);
            possibleMoves[j]=new Move(new MagentaDice(j+1),this);
        } 
        return possibleMoves;
    }
    else{
    for(int i=0;(lastNumber)<=6;i++){
        // Temp_dice.setValue((lastNumber));
        possibleMoves[i]=new Move(new MagentaDice((lastNumber)),this);
        (lastNumber)+=1;
    }
    return possibleMoves;
}

}
///////Done

public Move[] getPossibleMovesForADie (Dice dice){
    Move[] possibleMoves = new Move[6];
    if(dice.getValue()==6){
        for(int j=0; j<6;j++){
            // Temp_dice.setValue(j+1);
            possibleMoves[j]=new Move(new MagentaDice(j+1),this);
        } 
        return possibleMoves;
    }
    else{
        for(int i=0;(int)dice.getValue()<=6;i++){
            // Temp_dice.setValue((lastNumber));
            possibleMoves[i]=new Move(new MagentaDice((dice.getValue())),this);
            dice.setValue(dice.getValue()+1);
        }
        return possibleMoves;
    }

}
/////partially done
public String scoreSheet(){
    return  "    Mystical Sky: Majestic Phoenix (MAGENTA REALM):\n" +
    "            +-----------------------------------------------------------------------+\n" +
    "            |  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
    "            +-----------------------------------------------------------------------+\n" +
    "            |  H  |"+attack[0]+"|"+attack[1]+" |"+attack[2]+"|"+attack[3]+"|"+attack[4]+"|"+attack[5]+"|"+attack[6]+"|"+attack[7]+"|"+attack[8]+"|"+attack[9]+"|"+attack[10]+"|\n" +
    "            |  C  |<    |<    |<    |<    |<    |<    |<    |<    |<    |<    |<    |\n" +
    "            |  R  |     |     |"+scoreSheet_helper(rewards,2,"TW")+"|"+scoreSheet_helper(rewards,2,"GB")+"|"+scoreSheet_helper(rewards,2,"AB")+"|"+scoreSheet_helper(rewards,2,"RB")+"  |"+scoreSheet_helper(rewards,2,"EC")+" |"+scoreSheet_helper(rewards,2,"TW")+" |"+scoreSheet_helper(rewards,2,"BB")+"   |"+scoreSheet_helper(rewards,2,"YB")+" |"+scoreSheet_helper(rewards,2,"AB")+"|\n" +
    "            +-----------------------------------------------------------------------+\n";
}
private String scoreSheet_helper(Reward[] rewards,int index, String s){
    if(rewards[index]!=null)
        return "X";
    else
        return s;
    }
}




