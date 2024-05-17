package game.engine;

public class GameScore {
    private int score;
    //Get Realm Scores
    public int getRedRealmScore(){
        return 0;
    }
    public int getGreenRealmScore(){
        return 1;
    }
    public int getBlueRealmScore(){
        return 2;
    }
    public int getMagentaRealmScore(){
        return 3;
    }
    public int getYellowRealmScore(){
        return 4;
    }
    //Setters and Getters
    public int getScore() {
        return score;
    }

    public void setScore() {
        score = this.getRedRealmScore() +
                this.getGreenRealmScore() +
                this.getBlueRealmScore() +
                this.getMagentaRealmScore() +
                this.getYellowRealmScore();
    }
}
