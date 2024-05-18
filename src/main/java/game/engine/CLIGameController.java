package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.Bonus;
import game.collectibles.TimeWarp;
import game.creatures.Realm;
import game.dice.*;
import game.exceptions.PlayerActionException;


public class CLIGameController extends GameController {
    private GameBoard gameBoard;

    @Override
    public void startGame() {
        gameBoard = new GameBoard();
        System.out.println("Welcome to Dice Realms: Quest for Elemental Crests!!!");
        System.out.println("Enter Player 1 name");
        String s1 = gameBoard.getScan().string();
        System.out.println("Enter Player 2 name");
        String s2 = gameBoard.getScan().string();
        gameBoard.setPlayer1(new Player(s1,PlayerStatus.ACTIVE));
        gameBoard.setPlayer2(new Player(s2,PlayerStatus.PASSIVE));
    }

    @Override
    public boolean switchPlayer() {
        if(gameBoard.getPlayer1().getPlayerStatus()==PlayerStatus.ACTIVE) {
            gameBoard.getPlayer1().setPlayerStatus(PlayerStatus.PASSIVE);
            gameBoard.getPlayer2().setPlayerStatus(PlayerStatus.ACTIVE);
        }
        else{
            gameBoard.getPlayer1().setPlayerStatus(PlayerStatus.ACTIVE);
            gameBoard.getPlayer2().setPlayerStatus(PlayerStatus.PASSIVE);
        }
        gameBoard.getGameStatus().resetTurn();
        return true;
    }

    @Override
    public Dice[] rollDice() {
        for (Dice die : gameBoard.getDice())
            if (die.getDiceStatus()==DiceStatus.AVAILABLE)
                die.roll();
        return gameBoard.getDice();
    }

    @Override
    public Dice[] getAvailableDice() {
        int c=0;
        Dice[] arr=getAllDice();
        for(Dice d : arr)
            if(d.getDiceStatus()==DiceStatus.AVAILABLE)
                c++;
        Dice[] out = new Dice[c];
        for(Dice d : arr)
            if(d.getDiceStatus()==DiceStatus.AVAILABLE)
                out[c-1]=d;
        return out;
    }

    @Override
    public Dice[] getAllDice() {
        return getGameBoard().getDice();
    }

    @Override
    public Dice[] getForgottenRealmDice() {
        Dice[] dice = this.getAvailableDice();
        for(int i = 0; i < dice.length; i++) {
            if (dice[i].getDiceStatus() != DiceStatus.FORGOTTEN_REALM)
                dice[i].setValue(0);
            System.out.print(dice[i] + "  ");
        }
        System.out.println();
        return dice;
    }

    @Override
    public Move[] getAllPossibleMoves(Player player) {
        Move[] red = player.getScoreSheet().getDragon().getAllPossibleMoves();
        Move[] green = player.getScoreSheet().getGaia().getAllPossibleMoves();
        Move[] blue = player.getScoreSheet().getHydra().getAllPossibleMoves();
        Move[] magenta = player.getScoreSheet().getPhoenix().getAllPossibleMoves();
        Move[] yellow = player.getScoreSheet().getLion().getAllPossibleMoves();
        Move[] out = new Move[red.length + green.length + blue.length + magenta.length + yellow.length];
        System.arraycopy(red, 0, out, 0, red.length);
        System.arraycopy(green, 0, out, red.length, green.length);
        System.arraycopy(blue, 0, out, red.length + green.length, blue.length);
        System.arraycopy(magenta, 0, out, red.length + green.length + blue.length, magenta.length);
        System.arraycopy(yellow, 0, out, red.length + green.length + blue.length + magenta.length, yellow.length);
        return out;
    }

    @Override
    public Move[] getPossibleMovesForAvailableDice(Player player) throws Exception {
        Move[] red = this.getPossibleMovesForADie(player,this.getAvailableDice()[0]);
        Move[] green = this.getPossibleMovesForADie(player,this.getAvailableDice()[1]);
        Move[] blue = this.getPossibleMovesForADie(player,this.getAvailableDice()[2]);
        Move[] magenta = this.getPossibleMovesForADie(player,this.getAvailableDice()[3]);
        Move[] yellow = this.getPossibleMovesForADie(player,this.getAvailableDice()[4]);
        Move[] out = new Move[red.length + green.length + blue.length + magenta.length + yellow.length];
        System.arraycopy(red, 0, out, 0, red.length);
        System.arraycopy(green, 0, out, red.length, green.length);
        System.arraycopy(blue, 0, out, red.length + green.length, blue.length);
        System.arraycopy(magenta, 0, out, red.length + green.length + blue.length, magenta.length);
        System.arraycopy(yellow, 0, out, red.length + green.length + blue.length + magenta.length, yellow.length);
        return out;
    }

