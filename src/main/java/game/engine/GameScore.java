package game.engine;

import game.collectibles.ElementalCrest;
import game.creatures.Realm;

import java.util.ArrayList;
import java.util.List;

public class GameScore {
    private ScoreSheet scoreSheet;
    List<ElementalCrest> elementalCrest;


    public GameScore() {
        scoreSheet = new ScoreSheet();
        elementalCrest = new ArrayList<>();
    }
    public int getScore() {
        int[] arr = new int[]{getRedRealmScore(),getGreenRealmScore(),getBlueRealmScore(),getMagentaRealmScore(),getYellowRealmScore()};
        int min = arr[0];
        int out = min;
        for(int i = 1; i < arr.length; i++) {
            out += arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        out+=(min * elementalCrest.size());
        return out;
    }
    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }
    public int getRedRealmScore(){
        return scoreSheet.getDragon().getPoints();
    }
    public int getGreenRealmScore(){
        return scoreSheet.getGaia().getGreenRealmScore();
    }
    public int getBlueRealmScore(){
        return scoreSheet.getHydra().getScore();
    }
    public int getMagentaRealmScore(){
        return scoreSheet.getPhoenix().getMagentaRealmScore();
    }
    public int getYellowRealmScore(){
        return scoreSheet.getLion().getYellowRealmScore();
    }
    public ElementalCrest[] getElementalCrest() {
        ElementalCrest[] out =  new ElementalCrest[elementalCrest.size()];
        elementalCrest.toArray(out);
        return out;
    }
    public void addElementalCrest(ElementalCrest e){
        elementalCrest.add(e);
    }



}
