package game.creatures;
import game.dice.*;
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
    private Reward[] rewards;
    public Phoenix(){
        attack = new int[11];
        rewards = new Reward[11];
        getReward();
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

    //////Done

    public Reward[] checkReward(){
       return  new Reward[] {rewards[count]};
    }


    private void rewardFromString(String N){
        switch (N) {
            case "TimeWarp": rewards[count-1]=new TimeWarp();break; 
            case "GreenBonus": rewards[count-1]=new Bonus(Realm.GREEN);break;
            case "ArcaneBoost": rewards[count-1]=new ArcaneBoost();break;
            case "RedBonus": rewards[count-1]=new Bonus(Realm.RED);break;
            case "ElementalCrest": rewards[count-1]= new ElementalCrest(Realm.MAGENTA);break;
            case "BlueBonus":rewards[count-1]=new Bonus(Realm.BLUE);break;
            case "YellowBonus": rewards[count-1]= new Bonus(Realm.YELLOW);break;

        }
    }
    private void getReward(){
        Properties prop= new Properties();
        String reward_String;
        try {
            FileInputStream scoreConfig = new FileInputStream("src/main/resources/config/MysticalSkyRewards.properties");
            prop.load(scoreConfig);
        
        switch (count) {
            case 1:reward_String=prop.getProperty("hit1Reward"); rewardFromString(reward_String);break;
            case 2:reward_String=prop.getProperty("hit2Reward"); rewardFromString(reward_String);break;
            case 3:reward_String=prop.getProperty("hit3Reward"); rewardFromString(reward_String);break;
            case 4:reward_String=prop.getProperty("hit4Reward"); rewardFromString(reward_String);break;
            case 5:reward_String=prop.getProperty("hit5Reward"); rewardFromString(reward_String);break;
            case 6:reward_String=prop.getProperty("hit6Reward"); rewardFromString(reward_String);break;
            case 7:reward_String=prop.getProperty("hit7Reward"); rewardFromString(reward_String);break;
            case 8:reward_String=prop.getProperty("hit8Reward"); rewardFromString(reward_String);break;
            case 9:reward_String=prop.getProperty("hit9Reward"); rewardFromString(reward_String);break;
            case 10:reward_String=prop.getProperty("hit10Reward"); rewardFromString(reward_String);break;
            case 11:reward_String=prop.getProperty("hit11Reward"); rewardFromString(reward_String);break;
            default:
        }
    }catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
    ////////Done

    public int getMagentaRealmScore(){
        return score_int;
    }
    ///////Done
    



public Move[] getAllPossibleMoves(){
    int lastNumber = attack[count];
    int last_test = attack[count];
    Move[] possibleMoves;
    if(lastNumber==6)
        possibleMoves = new Move[6];
    else{
        int length=0;
        while(last_test<6){
            length++;
            last_test++;
        }
        possibleMoves=new Move[length];
    }
    if(lastNumber==6){
        for(int j=0; j<6;j++){
            possibleMoves[j]=new Move(new MagentaDice(j+1),this);
        } 
        return possibleMoves;
    }
    else{
        for(int i=0;lastNumber<6;i++){
            possibleMoves[i]=new Move(new MagentaDice(lastNumber),this);
            lastNumber+=1;
    }
    return possibleMoves;
}

}
///////Done

public Move[] getPossibleMovesForADie (Dice dice){
    Move[] possibleMoves = new Move[1];
//    if(dice.getValue()==6){
//        for(int j=0; j<6;j++){
//            // Temp_dice.setValue(j+1);
//            possibleMoves[j]=new Move(new MagentaDice(j+1),this);
//        }
//        return possibleMoves;
//    }
//    else{
//        for(int i=0;(int)dice.getValue()<=6;i++){
//            // Temp_dice.setValue((lastNumber));
//            possibleMoves[i]=new Move(new MagentaDice((dice.getValue())),this);
//            dice.setValue(dice.getValue()+1);
//        }
//        return possibleMoves;
//    }
    if(dice.getValue() > attack[count] || dice.getValue() == 6){
        possibleMoves[0] = new Move(dice,this);
    }
    else {
        return new Move[0];
    }
    return possibleMoves;

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
    "|  R  |     |     |"+scoreSheet_helper(rewards,2,"TW")+"   |"+scoreSheet_helper(rewards,2,"GB")+"   |"+scoreSheet_helper(rewards,2,"AB")+"   |"+scoreSheet_helper(rewards,2,"RB")+"   |"+scoreSheet_helper(rewards,2,"EC")+"   |"+scoreSheet_helper(rewards,2,"TW")+"   |"+scoreSheet_helper(rewards,2,"BB")+"   |"+scoreSheet_helper(rewards,2,"YB")+"   |"+scoreSheet_helper(rewards,2,"AB")+"   |\n" +
    "+-----------------------------------------------------------------------+\n\n\n";
}
private String scoreSheet_helper(Reward[] rewards,int index, String s){
    if(rewards[index]!=null)
        return "X";
    else
        return s;
    }
}




