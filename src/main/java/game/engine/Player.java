package game.engine;

import game.collectibles.ElementalCrest;
import game.dice.Dice;

public interface Player {
    // GameScore gameScore;
    // PlayerStatus playerStatus;
    // Dice selectedDice;
    // int timeWarpCount;
    // int arcaneBoostCount;
    // boolean testFlag;
    public PlayerStatus getPlayerStatus();
    public void setPlayerStatus(PlayerStatus playerStatus) ;

    public GameScore getGameScore();

    public boolean isTestFlag() ;

    public void setTestFlag(boolean testFlag);

    public String getPlayerName() ;
    public void setPlayer_name(String playerName);

    public int getTimeWarpCount() ;
    public void addTimeWarpCount() ;
    public void subtractTimeWarpCount(); 

    public int getArcaneBoostCount();
    
    public void addArcaneBoostCount() ;
    public void subtractArcaneBoostCount();
    public ElementalCrest[] getElementalCrest();
    public Dice getSelectedDice() ;
    public void setSelectedDice(Dice selectedDice) ;

    public ScoreSheet getScoreSheet() ;
    public String getPlayerType();
    public int chooseDieNum(Dice[] arrayOfDice, int turn );
}
