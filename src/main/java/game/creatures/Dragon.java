package game.creatures;

import game.dice.RedDice;
import game.engine.Player;

public class Dragon extends Creature {
    private int [][] dragonParts;
    public Dragon() {
        this.dragonParts = new int[4][4];
        dragonParts[0][0] = 3; dragonParts[0][1] = 6; dragonParts[0][2] = 5; dragonParts[0][3] = 0;
        dragonParts[1][0] = 2; dragonParts[1][1] = 1; dragonParts[1][2] = 0; dragonParts[1][3] = 5;
        dragonParts[2][0] = 1; dragonParts[2][1] = 0; dragonParts[2][2] = 2; dragonParts[2][3] = 4;
        dragonParts[3][0] = 0; dragonParts[3][1] = 3; dragonParts[3][2] = 4; dragonParts[3][3] = 6;
    }
    public Reward checkBonus (int[][] dragonParts){
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++){
            int c = 0;
            for (int j = 0; j < dragonParts[i].length; j++){
                if (dragonParts[i][j] == 0){
                    c++;
                    x++;
                }
            }
            if (c == dragonParts[i].length){
                switch (i){
                    case 0:
                        return GreenBonus;
                        break;
                    case 1:
                        return YellowBonus;
                        break;
                    case 2:
                        return BlueBonus;
                        break;
                    case 3:
                        return ElementalCrest;
                        break;
                }
            }
        }
        if (x == 16){
            return ArcaneBoostPower;
        }
        return null;
    }

    public int getPoints (int[][] dragonParts){
        int score = 0;
        for (int j = 0; j < dragonParts.length; j++){
            int c = 0;
            for (int i = 0; i < dragonParts[i].length; i++){
                if (dragonParts[i][j] == 0){
                    c++;
                }
            }
            if (c == dragonParts[j].length){
                switch (j){
                    case 0:
                        score = score +  10;
                        break;
                    case 1:
                        score = score + 14;
                        break;
                    case 2:
                        score = score + 16;
                        break;
                    case 3:
                        score = score + 20;
                        break;
                }
            }
        }
        return score;
    }

    public void attack (){
        int [] x = new int[2];
        x = move (r,  c);
        if (x[0] != 10 && x[1] != 10){
            dragonParts[x[0]][x[1]] = 0;
        }
    }

    public int [] move (RedDice r, Creature c) throws Exception{
        int c = 0;
        int [] x = new int[2];
      //  x [0] = 10;
       // x [1] = 10;
        for (int i = 0; i < dragonParts.length; i++){
            for (int j = 0; j < dragonParts[i].length; j++){
                if (r.getNo == dragonParts [i][j] && c == j ) {
                    if (dragonParts[i][j] != 0) {
                        x [0]= i;
                        x [1]= j;
                        return x;
                        break;
                    } else {
                        c++;
                    }
                }
            }
        }
        if (c == 16){
            throw new Exception();
        }
        return x;
    }
}
