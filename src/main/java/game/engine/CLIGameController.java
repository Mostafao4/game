package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.Bonus;
import game.collectibles.TimeWarp;
import game.creatures.Realm;
import game.dice.*;

import java.util.*;


public class CLIGameController extends GameController {
    private GameBoard gameBoard;

    public CLIGameController() {
        this.gameBoard = new GameBoard();
    }
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
        int i=0;
        for(Dice d : arr)
            if(d.getDiceStatus()==DiceStatus.AVAILABLE)
                out[i++]=d;
        return out;
    }

    @Override
    public Dice[] getAllDice() {
        return gameBoard.getDice();
    }

    @Override
    public Dice[] getForgottenRealmDice() {
        int c=0;
        getAllDice();
        for(Dice d : getAllDice())
            if(d.getDiceStatus()==DiceStatus.FORGOTTEN_REALM)
                c++;
        Dice[] out = new Dice[c];
        int i=0;
        for(Dice d : getAllDice())
            if(d.getDiceStatus()==DiceStatus.FORGOTTEN_REALM)
                out[i++]=d;
        return out;
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

    public Move[] getPossibleMovesForAvailableDice(Player player) {
            List<Move> allPossibleMoves = new ArrayList<>();
            Move[] m;
            if(getAllDice()[1].getDiceStatus()==DiceStatus.AVAILABLE && getAllDice()[5].getDiceStatus()==DiceStatus.AVAILABLE) {
                for(Dice d : getAvailableDice()){
                    m=getPossibleMovesForADieHelper(player,d);
                    for(Move m2 : m){
                        allPossibleMoves.add(m2);
                    }
                }
            }
            else{
                for(Dice d : getAvailableDice()){
                    m=getPossibleMovesForADie(player,d);
                    for(Move m2 : m){
                        allPossibleMoves.add(m2);
                    }
                }
            }



            // Remove extra green dice if more than one is available

            // Calculate possible moves for each die
//            for (Dice die : availableDice) {
//                Move[] possibleMovesForDie = getPossibleMovesForADie(player, die);
//                allPossibleMoves.addAll(Arrays.asList(possibleMovesForDie));
//            }

            // Convert list to array

            Move[] out = new Move[allPossibleMoves.size()];
            out = allPossibleMoves.toArray(out);
            return out;
}



        //        int count = 0;
//        int c = 0;
//        boolean flag = false;
//        List<Move> moves = new ArrayList<>();
//        for (Dice d : getAvailableDice()) {
//            Move[] m = getPossibleMovesForADie(player, d);
//            for (Move m1 : m) {
//                if (moves.contains(m1) && m1.getDice().getRealm() == Realm.GREEN)
//                    System.out.println("repeated");
//                moves.add(m1);
//            }
//        }
//
//        for (Move m : moves) {
//            if (m.getDice().getRealm() == Realm.GREEN)
//                count++;
//            if (count == 2 && m.getDice().getRealm() == Realm.GREEN) {
//                moves.remove(m);
//            }
//        }
//
//        Move[] out = new Move[moves.size()];
//        out = moves.toArray(out);
//        return out;
//    }



    @Override
    public Move[] getPossibleMovesForADie(Player player, Dice dice) {
        Move[] m;
        switch (dice.getRealm()) {
            case RED:
                m = player.getScoreSheet().getDragon().getPossibleMovesForADie(dice);
                break;
            case GREEN:
                m = player.getScoreSheet().getGaia().getPossibleMovesForADie(dice);
                break;
            case BLUE:
                m = player.getScoreSheet().getHydra().getPossibleMovesForADie(dice);
                break;
            case MAGENTA:
                m = player.getScoreSheet().getPhoenix().getPossibleMovesForADie(dice);
                break;
            case YELLOW:
                m = player.getScoreSheet().getLion().getPossibleMovesForADie(dice);
                break;
            default:
                Move[] red = player.getScoreSheet().getDragon().getPossibleMovesForADie(new RedDice(dice.getValue()));
                Move[] green = player.getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(dice.getValue()+getAllDice()[1].getValue()));
                Move[] blue = player.getScoreSheet().getHydra().getPossibleMovesForADie(new BlueDice(dice.getValue()));
                Move[] magenta = player.getScoreSheet().getPhoenix().getPossibleMovesForADie(new MagentaDice(dice.getValue()));
                Move[] yellow = player.getScoreSheet().getLion().getPossibleMovesForADie(new YellowDice(dice.getValue()));
                m = new Move[red.length + green.length + blue.length + magenta.length + yellow.length];
                System.arraycopy(red, 0, m, 0, red.length);
                System.arraycopy(green, 0, m, red.length, green.length);
                System.arraycopy(blue, 0, m, red.length + green.length, blue.length);
                System.arraycopy(magenta, 0, m, red.length + green.length + blue.length, magenta.length);
                System.arraycopy(yellow, 0, m, red.length + green.length + blue.length + magenta.length, yellow.length);

        }
        return m;
    }



    public Move[] getPossibleMovesForADieHelper(Player player, Dice dice) {
        Move[] m;
        switch (dice.getRealm()) {
            case RED:
                m = player.getScoreSheet().getDragon().getPossibleMovesForADie(dice);
                break;
            case GREEN:
                m = player.getScoreSheet().getGaia().getPossibleMovesForADie(dice);
                break;
            case BLUE:
                m = player.getScoreSheet().getHydra().getPossibleMovesForADie(dice);
                break;
            case MAGENTA:
                m = player.getScoreSheet().getPhoenix().getPossibleMovesForADie(dice);
                break;
            case YELLOW:
                m = player.getScoreSheet().getLion().getPossibleMovesForADie(dice);
                break;
            default:
                Move[] red = player.getScoreSheet().getDragon().getPossibleMovesForADie(new RedDice(dice.getValue()));
                Move[] blue = player.getScoreSheet().getHydra().getPossibleMovesForADie(new BlueDice(dice.getValue()));
                Move[] magenta = player.getScoreSheet().getPhoenix().getPossibleMovesForADie(new MagentaDice(dice.getValue()));
                Move[] yellow = player.getScoreSheet().getLion().getPossibleMovesForADie(new YellowDice(dice.getValue()));
                m = new Move[red.length  + blue.length + magenta.length + yellow.length];
                System.arraycopy(red, 0, m, 0, red.length);
                System.arraycopy(blue, 0, m, red.length, blue.length);
                System.arraycopy(magenta, 0, m, red.length + blue.length, magenta.length);
                System.arraycopy(yellow, 0, m, red.length + blue.length + magenta.length, yellow.length);

        }
        return m;
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
        player.setSelectedDice(dice);
        for (Dice value : getAvailableDice()) {
            if(value.getValue() < dice.getValue())
                value.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
        }
        dice.setDiceStatus(DiceStatus.TURN_SELECTED);
        return true;
    }

    @Override
    public boolean makeMove(Player player, Move move)  {

        String s;
        boolean b=false;
        switch(move.getDice().getRealm()){
            case RED:
                System.out.println("Select a dragon to attack");

                RedDice d = new RedDice(move.getDice().getValue());
                b=(player.getScoreSheet().getDragon().makeMove(new Move(move.getDice(),player.getScoreSheet().getDragon())));
                break;
            case GREEN:
                int x = move.getDice().getValue();
                int y = getAllDice()[5].getValue();
                Dice f = new GreenDice(x+y);
                b=(player.getScoreSheet().getGaia().makeMove(new Move(f,player.getScoreSheet().getGaia())));
                break;
            case BLUE: b=(player.getScoreSheet().getHydra().makeMove(move)); break;
            case MAGENTA: b=(player.getScoreSheet().getPhoenix().makeMove(move)); break;
            case YELLOW: b=(player.getScoreSheet().getLion().makeMove(move)); break;
            case WHITE:
                System.out.println("Choose a realm to attack");
                int q = gameBoard.getScan().num();
                b=(whiteMove(player,move.getDice().getValue(),q));
                break;
            default:break;
        }
        System.out.println("\n"+player.getPlayerName()+"'s Grimoire:\n");
        System.out.println(player.getScoreSheet());
        return b;
    }

    public boolean thereAreAvailableDice(){
        return getAvailableDice().length != 0;
    }

    public void useBonus (Bonus bonus,int i)  {
        Realm r = bonus.getRealm();
        Dice d;
        switch (r) {
            case RED:
                d = new RedDice(i);
                break;
            case GREEN:
                d = new GreenDice(i);
                break;
            case BLUE:
                d = new BlueDice(i);
                break;
            case MAGENTA:
                d = new MagentaDice(i);
                break;
            case YELLOW:
                d = new YellowDice(i);
                break;
            default:
                //throw new PlayerActionException();
                d = null;
        }

        this.makeMove(this.getActivePlayer(), new Move(d, this.getActivePlayer().getScoreSheet().getCreatureByRealm(d)));
    }



    public boolean useArcaneBoost(Player player)  {
        int i = gameBoard.getScan().num();
        Dice d = getAllDice()[i];
        if(d.getDiceStatus()!=DiceStatus.POWER_SELECTED) {
            this.makeMove(player, new Move(d, player.getScoreSheet().getCreatureByRealm(d)));
            return true;
        }
        return false;
    }



    public static void printDice (Dice[] dice){
        for(Dice d : dice){
            switch(d.getRealm()){
                case RED:
                    System.out.print("Red: "+d.getValue()+"  ");
                    break;
                case GREEN:
                    System.out.print("Green: "+d.getValue()+"  ");
                    break;
                case BLUE:
                    System.out.print("Blue: "+d.getValue()+"  ");
                    break;
                case MAGENTA:
                    System.out.print("Magenta: "+d.getValue()+"  ");
                    break;
                case YELLOW:
                    System.out.print("Yellow: "+d.getValue()+"  ");
                    break;
                case WHITE:
                    System.out.print("Arcane: "+d.getValue()+"  ");
                    break;
                default:
                    System.out.print("error");
            }
        }
        System.out.println();
    }
    public void startTurn(){
        System.out.println("Round: " + getGameBoard().getGameStatus().getRound());
        System.out.println("Turn: " + getGameBoard().getGameStatus().getTurn());
        System.out.println("Current Player: " + getActivePlayer().getPlayerName());
        System.out.println();
    }
    public void resetDice(){
        for(Dice d : getAllDice()){
            d.setDiceStatus(DiceStatus.AVAILABLE);
        }
    }
    public static Realm getRealmFromString(String x){
        switch (x) {
            case "red":
                return Realm.RED;
            case "green":
                return Realm.GREEN;
            case "blue":
                return Realm.BLUE;
            case "magenta":
                return Realm.MAGENTA;
            case "yellow":
                return Realm.YELLOW;
            default:
                return Realm.WHITE;
        }
    }
    public boolean whiteMove(Player player,int i,int g) {
        switch(g) {
            case 0:
                System.out.println("Select a dragon to attack");
                int in = gameBoard.getScan().num();
                RedDice d = new RedDice(i);
                player.getScoreSheet().getDragon().makeMove(new Move(d, player.getScoreSheet().getDragon(), in));
                break;
            case 1:
                int re=0;
                for(Dice di : getAllDice())
                    if(di.getRealm()==Realm.GREEN)
                        re = di.getValue();
                Dice f = new GreenDice(i + re);
                player.getScoreSheet().getGaia().makeMove(new Move(f, player.getScoreSheet().getGaia()));
                break;
            case 2:
                BlueDice b = new BlueDice(i);
                player.getScoreSheet().getHydra().makeMove(new Move(b,player.getScoreSheet().getCreatureByRealm(b)));
                break;
            case 3:
                MagentaDice m = new MagentaDice(i);
                player.getScoreSheet().getPhoenix().makeMove(new Move(m,player.getScoreSheet().getCreatureByRealm(m)));
                break;
            case 4:
                YellowDice y = new YellowDice(i);
                player.getScoreSheet().getLion().makeMove(new Move(y,player.getScoreSheet().getCreatureByRealm(y)));
                break;
        }
        return true;
    }

}