    @Override
    public Move[] getPossibleMovesForADie(Player player, Dice dice) throws Exception {
        Move[] red = player.getScoreSheet().getDragon().getPossibleMovesForADie(dice);
        Move[] green = player.getScoreSheet().getGaia().getPossibleMovesForADie(dice);
        Move[] blue = player.getScoreSheet().getHydra().getPossibleMovesForADie(dice);
        Move[] magenta = player.getScoreSheet().getPhoenix().getPossibleMovesForADie(dice);
        Move[] yellow = player.getScoreSheet().getLion().getPossibleMovesForADie(dice);
        Move[] out = new Move[red.length + green.length + blue.length + magenta.length + yellow.length];
        System.arraycopy(red, 0, out, 0, red.length);
        System.arraycopy(green, 0, out, red.length, green.length);
        System.arraycopy(blue, 0, out, red.length + green.length, blue.length);
        System.arraycopy(magenta, 0, out, red.length + green.length + blue.length, magenta.length);
        System.arraycopy(yellow, 0, out, red.length + green.length + blue.length + magenta.length, yellow.length);
        return out;
    }

    @Override
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public Player getActivePlayer() {
        if(gameBoard.getPlayer1().getPlayerStatus()==PlayerStatus.ACTIVE)
            return gameBoard.getPlayer1();
        else
            return gameBoard.getPlayer2();
    }

    @Override
    public Player getPassivePlayer() {
        if(gameBoard.getPlayer1().getPlayerStatus()==PlayerStatus.PASSIVE)
            return gameBoard.getPlayer1();
        else
            return gameBoard.getPlayer2();
    }

    @Override
    public ScoreSheet getScoreSheet(Player player) {
        return player.getScoreSheet();
    }

    @Override
    public GameStatus getGameStatus() {
        return gameBoard.getGameStatus();
    }

    @Override
    public GameScore getGameScore(Player player) {
        return player.getGameScore();
    }

    @Override
    public TimeWarp[] getTimeWarpPowers(Player player) {
        int x = getActivePlayer().getTimeWarpCount();
        TimeWarp[] powers = new TimeWarp[x];
        for (TimeWarp tw : powers)
            tw = new TimeWarp();
        return powers;
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers(Player player) {
        int x = getActivePlayer().getArcaneBoostCount();
        ArcaneBoost[] powers = new ArcaneBoost[x];
        for (ArcaneBoost ab : powers)
            ab = new ArcaneBoost();
        return powers;
    }

    @Override
    public boolean selectDice(Dice dice, Player player) {
        Dice[] diceArray = this.getAvailableDice();
        player.setSelectedDice(dice);
        for (Dice value : diceArray) {
            if(value.getValue() < dice.getValue())
                value.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
        }

        return true;
    }

    @Override
    public boolean makeMove(Player player, Move move) throws Exception {
        switch(move.getDice().getRealm()){
            case RED:
                System.out.println("Select a dragon to attack");
                int i = gameBoard.getScan().num();
                RedDice d = new RedDice(gameBoard.getDice()[0].getValue());
                player.getScoreSheet().getDragon().makeMove(new Move(d,player.getScoreSheet().getDragon(),i));
                break;
            case GREEN:
                int x = gameBoard.getDice()[1].getValue();
                int y = gameBoard.getDice()[5].getValue();
                Dice f = new GreenDice(x+y);
                player.getScoreSheet().getGaia().makeMove(new Move(f,player.getScoreSheet().getGaia()));
                break;
            case BLUE: player.getScoreSheet().getHydra().makeMove(move); break;
            case MAGENTA: player.getScoreSheet().getPhoenix().makeMove(move); break;
            case YELLOW: player.getScoreSheet().getLion().makeMove(move); break;
            case WHITE: break;
            default: throw new Exception();
        }
        System.out.println(this.getActivePlayer().getScoreSheet());
        return true;
    }
    public boolean thereAreAvailableDice(){
        Dice[] dice = this.getAvailableDice();
        for (Dice die : dice)
            if (die != null && die.getDiceStatus() == DiceStatus.AVAILABLE)
                return true;
        return false;
    }
    public void useBonus (Bonus bonus,int i) throws Exception {
        Realm r = bonus.getRealm();
        Dice d;
        switch (r) {
            case RED:
                d = (RedDice) new RedDice(i);
                break;
            case GREEN:
                d = (GreenDice) new GreenDice(i);
                break;
            case BLUE:
                d = (BlueDice) new BlueDice(i);
                break;
            case MAGENTA:
                d = (MagentaDice) new MagentaDice(i);
                break;
            case YELLOW:
                d = (YellowDice) new YellowDice(i);
                break;
            default:
                throw new PlayerActionException();
        }

        this.makeMove(this.getActivePlayer(), new Move(d, this.getActivePlayer().getScoreSheet().getCreatureByRealm(d)));
    }
    public boolean useArcaneBoost(Player player) throws Exception {
        int i = gameBoard.getScan().num();
        Dice d = gameBoard.getDice()[i];
        if(d.getDiceStatus()!=DiceStatus.POWER_SELECTED) {
            this.makeMove(player, new Move(d, player.getScoreSheet().getCreatureByRealm(d)));
            return true;
        }
        return false;
    }
    public static void printDice (Dice[] dice){

        for(Dice d : dice)
          System.out.print(

    }
}
