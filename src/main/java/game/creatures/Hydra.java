package game.creatures;

import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;
import game.dice.BlueDice;
import game.dice.Dice;
import game.engine.Move;
import game.collectibles.Reward;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import game.collectibles.ArcaneBoost;
import game.collectibles.Bonus;
import game.exceptions.InvalidMoveException;


public class Hydra extends Creature {

    private int[] hydra;
    private int[]scores;
    private String[]reward;
    private Reward[]rewards;
    private String[] Xb;
    private int hydra1Heads;
    private int hydra2Heads;
    private String[] rew = new String[11];

    public Hydra(){
        hydra1Heads = 0;
        hydra2Heads = 0;
        rewards = new Reward[11];
        this.hydra = new int[11];
        hydra[0] = 1;   hydra[5] = 1;
        hydra[1] = 2;   hydra[6] = 2;
        hydra[2] = 3;   hydra[7] = 3;
        hydra[3] = 4;   hydra[8] = 4;
        hydra[4] = 5;   hydra[9] = 5;
                         hydra[10] = 6;


       //config file for the scores//
        try{

            Properties prop1 = new Properties();
            FileInputStream scoreConfig = new FileInputStream("src/main/resources/config/TideAbyssScore.properties");
            prop1.load(scoreConfig);
            int score1 = Integer.parseInt(prop1.getProperty("col1"));
            int score2 = Integer.parseInt(prop1.getProperty("col2"));
            int score3 = Integer.parseInt(prop1.getProperty("col3"));
            int score4 = Integer.parseInt(prop1.getProperty("col4"));
            int score5 = Integer.parseInt(prop1.getProperty("col5"));
            int score6 = Integer.parseInt(prop1.getProperty("col6"));
            int score7 = Integer.parseInt(prop1.getProperty("col7"));
            int score8 = Integer.parseInt(prop1.getProperty("col8"));
            int score9 = Integer.parseInt(prop1.getProperty("col9"));
            int score10 = Integer.parseInt(prop1.getProperty("col10"));
            int score11 = Integer.parseInt(prop1.getProperty("col11"));
            scores = new int[]{score1,score2,score3,score4,score5,score6,score7,score8,score9,score10,score11};

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        editReward();
    }


    private String currhydra(int i){
        if( i > 4){
            System.out.println("You killed first hydra and will attack the new hydra with 6 heads");
            return "Hydra 2";
        }
        else{
            System.out.println("You will attack the first hydra");
            return "Hydra 1";
        }
    }

    // checks rewards and returns the corresponding reward in an array & makes used reward an x
    public Reward[] checkReward(){

        switch (rew[headsKilled()-1]){
            case "AB":
                rew[headsKilled()-1]="X ";
                return new Reward[]{new ArcaneBoost()};
            case "BB":
                rew[headsKilled()-1]="X ";
                return new Reward[]{new Bonus(Realm.BLUE)};
            case "EC":
                rew[headsKilled()-1]="X ";
                return new Reward[]{new ElementalCrest(Realm.YELLOW)};
            case "GB":
                rew[headsKilled()-1]="X ";
                return new Reward[]{new Bonus(Realm.GREEN)};
            case "MB":
                rew[headsKilled()-1]="X ";
                return new Reward[]{new Bonus(Realm.MAGENTA)};
            case "RB":
                rew[headsKilled()-1]="X ";
                return new Reward[]{new Bonus(Realm.RED)};
            case "TW":
                rew[headsKilled()-1]="X ";
                return new Reward[]{new TimeWarp()};
            case "YB":
                rew[headsKilled()-1]="X ";
                return new Reward[]{new Bonus(Realm.YELLOW)};
            default: return null;
        }
    }
    public int getScore(){
        if(headsKilled()==0)
            return 0;
        return scores[hydra1Heads + hydra2Heads - 1];
    }


    public int headsKilled(){
        return hydra1Heads + hydra2Heads;
    }



    private String[] XinScoresheet(){
        String[] Xs = new String[11];
        for(int i = 0; i < 11; i++){
            if(hydra[i] == 0){
                Xs[i] = "X  ";
            }
            else{
                Xs[i] = "---";
            }
        }
        return Xs;

    }





    public String toString(){
        String[] Xs = this.XinScoresheet();
        return ("Tide Abyss: Hydra Serpents (BLUE REALM):\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  #  |H11  |H12  |H13  |H14  |H15  |H21  |H22  |H23  |H24  |H25  |H26  |\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  H  |"+Xs[0]+"  |"+Xs[1]+"  |"+Xs[2]+"  |"+Xs[3]+"  |"+Xs[4]+"  |"+Xs[5]+"  |"+Xs[6]+"  |"+Xs[7]+"  |"+Xs[8]+"  |"+Xs[9]+"  |"+Xs[10]+"  |\n" +
        "|  C  |≥1   |≥2   |≥3   |≥4   |≥5   |≥1   |≥2   |≥3   |≥4   |≥5   |≥6   |\n" +
        "|  R  |"+rew[0]+"   |"+rew[1]+"   |"+rew[2]+"   |"+rew[3]+"   |"+rew[4]+"   |"+rew[5]+"   |"+rew[6]+"   |"+rew[7]+"   |"+rew[8]+"   |"+rew[9]+"   |"+rew[10]+"   |\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  S  |"+scores[0]+(scores[0]>9?"   |":"    |")+scores[1]+(scores[1]>9?"   |":"    |")+scores[2]+(scores[2]>9?"   |":"    |")+scores[3]+(scores[3]>9?"   |":"    |")+scores[4]+(scores[4]>9?"   |":"    |")+scores[5]+(scores[5]>9?"   |":"    |")+scores[6]+(scores[6]>9?"   |":"    |")+scores[7]+(scores[7]>9?"   |":"    |")+scores[8]+(scores[8]>9?"   |":"    |")+scores[9]+(scores[9]>9?"   |":"    |")+scores[10]+( scores[10]>9?"   |\n":"    |\n") +
        "+-----------------------------------------------------------------------+\n\n");
        }


    public boolean makeMove(Move move) throws InvalidMoveException {
        int value = move.getDice().getValue();
        boolean flag = false;
        if(hydra1Heads < 5){
            if(hydra[hydra1Heads] != 0 && value > hydra1Heads){
                flag = true;
                System.out.println("You have succesfully attacked Hydra 1, Head "+hydra1Heads+"!");
                hydra[hydra1Heads] = 0;
                hydra1Heads++;
            }
            else{
                throw new InvalidMoveException("Dice value must be greater than "+hydra1Heads+" for this attack");
            }
        }
        else if(hydra2Heads < 6){
            if(hydra[hydra1Heads + hydra2Heads] != 0 && value > hydra2Heads){
                flag = true;
                hydra[hydra1Heads + hydra2Heads] = 0;
                hydra2Heads++;
            }
            else{
                throw new InvalidMoveException("Dice value must be greater than "+hydra2Heads+" for this attack");
            }

        }
        return flag;
    }
            
        


public Move[] getAllPossibleMoves() {
    Move[] moves = new Move[6]; // Always create an array of size 6
    
    // Fill the array with Moves based on the hydra heads
    if (hydra1Heads < 5) {
        for (int j = 0; j <= 4; j++) {
            if (hydra[j] != 0) {
                int val = hydra[j];
                moves[j] = new Move(new BlueDice(val), this);
            }
        }
    } else {
        for (int c = 0; c <= 5; c++) {
            if (hydra[c] != 0) {
                int val = hydra[c];
                moves[c] = new Move(new BlueDice(val), this);
            }
        }
    }


    return removeNullsAndFill(moves);
}

private Move[] removeNullsAndFill(Move[] moves) {
    int count = 0;
    for (Move move : moves) {
        if (move != null) {
            count++;
        }
    }
    
    Move[] newMoves = new Move[6];
    int index = 0;
    for (Move move : moves) {
        if (move != null) {
            newMoves[index++] = move;
        }
    }

    // Fill remaining slots with dummy moves if needed
    while (index < 6) {
        newMoves[index++] = new Move(new BlueDice(0), this); // Use a dummy move with value 0 or any appropriate default value
    }
    
    return newMoves;
}
    


    public Move[] getPossibleMovesForADie(Dice dice){
    
        if(dice.getValue()<hydra[headsKilled()]){
            return new Move[0];
        }
        else{
            return new Move[]{new Move(dice,this)};
        }
        }


    //creates array rew type string from config for rewards
    public void editReward(){
        Properties prop = new Properties();
        try{

            FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/TideAbyssRewards.properties");
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
                    default:
                        rew[i]="  ";
                        break;
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}


    

    

           


  
