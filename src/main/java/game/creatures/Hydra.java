package game.creatures;

import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;

public class Hydra {

    private int[] Hydra;
    public Hydra(){
        this.Hydra = new int[11];
        Hydra[0] = 1;   Hydra[5] = 1;
        Hydra[1] = 2;   Hydra[6] = 2;
        Hydra[2] = 3;   Hydra[7] = 3;
        Hydra[3] = 4;   Hydra[8] = 4;
        Hydra[4] = 5;   Hydra[9] = 5;
                         Hydra[10] = 6;                
    }


    public static void currHydra(int[] Hydra){
        int i = 0;
        if( i > 4){
            System.out.println("You killed first Hydra and will attack the new Hydra with 6 heads");
        }
        else{
            System.out.println("You will attack the first Hydra");
            i++;
        }
    }



    public Collectibles checkBonus(int[] Hydra){
        int i = 0;
            if(i <= 4){
                if(Hydra[i] == 4){
                       return ArcaneBoostPower;
                       break;
                    }
                else{
                        i++;
                    }
                }
            else{
                for(int j = 5; j <= 10; j++){
                    switch (Hydra[j]) {
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
                                return TimeWarpPower;
                                break; 
        
                            default:
                                break;
                        }
                    }
                }
        
                    }
    
            
    public int getScore(int[]hydra){
        return score(hydra);
    }

    private int score(int[]hydra){
        int score;
        int c = HeadsKilled(Hydra);
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

    
    

           
}

  
