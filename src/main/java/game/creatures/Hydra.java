package game.creatures;

import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;
import game.dice.Dice;
import game.engine.Move;

public class Hydra {

    private int[] hydra;
    public Hydra(){
        this.hydra = new int[11];
        hydra[0] = 1;   hydra[5] = 1;
        hydra[1] = 2;   hydra[6] = 2;
        hydra[2] = 3;   hydra[7] = 3;
        hydra[3] = 4;   hydra[8] = 4;
        hydra[4] = 5;   hydra[9] = 5;
                         hydra[10] = 6;                
    }


    public static void currhydra(){
        int i = 0;
        if( i > 4){
            System.out.println("You killed first hydra and will attack the new hydra with 6 heads");
        }
        else{
            System.out.println("You will attack the first hydra");
            i++;
        }
    }



    public Collectibles checkBonus(){
        int i = 0;
            if(i <= 4){
                if(hydra[i] == 4){
                       return ArcaneBoost;
                       break;
                    }
                else{
                        i++;
                    }
                }
            else{
                for(int j = 5; j <= 10; j++){
                    switch (hydra[j]) {
                        case 1:
                            return GreenBonus; 
                            break;
        
                        case 2:
                            return ElementalCrest; 
                            break;   
                            
                        case 4:
                            return MagentaBonus;
                            break;
        
                        case 5:
                            return TimeWarp;
                            break; 
        
                        default:
                            break;
                        }
                    }
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
            score = 1;

        case 2: 
            score = 3;

        case 3: 
            score = 6;

        case 4: 
            score = 10;

        case 5: 
            score = 15;

        case 6: 
            score = 21;

        case 7: 
            score = 28;

        case 8: 
            score = 36;

        case 9: 
            score = 45;

        case 10: 
            score = 55;

        default: 
            return score = 66;  
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



    public String[] XinScoresheet(){
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
        return ("Tide Abyss: hydra Serpents (BLUE REALM):\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  #  |H11  |H12  |H13  |H14  |H15  |H21  |H22  |H23  |H24  |H25  |H26  |\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  H " +Xs[0] +Xs[1] +Xs[2]  +Xs[3] +Xs[4] +Xs[5] +Xs[6] +Xs[7] +Xs[8] +Xs[9] +Xs[10] + "|\n" +
        "|  C  |≥1   |≥2   |≥3   |≥4   |≥5   |≥1   |≥2   |≥3   |≥4   |≥5   |≥6   |\n" +
        "|  R  |     |     |     |AB   |     |GB   |EC   |     |MB   |TW   |     |\n" +
        "+-----------------------------------------------------------------------+\n" +
        "|  S  |1    |3    |6    |10   |15   |21   |28   |36   |45   |55   |66   |\n" +
        "+-----------------------------------------------------------------------+\n\n");
        }


        public void makeMove(Dice dice){
            int value = dice.getValue();
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
            if(hydra[0] == 0){
            }

        }





}


    

    

           


  
