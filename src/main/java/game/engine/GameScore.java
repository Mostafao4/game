package game.engine;

public class GameScore {
    private ScoreSheet scoreSheet;
    //Get Realm Scores

    public GameScore() {
        scoreSheet = new ScoreSheet();
    }
    public int getScore() {
        return getRedRealmScore()+getBlueRealmScore()+getGreenRealmScore()+getYellowRealmScore()+getMagentaRealmScore();
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


}
