package game.engine;

import game.dice.*;

public class GameBoard {
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;
    private Dice[] dice;
    private RedDice r;
    private GreenDice g;
    private BlueDice b;
    private MagentaDice m;
    private YellowDice y;
    private ArcanePrism a;



    public GameBoard(int i) {
        r = new RedDice(0);
        g = new GreenDice(0);
        b = new BlueDice(0);
        m = new MagentaDice(0);
        y = new YellowDice(0);
        a = new ArcanePrism(0);
        dice = new Dice[]{r, g, b, m, y, a};
        gameStatus = new GameStatus();
        
        if(i==2){
            player1 = new HumanPlayer(PlayerStatus.ACTIVE);
            player2 = new HumanPlayer(PlayerStatus.PASSIVE);
        }
        else{
            if(i==1){
            player1 = new HumanPlayer(PlayerStatus.ACTIVE);
            player2 = new ComputerPlayer(PlayerStatus.PASSIVE);
            }
        else{
            player1 = new ComputerPlayer(PlayerStatus.ACTIVE);
            player2 = new ComputerPlayer(PlayerStatus.PASSIVE);
        }
        }
        
    }
    public GameBoard() {
        r = new RedDice(0);
        g = new GreenDice(0);
        b = new BlueDice(0);
        m = new MagentaDice(0);
        y = new YellowDice(0);
        a = new ArcanePrism(0);
        dice = new Dice[]{r,g,b,m,y,a};
        gameStatus = new GameStatus();
        player1 = new HumanPlayer(PlayerStatus.ACTIVE);
        player2 = new HumanPlayer(PlayerStatus.PASSIVE);
    }

    public Dice[] getDice() {
        return dice;
    }
    public void setDice(Dice[] dice) {
        this.dice = dice;
    }


    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String diceToString(){
        return r.toString()+" "+g.toString()+" "+b.toString()+" "+m.toString()+" "+y.toString()+" "+a.toString();
    }


}
