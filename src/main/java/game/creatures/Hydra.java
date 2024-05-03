package game.creatures;

import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;

public class Hydra {
    int[] Hydra1;
    int[] Hydra2;
    public Hydra(){
        this.Hydra1 = new int[5];
        this.Hydra2 = new int[6];
        Hydra1[0] = 1;   Hydra2[0] = 1;
        Hydra1[1] = 2;   Hydra2[1] = 2;
        Hydra1[2] = 3;   Hydra2[2] = 3;
        Hydra1[3] = 4;   Hydra2[3] = 4;
        Hydra1[4] = 5;   Hydra2[4] = 5;
                         Hydra2[5] = 6;
    }


    public static void currHydra(int[] Hydra1, int[] Hydra2){
        if(Hydra1.length == 0){
            System.out.println("You killed first Hydra and will attack the new Hydra with 6 heads");
        }
        else{
            System.out.println("You will attack the first Hydra");
        }
    }


    public Collectibles checkBonus(int[] Hydra1, int [] Hydra2){
        if(Hydra1.length != 0){
            int i = 0;
            if(Hydra1[i] == 4){
                return ArcaneBoostPower;
                break;
            }
            else{
                i++;
            }
        }
        else{
            for(int j = 0; j < Hydra2.length; j++){
                switch (Hydra2[j]) {
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
    
            
    public int getPoints(int[] Hydra1, int[]Hydra2){
        if(Hydra1.length != 0){
            for(int i = 0; i < Hydra1.length; i++){
                int c = 0;
            }
        }
    }        

           
}

  
