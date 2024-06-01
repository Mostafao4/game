package game.engine;

import game.collectibles.*;
import game.creatures.Realm;
import game.dice.*;
import game.exceptions.InvalidDiceSelectionException;
import game.exceptions.InvalidMoveException;
import game.exceptions.PlayerActionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CLIGameController extends GameController {
    private GameBoard gameBoard;
    private static Scanner scanner = new Scanner(System.in);
    public CLIGameController() {
        this.gameBoard = new GameBoard();
    }




//      || DICE ||

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
            for(Dice d : getAllDice())
                if(d.getDiceStatus()!=DiceStatus.TURN_SELECTED)
                    c++;
            Dice[] out = new Dice[c];
            int i=0;
            for(Dice d : getAllDice())
                if(d.getDiceStatus()!=DiceStatus.TURN_SELECTED)
                    out[i++]=d;
            return out;
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

    public Move chooseDie() {
        System.out.println("Select a die: ");
        int i = takeNumberInput();
        while (i >= getAvailableDice().length || i<0){
            System.out.println("Please enter a valid number");
            i = takeNumberInput();
        }
        selectDice(getAvailableDice()[i], getActivePlayer());
        Move move = new Move(getActivePlayer().getSelectedDice(), getActivePlayer().getScoreSheet().getCreatureByRealm(getActivePlayer().getSelectedDice()), MoveType.AVAILABLE);
        makeMove(getActivePlayer(), move);
        getGameBoard().getGameStatus().incrementTurn();
        return move;
    }
    public Move chooseForgottenRealm(){
        System.out.println(getPassivePlayer().getPlayerName()+ ", select a die from the forgotten realm: ");
        printDice(getForgottenRealmDice());
        int i = takeNumberInput();
        Move move = new Move(getForgottenRealmDice()[i], getPassivePlayer().getScoreSheet().getCreatureByRealm(getForgottenRealmDice()[i]),MoveType.FORGOTTEN_REALM);
        makeMove(getPassivePlayer(), move);
        return move;
    }
    public boolean thereAreAvailableDice(){
        return getAvailableDice().length != 0;
    }
    public static void printDice (Dice[] dice){
        for(int i=0;i<dice.length;i++){
            Dice d = dice[i];
            switch(d.getRealm()){
                case RED:
                    System.out.print("R" + i + ": " + d.getValue()+"     ");
                    break;
                case GREEN:
                    System.out.print("G" + i + ": " + d.getValue()+"     ");
                    break;
                case BLUE:
                    System.out.print("B" + i + ": " + d.getValue()+"     ");
                    break;
                case MAGENTA:
                    System.out.print("M" + i + ": " + d.getValue()+"     ");
                    break;
                case YELLOW:
                    System.out.print("Y" + i + ": " + d.getValue()+"     ");
                    break;
                case WHITE:
                    System.out.print("A" + i + ": " + d.getValue());
                    break;
                default:
                    System.out.print("error");
            }
        }
        System.out.println();
    }
    public void resetDice(){
        for(Dice d : getAllDice()){
            d.setDiceStatus(DiceStatus.AVAILABLE);
        }
    }
    public Dice[] getArcaneBoostDice(){
        int c=0;
        for(Dice d : getAllDice()){
            if(d.getDiceStatus()!=DiceStatus.POWER_SELECTED){
                c++;
            }
        }
        Dice[] out = new Dice[c];
        int i=0;
        for(Dice d : getAllDice()){
            if(d.getDiceStatus()!=DiceStatus.POWER_SELECTED){
                out[i++] = d;
            }
        }
        return out;
    }

//      || DICE ||

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//      || MOVES ||

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
        Move[] out = new Move[allPossibleMoves.size()];
        allPossibleMoves.toArray(out);
        return out;


            // Remove extra green dice if more than one is available

            // Calculate possible moves for each die
//            for (Dice die : availableDice) {
//                Move[] possibleMovesForDie = getPossibleMovesForADie(player, die);
//                allPossibleMoves.addAll(Arrays.asList(possibleMovesForDie));
//            }

            // Convert list to array


}

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
                m = player.getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(dice.getValue() + getAllDice()[5].getValue()));
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
    public boolean makeMove(Player player, Move move)  {
        boolean b=false;
        switch(move.getDice().getRealm()){
            case RED:
                if (((RedDice) (move.getDice())).getDragonNumber() == 0) {
                    if(getPossibleMovesForADie(player,move.getDice()).length == 0){
                        System.out.println("No possible moves for this die");
                        invalidMoveHelper(player, move);
                        break;
                    }
                    System.out.println(player.getScoreSheet().getDragon());
                    System.out.println("Select a dragon to attack with attack value: " + move.getDice().getValue());
                    int i = takeNumberInput();
                    RedDice d = new RedDice(move.getDice().getValue());
                    d.selectsDragon(i);
                    try {
                        b = (player.getScoreSheet().getDragon().makeMove(new Move(d, player.getScoreSheet().getDragon())));
                    }
                    catch (PlayerActionException e) {
                        System.out.println(e.getMessage());
                        makeMove(player, move);
                    }
                }
                else {
                    try {
                        b = player.getScoreSheet().getDragon().makeMove(move);
                    } catch (PlayerActionException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;

            case GREEN:
                int x = move.getDice().getValue();
                int y = getAllDice()[5].getValue();
                Dice f = new GreenDice(x + y);
                try {
                    b = (player.getScoreSheet().getGaia().makeMove(new Move(f, player.getScoreSheet().getGaia())));
                }
                catch(InvalidMoveException e){
                    System.out.println(e.getMessage());
                    invalidMoveHelper(player, move);
                }
                break;
            case BLUE:
                try {
                    b=(player.getScoreSheet().getHydra().makeMove(move));
                }
                catch(InvalidMoveException e){
                    System.out.println(e.getMessage());
                    invalidMoveHelper(player, move);
                }
                break;
            case MAGENTA:
                try {
                    b = (player.getScoreSheet().getPhoenix().makeMove(move));
                }
                catch(InvalidMoveException | InvalidDiceSelectionException e){
                    System.out.println(e.getMessage());
                    invalidMoveHelper(player, move);
                }
                break;
            case YELLOW:
                try {
                    b=(player.getScoreSheet().getLion().makeMove(move));
                }
                catch(InvalidMoveException e){
                    System.out.println(e.getMessage());
                    invalidMoveHelper(player, move);
                }
                break;
            case WHITE:
                System.out.println("Choose a realm to attack");
                System.out.println("Red(0)  |  Green(1)  |  Blue(2)  |  Magenta(3)  |  Yellow(4)");
                boolean flag = false;
                Move whiteMove = move;
                int v = move.getDice().getValue();
                while(!flag) {
                    int q = takeNumberInput();
                    System.out.println(q);
                    switch (q) {
                        case 0:
                            whiteMove = new Move(new RedDice(v), player.getScoreSheet().getDragon(), move.getMoveType());
                            flag = true;
                            break;
                        case 1:
                            whiteMove = new Move(getAllDice()[1], player.getScoreSheet().getGaia(), move.getMoveType());
                            flag = true;
                            break;
                        case 2:
                            whiteMove = new Move(new BlueDice(v), player.getScoreSheet().getHydra(), move.getMoveType());
                            flag = true;
                            break;
                        case 3:
                            whiteMove = new Move(new MagentaDice(v), player.getScoreSheet().getPhoenix(), move.getMoveType());
                            flag = true;
                            break;
                        case 4:
                            whiteMove = new Move(new YellowDice(v), player.getScoreSheet().getLion(), move.getMoveType());
                            flag = true;
                            break;
                        default:
                            System.out.println("Please enter a valid number");
                    }
                }
                b = makeMove(player, whiteMove);
                break;

            default:
                break;
        }
        getReward(checkReward(move,player),player);
        return b;
    }
//    public boolean whiteMove(Player player,int i,int g) {
//        Move move;
//        Reward[] r;
//        switch(g) {
//            case 0:
//                System.out.println(player.getScoreSheet().getDragon());
//                System.out.println("Select a dragon to attack with value "+i);
//                int in = scanner.nextInt();
//                RedDice d = new RedDice(i);
//                move = new Move(d, player.getScoreSheet().getDragon(), in);
//                try {
//                    player.getScoreSheet().getDragon().makeMove(move);
//                }
//                catch(InvalidMoveException e){
//                    System.out.println(e.getMessage());
//                    printDice(getAvailableDice());
//                    chooseDieHelper();
//                }
//                catch(PlayerActionException e){
//                    System.out.println(e.getMessage());
//                    whiteMove(player,i,g);
//                }
//                r = checkReward(move,player);
//                getReward(r, player);
//                break;
//            case 1:
//                GreenDice f = new GreenDice(i + getAllDice()[1].getValue());
//                player.getScoreSheet().getGaia().makeMove(new Move(f, player.getScoreSheet().getGaia()));
//                move = new Move(f, player.getScoreSheet().getGaia());
//                r = checkReward(move,player);
//                getReward(r,player);
//                break;
//            case 2:
//                BlueDice b = new BlueDice(i);
//                player.getScoreSheet().getHydra().makeMove(new Move(b,player.getScoreSheet().getHydra()));
//                move = new Move(b,player.getScoreSheet().getHydra());
//                r = checkReward(move,player);
//                getReward(r,player);
//                break;
//            case 3:
//                MagentaDice c = new MagentaDice(i);
//                player.getScoreSheet().getPhoenix().makeMove(new Move(c,player.getScoreSheet().getPhoenix()));
//                move = new Move(c,player.getScoreSheet().getPhoenix());
//                r = checkReward(move,player);
//                getReward(r,player);
//                break;
//            case 4:
//                YellowDice y = new YellowDice(i);
//                player.getScoreSheet().getLion().makeMove(new Move(y,player.getScoreSheet().getLion()));
//                move = new Move(y,player.getScoreSheet().getLion());
//                r = checkReward(move,player);
//                getReward(r,player);
//                break;
//        }
//        return true;
//    }

//      || MOVES ||

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//      || GETTERS ||

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

//      || GETTERS ||

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//      || GAME LOOP ||

    @Override
    public void startGame() {
        gameBoard = new GameBoard();
        System.out.println("Welcome to Dice Realms: Quest for Elemental Crests!!!");
        System.out.println("Enter Player 1 name");
        String s1 = scanner.nextLine();
        System.out.println("Enter Player 2 name");
        String s2 = scanner.nextLine();
        gameBoard.setPlayer1(new Player(s1,PlayerStatus.ACTIVE));
        gameBoard.setPlayer2(new Player(s2,PlayerStatus.PASSIVE));
        gameLoop();
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

    public void gameLoop(){
        while (getGameStatus().getRound() <= 6) {
            while(getGameStatus().getPartOfRound()<2) {
                while (getGameStatus().getTurn() <= 3 && thereAreAvailableDice()) {
                    startTurn();
                    timeWarpPrompt();
                    chooseDieHelper();
                    if (getGameStatus().getTurn() == 4 || !thereAreAvailableDice()) {
                        System.out.println("\n"+getActivePlayer().getPlayerName()+", your time as active spellcaster is over. Press 1 to display Grimoire, 0 to end turn.");
                        int i = takeNumberInput();
                        while(i!=0 && i!=1){
                            System.out.println("Enter a valid number");
                            i = takeNumberInput();
                        }
                        if(i==1) {
                            System.out.println("\n" + getActivePlayer().getPlayerName().toUpperCase() + "'S GRIMOIRE:");
                            System.out.println(getActivePlayer().getScoreSheet());
                        }
                    }
                }
                chooseForgottenRealmHelper();
                arcaneBoostPrompt();
                endTurn();
            }
        }
        endGame();
    }
    public void startTurn(){
        System.out.println("\n+-----------------------------------------------------------------------+");
        if(getGameStatus().getTurn()==1)
            getRoundReward();
        System.out.println("Round: " + getGameBoard().getGameStatus().getRound());
        System.out.println("Turn: " + getGameBoard().getGameStatus().getTurn());
        System.out.println("Current Player: " + getActivePlayer().getPlayerName()+"\n");
        System.out.println("Press 1 to display Grimoire, 0 to proceed");
        int i = takeNumberInput();
        while(i!=0 && i!=1){
            System.out.println("Enter a valid number");
            i = takeNumberInput();
        }
        if(i==1) {
            System.out.println("\n" + getActivePlayer().getPlayerName().toUpperCase() + "'S GRIMOIRE:");
            System.out.println(getActivePlayer().getScoreSheet());
        }
        rollDice();
        printDice(getAvailableDice());

    }
    public void endTurn(){
        resetDice();
        ((RedDice)getAllDice()[0]).resetDragonNumber();
        switchPlayer();
        if (getGameStatus().getPartOfRound() == 0)
            getGameStatus().incrementPartOfRound();
        else {
            getGameStatus().incrementRound();
            getGameStatus().resetPartofRound();
        }
    }
    public void endGame(){
        scanner.close();
        int score1 = getGameScore(getGameBoard().getPlayer1()).getScore();
        int score2 = getGameScore(getGameBoard().getPlayer2()).getScore();
        System.out.println(getGameBoard().getPlayer1().getPlayerName()+" scored "+score1+" points and collected "+getGameBoard().getPlayer1().getGameScore().getElementalCrest().length+" Elemental Crests");
        System.out.println(getGameBoard().getPlayer2().getPlayerName()+" scored "+score2+" points and collected "+getGameBoard().getPlayer2().getGameScore().getElementalCrest().length+" Elemental Crests");
        if (score1 > score2)
            System.out.println(getGameBoard().getPlayer1().getPlayerName() + " has won the game!");
        else
            System.out.println(getGameBoard().getPlayer2().getPlayerName() + " has won the game!");
    }

//      || GAME LOOP ||

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//      || REWARDS ||

    public void timeWarpPrompt(){
        if(getActivePlayer().getTimeWarpCount()>0) {
            System.out.println("Press 1 to use Time Warp, 0 to proceed");
            int i = takeNumberInput();
            while(i!=0 && i!=1){
                i = takeNumberInput();
            }
            if (i==1) {
                getActivePlayer().subtractTimeWarpCount();
                rollDice();
                printDice(getAvailableDice());
            }
        }
    }
    public void arcaneBoostPrompt(){
        resetDice();
        while(getActivePlayer().getArcaneBoostCount()>0) {
            System.out.println(getActivePlayer().getPlayerName() + ": Press 1 to use Arcane Boost, 0 to proceed");
            int i = takeNumberInput();
            while(i!=0 && i!=1){
                i = takeNumberInput();
            }
            if (i==1) {
                System.out.println("Choose a die to perform your Arcane Boost");
                printDice(getArcaneBoostDice());
                useArcaneBoost(getActivePlayer());
            }
        }
        resetDice();
        while(getPassivePlayer().getArcaneBoostCount()>0) {
            System.out.println(getPassivePlayer().getPlayerName() + ": Press 1 to use Arcane Boost, 0 to proceed");
            int j = takeNumberInput();
            while(j!=0 && j!=1){
                j = takeNumberInput();
            }
            if (j==1) {
                System.out.println("Choose a die to perform your Arcane Boost");
                printDice(getArcaneBoostDice());
                useArcaneBoost(getPassivePlayer());
            }
        }
    }
    public void getRoundReward(){
        switch (getGameBoard().getGameStatus().getRound()) {
            case 1:
            case 3:
                getActivePlayer().addTimeWarpCount();
                System.out.println(getActivePlayer().getPlayerName()+" received a Time Warp!");
                break;
            case 2:
                getActivePlayer().addArcaneBoostCount();
                System.out.println(getActivePlayer().getPlayerName()+" You received an Arcane Boost!");
                break;
            case 4:
                System.out.println(getActivePlayer().getPlayerName()+" You received an Essence Bonus!");
                //useEssenceBonus();
                break;
            default:
                System.out.println("You did not receive any reward");
        }
    }
    public void useBonus (Player player,Bonus bonus,int i)  {
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
        if(d!=null)
            makeMove(player, new Move(d, player.getScoreSheet().getCreatureByRealm(d), MoveType.BONUS));
    }
    public void useArcaneBoost(Player player)  {
        int i = takeNumberInput();
        Dice d = getAllDice()[i];
        Move m = new Move(d, player.getScoreSheet().getCreatureByRealm(d), MoveType.ARCANE_BOOST);
        makeMove(player, m);
        getAllDice()[i].setDiceStatus(DiceStatus.POWER_SELECTED);
        player.subtractArcaneBoostCount();
        //getBonus(checkReward(m, player), player);

    }
    public void useEssenceBonus(){
        System.out.println("Select a realm to attack");
        System.out.println("R0  |  G1  |  B2  |  M3  |  Y4");
        int i = takeNumberInput();
        System.out.println("Select an attack value");
        System.out.println("1  |  2  |  3  |  4  |  5  |  6");
        int j = takeNumberInput();



    }
    public Reward[] checkReward(Move move, Player player){
        Realm r = move.getDice().getRealm();
        switch(r){
            case RED:
                return player.getScoreSheet().getDragon().checkReward();
            case GREEN:
                return player.getScoreSheet().getGaia().checkReward();
            case BLUE:
                return player.getScoreSheet().getHydra().checkReward();
            case MAGENTA:
                return player.getScoreSheet().getPhoenix().checkReward();
            case YELLOW:
                return player.getScoreSheet().getLion().checkReward();
            default:
                return null;
        }

    }
    public void getReward(Reward[] r, Player player){
        if(r!=null) {
            for (Reward reward : r) {
                if (reward != null) {
                    useReward(reward, player);
                }
            }
        }
    }

    public void getBonus(Reward[] r, Player player){
        if(r!=null) {
            for (Reward reward : r) {
                if (reward instanceof Bonus) {
                    useBonusHelper(reward, player);
                }
            }
        }
    }


    public void useReward(Reward reward, Player player){

        if(reward instanceof TimeWarp){
            System.out.println("You received a Time Warp!");
            player.addTimeWarpCount();
        }
        else if(reward instanceof ArcaneBoost){
            System.out.println("You received an Arcane Boost!");
            player.addArcaneBoostCount();
        }
        else if(reward instanceof ElementalCrest)
            player.getGameScore().addElementalCrest((ElementalCrest)reward);
        else if(reward instanceof Bonus){
            System.out.println("\n+-----------------------------------------------------------------------+");
            System.out.println("\n" + player.getPlayerName().toUpperCase() + "'S GRIMOIRE:");
            System.out.println(player.getScoreSheet());
            useBonusHelper(reward,player);
        }
    }
    public void useBonusHelper(Reward reward, Player player) {
            Realm r = ((Bonus) reward).getRealm();
            System.out.println("You received a " + r + " Bonus!");
            System.out.println("Choose an attack value");
            int att = takeNumberInput();
            while(att<1 || att>6) {
                att = takeNumberInput();
            }
            useBonus(player, (Bonus) reward, att);
    }
    public void chooseForgottenRealmHelper(){
        System.out.println("\n"+getPassivePlayer().getPlayerName()+", you must choose a die from the Forgotten Realm.");
        System.out.println("Enter 1 to display Grimoire, 0 to proceed.");
        int i = takeNumberInput();
        while(i!=0 && i!=1){
            System.out.println("Enter a valid number");
            i = takeNumberInput();
        }
        if(i==1) {
            System.out.println("\n+-----------------------------------------------------------------------+");
            System.out.println("\n" + getPassivePlayer().getPlayerName().toUpperCase() + "'S GRIMOIRE:");
            System.out.println(getPassivePlayer().getScoreSheet());
        }
        Move move = chooseForgottenRealm();
        //getBonus(checkReward(move, getPassivePlayer()), getPassivePlayer());
    }


    public void chooseDieHelper(){
        Move move = chooseDie();
        //getBonus(checkReward(move, getActivePlayer()), getActivePlayer());
    }
    public int takeNumberInput(){
        while(true) {
            String input = scanner.nextLine();
            if (!input.matches("[0-9]+")) {
                System.out.println("Please enter a valid number");
                continue;
            }
            return Integer.parseInt(input);
        }
    }

    public void invalidMoveHelper(Player player, Move move){
        switch(move.getMoveType()){
            case AVAILABLE:
                printDice(getAvailableDice());
                chooseDieHelper();
                break;
            case FORGOTTEN_REALM:
                printDice(getForgottenRealmDice());
                chooseForgottenRealmHelper();
                break;
            case ARCANE_BOOST:
                printDice(getArcaneBoostDice());
                useArcaneBoost(player);
                break;
            case BONUS:
                break;
            case ESSENCE_BONUS:
                System.out.println("ESSENCE_BONUS");
                break;
            default:
                System.out.println("DEFAULT");
        }
    }

//      || REWARDS ||
}

