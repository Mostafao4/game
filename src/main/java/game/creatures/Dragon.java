package game.creatures;

import game.dice.RedDice;
import game.engine.Player;
import game.collectibles.*;

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
            return ArcaneBoost;
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


    public void move (RedDice r, Creature c) throws Exception{
        int c = 0;
        for (int i = 0; i < dragonParts.length; i++){
            for (int j = 0; j < dragonParts[i].length; j++){
                if (r.getNo == dragonParts [i][j] && c == j ) {
                    if (dragonParts[i][j] != 0) {
                        dragonParts[i][j] = 0;
                        break;
                    } else {
                        c++;
                    }
                }
            }
        }
        if (c == 16){
            throw new Exception("This dragon has been attacked!");
        }
    }
}
