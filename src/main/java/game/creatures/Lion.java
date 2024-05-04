package game.creatures;
import game.collectibles.*;
public class Lion{
    private int hitNum;
    private int diceNum;
    private int[] multipliers={1,1,1,2,1,1,2,1,3};
    private int score;
    private int totalScore;
    private collectibles[] rewards={0,0,TimeWarp,0,RedBonus,ArcaneBoost,0,ElementalCrest,0,MagnetaBonus,0};
    
    public Lion(int hitNum, int diceNum, int[] multipliers, int score, int totalScore, collectibles[] rewards) {
        this.hitNum = hitNum;
        this.diceNum = diceNum;
        this.multipliers = multipliers;
        this.score = score;
        this.totalScore = totalScore;
        this.rewards = rewards;
    }



public int getHitNum() {
    return hitNum;
}
public void setHitNum(int hitNum) {
    this.hitNum = hitNum;
}
public int getDiceNum() {
    return diceNum;
}
public void setDiceNum(int diceNum) {
    this.diceNum = diceNum;
}
public int[] getMultipliers() {
    return multipliers;
}
public void setMultipliers(int[] multipliers) {
    this.multipliers = multipliers;
}
public int getScore() {
    return score;
}
public void setScore(int score) {
    this.score = score;
}
public int getTotalScore() {
    return totalScore;
}
public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
}
public Object[] getRewards() {
    return rewards;
}
public void setRewards(collectibles[] rewards) {
    this.rewards = rewards;
}
public static int multiplyScore(int hitNum,int diceNum,int[] multipliers){
    int score=multipliers[hitNum]*diceNum;
     return score;
 }
 public int TotalScore(int score, int totalScore){
     totalScore+=score;
     return totalScore;
 }
public collectibles getReward(int hitNum, collectibles[] rewards){
    return rewards[hitNum];
 }
 
 }

