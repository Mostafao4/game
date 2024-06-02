package game.creatures;

import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;
import game.dice.BlueDice;
import game.dice.Dice;
import game.engine.Move;
import game.collectibles.Reward;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import game.collectibles.ArcaneBoost;
import game.collectibles.Bonus;
import game.exceptions.InvalidMoveException;


public class Hydra extends Creature {

    private int[] hydra;
    private int[]scores;
    private String[]reward;
    private String[] Xb;
    private int hydra1Heads;
    private int hydra2Heads;

    public Hydra(){
        hydra1Heads = 0;
        hydra2Heads = 0;
        this.hydra = new int[11];
        hydra[0] = 1;   hydra[5] = 1;
        hydra[1] = 2;   hydra[6] = 2;
        hydra[2] = 3;   hydra[7] = 3;
        hydra[3] = 4;   hydra[8] = 4;
        hydra[4] = 5;   hydra[9] = 5;
                         hydra[10] = 6;

        //config file for the rewards//
        try{

            Properties prop = new Properties();
            FileInputStream rewardConfig = new FileInputStream("src/main/resources/config/TideAbyssRewards.properties");
            prop.load(rewardConfig);
            String reward1 = prop.getProperty("null");
            String reward2 = prop.getProperty("null");
            String reward3 = prop.getProperty("null");
            String reward4 = prop.getProperty("ArcaneBoost");
            String reward5 = prop.getProperty("null");
            String reward6 = prop.getProperty("GreenBonus");
            String reward7 = prop.getProperty("ElementalCrest");
            String reward8 = prop.getProperty("null");
            String reward9 = prop.getProperty("MagentaBonus");
            String reward10 = prop.getProperty("TimeWarp");
            String reward11 = prop.getProperty("null");
            reward = new String[]{reward1,reward2,reward3,reward4,reward5,reward6,reward7,reward8,reward9,reward10,reward11};


        } catch (IOException e) {
            e.printStackTrace();
        }

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

    // private String[] Xbonus(){
    //     String[] XB = new String[]{"AB","GB","EC","MB","TW"};
    //     Reward reward = checkBonus();

    //     switch (reward) {
    //         case "ArcaneBoost":
    //             XB[0] = "X";
    //             break;
    //         case "GreenBonus":
    //             XB[1] = "X";
    //             break;
    //         case "ElementalCrest":
    //             XB[2] = "X";
    //             break;
    //         case "MagentaBonus":
    //             XB[3] = "X";
    //             break;
    //         case "TimeWarp":
    //             XB[4] = "X";
    //             break;
    //         default:
    //             break;
    //     }
    //     return XB;
    // }



    public Reward[] checkReward(){

            int i =  headsKilled();
            if(i <= 5){
                for(int j = 0; j <= 4; j++){
                if(hydra[j] == 4){
                       return new Reward[]{BonusHelper(reward[3])};
                    }
                else{
                        j++;
                    }
                }
                }

            else{
                for(int j = 5; j <= 10; j++){
                    switch (hydra[j]) {
                        case 1:
                            return new Reward[]{BonusHelper(reward[5])};

                        case 2:
                            return new Reward[]{BonusHelper(reward[6])};


                        case 4:
                            return new Reward[]{BonusHelper(reward[8])};


                        case 5:
                            return new Reward[]{BonusHelper(reward[9])};


                        default:
                            break;
                        }
                    }

                }
                return null;

                    }

    private Reward BonusHelper(String s){
        switch (s) {
            case "ArcaneBoost":
                return new ArcaneBoost();

            case "GreenBonus":
                return new Bonus(Realm.GREEN);

            case "ElementCrest":
                return new ElementalCrest(Realm.BLUE);

            case "MagentaBonus":
                return new Bonus(Realm.MAGENTA);

            case "TimeWarp":
                return new TimeWarp();

            default:
                return null;
        }
    }




    public int getScore(){
        return scores[hydra1Heads + hydra2Heads - 1];
    }


    private int headsKilled(){
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
        // String[] Xb = this.Xbonus();
        String[] Xb = new String[]{"AB","GB","EC","MB","TW"};
        return ("Tide Abyss: Hydra Serpents (BLUE REALM):\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  #  |H11  |H12  |H13  |H14  |H15  |H21  |H22  |H23  |H24  |H25  |H26  |\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  H  |"+Xs[0]+"  |"+Xs[1]+"  |"+Xs[2]+"  |"+Xs[3]+"  |"+Xs[4]+"  |"+Xs[5]+"  |"+Xs[6]+"  |"+Xs[7]+"  |"+Xs[8]+"  |"+Xs[9]+"  |"+Xs[10]+"  |\n" +
        "|  C  |≥1   |≥2   |≥3   |≥4   |≥5   |≥1   |≥2   |≥3   |≥4   |≥5   |≥6   |\n" +
        "|  R  |     |     |     |"+Xb[0]+"   |     |"+Xb[1]+"   |"+Xb[2]+"   |     |"+Xb[3]+"   |"+Xb[4]+"   |     |"+"\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  S  |1    |3    |6    |10   |15   |21   |28   |36   |45   |55   |66   |\n" +
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
            Move[] moves = new Move[1];
            moves[0] = new Move(dice,this);
            return moves;
        }

    
}


    

    

           


  
