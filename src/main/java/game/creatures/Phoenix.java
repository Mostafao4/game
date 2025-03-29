package game.creatures;
import game.dice.*;
<<<<<<< HEAD
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
=======
import game.engine.*;
import game.collectibles.*;
import game.exceptions.InvalidDiceSelectionException;
import game.exceptions.InvalidMoveException;
// import game.exceptions.*;
import java.io.*;
import java.util.Properties;
public class Phoenix extends Creature {
    
    private int count;
    private int score_int;
    private int[] attack;
    //private Reward[] rewards;
    private String[] rew = new String[11];
    public Phoenix(){
        attack = new int[11];
        //rewards = new Reward[11];
        count = 0;
        editReward();
    }
    public boolean makeMove(Move move) throws InvalidMoveException, InvalidDiceSelectionException {
        if(count>=11){
            throw new InvalidMoveException("You have reached the maximum number of attacks");
        }
        else if(count==0 || move.getDice().getValue()>attack[count-1] || attack[count-1]==6){
            attack[count++] = move.getDice().getValue();
            score_int += move.getDice().getValue();
            System.out.println("You have successfully attacked the Majestic Phoenix!");
            return true;
        }
        else{
            throw new InvalidDiceSelectionException("Dice value must be greater than previous attack");
        }
    }

//creates array rew type string from config for rewards
public void editReward(){
    Properties prop = new Properties();
    try{

        FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/MysticalSkyRewards.properties");
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
                case "EssenceBonus":
                    rew[i]="EB";
                    break;
                default:
                    rew[i]="  ";
                    break;
            }
        }
    }
    catch (IOException ex) {
        ex.printStackTrace();
>>>>>>> a661c1e9a7a78cf04911d94f4cd0a4b481b78356
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
<<<<<<< HEAD
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
=======
    // checks rewards and returns the corresponding reward in an array & makes used reward an x
    public Reward[] checkReward(){
        if(count==0)
            return new Reward[0];
        switch (rew[count-1]){
            case "AB":
                rew[count-1]="X ";
                return new Reward[]{new ArcaneBoost()};
            case "BB":
                rew[count-1]="X ";
                return new Reward[]{new Bonus(Realm.BLUE)};
            case "EC":
                rew[count-1]="X ";
                return new Reward[]{new ElementalCrest(Realm.YELLOW)};
            case "GB":
                rew[count-1]="X ";
                return new Reward[]{new Bonus(Realm.GREEN)};
            case "MB":
                rew[count-1]="X ";
                return new Reward[]{new Bonus(Realm.MAGENTA)};
            case "RB":
                rew[count-1]="X ";
                return new Reward[]{new Bonus(Realm.RED)};
            case "TW":
                rew[count-1]="X ";
                return new Reward[]{new TimeWarp()};
            case "YB":
                rew[count-1]="X ";
                return new Reward[]{new Bonus(Realm.YELLOW)};
            case "EB": 
                rew[count-1]="X ";
                return new Reward[]{new EssenceBonus()};
            default: return null;
        }
    }

    public int getMagentaRealmScore(){
        return score_int;
    }


    public Move[] getAllPossibleMoves(){
        Move[] moves;
        if(count>10){
            moves =  new Move[0];
        }
        else if (count==0 || attack[count-1]==6){
            moves = new Move[6];
            for(int i=0;i<6;i++){
                moves[i] = new Move(new MagentaDice(i),this);
            }
        }
        else{
            int j = 0;
            moves = new Move[6-attack[count-1]];
            for(int i=attack[count-1]+1;i<=6;i++){
                moves[j++] = new Move(new MagentaDice(i),this);
            }
        }
        return moves;
    }

    public Move[] getPossibleMovesForADie (Dice dice){
        if(count==0){
            return new Move[]{new Move(dice,this)};
        }
        if(count>10){
            return new Move[0];
        }
        if(dice.getValue() > attack[count-1] || attack[count-1] == 6){
            return new Move[]{new Move(dice,this)};
        }
       else{
           return new Move[0];
        }
    }
    public int getCount(){
        return count;
    }
/////partially done
    public String toString(){
        return
        "\nMystical Sky: Majestic Phoenix (MAGENTA REALM):\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  H  |"+attack[0]+"    |"+attack[1]+"    |"+attack[2]+"    |"+attack[3]+"    |"+attack[4]+"    |"+attack[5]+"    |"+attack[6]+"    |"+attack[7]+"    |"+attack[8]+"    |"+attack[9]+"    |"+attack[10]+"    |\n" +
        "|  C  |<    |<    |<    |<    |<    |<    |<    |<    |<    |<    |<    |\n" +
        "|  R  |"+rew[0]+"   |"+rew[1]+"   |"+rew[2]+"   |"+rew[3]+"   |"+rew[4]+"   |"+rew[5]+"   |"+rew[6]+"   |"+rew[7]+"   |"+rew[8]+"   |"+rew[9]+"   |"+rew[10]+"   |\n" +
        "+-----------------------------------------------------------------------+\n\n\n";
    }

>>>>>>> a661c1e9a7a78cf04911d94f4cd0a4b481b78356
}



<<<<<<< HEAD
}
=======

>>>>>>> a661c1e9a7a78cf04911d94f4cd0a4b481b78356
