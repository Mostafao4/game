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


public class Hydra extends Creature {

    private int[] hydra;
    private int[]scores;
    private String[]reward;

    public Hydra(){
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



    public Reward checkBonus(){
            int i =  HeadsKilled(hydra);
            if(i <= 5){
                for(int j = 0; j <= 4; j++){
                if(hydra[j] == 4){
                       return  BonusHelper(reward[3]);
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
                            return BonusHelper(reward[5]); 
        
                        case 2:
                            return BonusHelper(reward[6]); 
                              
                            
                        case 4:
                            return BonusHelper(reward[8]);
                            
        
                        case 5:
                            return BonusHelper(reward[9]);
                            
        
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
        return this.score();
    }

    private int score(){ 
        int score;
        int c = HeadsKilled(hydra);
        switch (c) {
        case 1:
           return score = scores[0];
            
        case 2: 
            return score = scores[1];

        case 3: 
            return score = scores[2];

        case 4: 
            return score = scores[3];

        case 5: 
            return score = scores[4];

        case 6: 
            return score = scores[5];

        case 7: 
            return score = scores[6];

        case 8: 
            return score = scores[7];

        case 9: 
            return score = scores[8];

        case 10: 
            return score = scores[9];

        default: 
            return score = scores[10];  
        }
    }




    private int HeadsKilled(int[]hydra){
        int i = 0;
        while(i <= 11){
            if(hydra[i] == 0){
                i++;
            }
            else{
                return i+1;
            }
        }
            return i;
        }



    private String[] XinScoresheet(){
        String[] Xs = new String[11];
        for(int i = 0; i < 11; i++){
            if(hydra[i] == 0){
                Xs[i] = "X";
            }
            else{
                Xs[i] = "---";
            }
        }
        return Xs;

    } 

    
    


    public String getScoresheet(){
        String[] Xs = this.XinScoresheet();
        // String[] Xb = this.Xbonus();
        String[] Xb = new String[]{"AB","GB","EC","MB","TW"};
        return ("Tide Abyss: hydra Serpents (BLUE REALM):\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  #  |H11  |H12  |H13  |H14  |H15  |H21  |H22  |H23  |H24  |H25  |H26  |\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  H  |"+Xs[0]+"  |"+Xs[1]+"  |"+Xs[2]+"  |"+Xs[3]+"  |"+Xs[4]+"  |"+Xs[5]+"  |"+Xs[6]+"  |"+Xs[7]+"  |"+Xs[8]+"  |"+Xs[9]+"  |"+Xs[10]+"  |\n" +
        "|  C  |≥1   |≥2   |≥3   |≥4   |≥5   |≥1   |≥2   |≥3   |≥4   |≥5   |≥6   |\n" +
        "|  R  |     |     |"+ Xb[0]+"  |"+Xb[1]+"  |"+Xb[2]+"  |"+Xb[3]+"  |"+Xb[4]+"  |"+"\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  S  |1    |3    |6    |10   |15   |21   |28   |36   |45   |55   |66   |\n" +
        "+-----------------------------------------------------------------------+\n\n");
        }


    public void makeMove(Move move) throws Exception{
        int value = move.getDice().getValue();
        for (int i = 0; i <= 4; i++) {
                if (value >= i && hydra[i] != 0) {
                    System.out.println("Head " + i + " successfully attacked!");
                    hydra[i] = 0;
                    break;
                  } 
             }
            for(int j = 5; j <= 10; j++){
                if (value >= j && hydra[j] != 0) {
                    System.out.println("Head " + j + " successfully attacked!");
                    hydra[j] = 0;
                    break;
                  }
            }     
        }
            
        


    public Move[] getAllPossibleMoves(){ 
            Move[] moves = new Move[6];
            int hydranum = HeadsKilled(hydra);
            if(hydranum <= 5){
                for(int j = 0; j <= 4; j++){
                    if(hydra[j] != 0){
                        int val = hydra[j];
                        moves[j] = new Move(new BlueDice(val), this); 
                    }
                    else{
                        moves[j] = null;
                    }
                }
                return moves;
            }
            else{
                for(int c = 0; c <= 5; c++){
                    if(hydra[c] != 0){
                        int val = hydra[c];
                        moves[c] = new Move(new BlueDice(val), this); 
                    }
                    else{
                        moves[c] = null;
                    }
                }
                return moves;
            }
        }


    public Move[] getPossibleMovesForADie(Dice dice){
            Move[] moves = new Move[1];
            moves[0] = new Move(dice,this);
            return moves;
        }

    
}


    

    

           


  
