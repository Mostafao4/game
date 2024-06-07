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
        if(getActivePlayer().isTestFlag()){
            int i=0;
            for(Dice d : getAllDice()){
                if(d.getDiceStatus()==DiceStatus.FORGOTTEN_REALM){
                    i++;
                }
            }
            Dice[] dout = new Dice[i];
            for(int j = 0;j<i;j++){
                if(getAllDice()[i].getDiceStatus() == DiceStatus.FORGOTTEN_REALM){
                    dout[j]=getAllDice()[i];
                }
            }
            return dout;
        }
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
        player.setTestFlag(true);
        for (Dice value : getAvailableDice()) {
            if(value.getValue() < getActivePlayer().getSelectedDice().getValue())
                value.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
        }
        getActivePlayer().getSelectedDice().setDiceStatus(DiceStatus.POWER_SELECTED);

        return true;
    }

    public Move chooseDie() {
        boolean bool = possibleMovesForArrayOfDice(getActivePlayer(), getAvailableDice());
        if(!bool){
            System.out.println("No possible moves for available dice");
            return null;
        }
        System.out.println("Select a die: ");
        int i = takeNumberInput();
        while (i >= getAvailableDice().length || i<0){
            System.out.println("Please enter a valid number");
            i = takeNumberInput();
        }
        getActivePlayer().setSelectedDice(getAvailableDice()[i]);
        Move move = new Move(getActivePlayer().getSelectedDice(), getActivePlayer().getScoreSheet().getCreatureByRealm(getActivePlayer().getSelectedDice()), MoveType.AVAILABLE);
        boolean b = makeMove(getActivePlayer(), move);
        for (Dice value : getAvailableDice()) {
            if(value.getValue() < getActivePlayer().getSelectedDice().getValue())
                value.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
        }
        getActivePlayer().getSelectedDice().setDiceStatus(DiceStatus.TURN_SELECTED);
        if (b) {
            getGameBoard().getGameStatus().incrementTurn();
        }
        return move;
    }


    public Move chooseForgottenRealm(){
        boolean bool = possibleMovesForArrayOfDice(getPassivePlayer(), getForgottenRealmDice());
        if(!bool){
            System.out.println("No possible moves for Forgotten Realm dice");
            return null;
        }
        System.out.println("\n"+getPassivePlayer().getPlayerName()+ ", select a die from the forgotten realm: ");
        printDice(getForgottenRealmDice());
        int i = takeNumberInput();
        while(i<0 || i>=getForgottenRealmDice().length){
            System.out.println("Please enter a valid number");
            i=takeNumberInput();
        }
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
                    System.out.print("\u001B[31m"+i+": R" + d.getValue()+"     "+"\u001B[0m");
                    break;
                case GREEN:
                    System.out.print("\u001B[32m"+i+": G" + d.getValue()+"     "+"\u001B[0m");
                    break;
                case BLUE:
                    System.out.print("\u001B[34m"+i+": B" + d.getValue()+"     "+"\u001B[0m");
                    break;
                case MAGENTA:
                    System.out.print("\u001B[35m"+i+": M" + d.getValue()+"     "+"\u001B[0m");
                    break;
                case YELLOW:
                    System.out.print("\u001B[33m"+i+": Y" + d.getValue()+"     "+"\u001B[0m");
                    break;
                case WHITE:
                    System.out.print("\u001B[37.m"+i+": A" + d.getValue()+"\u001B[0m");
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
                        invalidMoveHelper(player, move, Realm.RED);
                        break;
                    }
                    System.out.println(player.getScoreSheet().getDragon());
                    System.out.println("Select a dragon to attack with attack value: " + move.getDice().getValue());
                    int i = takeNumberInput();
                    while(i<1 || i>4){
                        System.out.println("Please enter a valid number");
                        i = takeNumberInput();
                    }
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
                GreenDice f;
                if(move.getMoveType() == MoveType.BONUS){
                    f = (GreenDice)move.getDice();
                }
                else {
                    int x = move.getDice().getValue();
                    int y = getAllDice()[5].getValue();
                    f = new GreenDice(x + y);
                }
                try {
                    b = (player.getScoreSheet().getGaia().makeMove(new Move(f, player.getScoreSheet().getGaia())));
                }
                catch(InvalidMoveException e){
                    System.out.println(e.getMessage());
                    invalidMoveHelper(player, move, Realm.GREEN);
                }
                break;
            case BLUE:
                try {
                    b=(player.getScoreSheet().getHydra().makeMove(move));
                }
                catch(InvalidMoveException e){
                    System.out.println(e.getMessage());
                    invalidMoveHelper(player, move, Realm.BLUE);
                }
                break;
            case MAGENTA:
                try {
                    b = (player.getScoreSheet().getPhoenix().makeMove(move));
                }
                catch(InvalidMoveException | InvalidDiceSelectionException e){
                    System.out.println(e.getMessage());
                    invalidMoveHelper(player, move, Realm.MAGENTA);
                }
                break;
            case YELLOW:
                try {
                    b=(player.getScoreSheet().getLion().makeMove(move));
                }
                catch(InvalidMoveException e){
                    System.out.println(e.getMessage());
                    invalidMoveHelper(player, move, Realm.YELLOW);
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

    public boolean possibleMovesForArrayOfDice(Player player, Dice[] dice){
        int c = 0;
        for (Dice d : dice){
            if(getPossibleMovesForADie(player, d).length == 0){
                c++;
            }
        }
        return !(c == dice.length);
    }


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
        for (int i=0;i<powers.length;i++){
            powers[i] = new TimeWarp();
        }
        return powers;
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers(Player player) {
        int x = getActivePlayer().getArcaneBoostCount();
        ArcaneBoost[] powers = new ArcaneBoost[x];
        for(int i=0;i<powers.length;i++){
            powers[i] = new ArcaneBoost();
        }
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
            if(getGameStatus().getRound()>6){
                break;
            }
            while (getGameStatus().getPartOfRound() < 2) {
                if(getGameStatus().getRound()>6){
                    break;
                }
                while (getGameStatus().getTurn() <= 3 && thereAreAvailableDice()) {
                    if(getGameStatus().getRound()>6){
                        break;
                    }
                    startTurn();
                    timeWarpPrompt();
                    chooseDieHelper();
                    if (getGameStatus().getTurn() == 4 || !thereAreAvailableDice()) {
                        System.out.println("\n" + getActivePlayer().getPlayerName() + ", your time as active spellcaster is over. Press 1 to display Grimoire, 0 to end turn.");
                        int i = takeNumberInput();
                        while (i != 0 && i != 1) {
                            System.out.println("Enter a valid number");
                            i = takeNumberInput();
                        }
                        if (i == 1) {
                            System.out.println("\n" + getActivePlayer().getPlayerName().toUpperCase() + "'S GRIMOIRE:");
                            System.out.println(getActivePlayer().getScoreSheet());
                        }
                    }
                }
                System.out.println("\n" + getPassivePlayer().getPlayerName() + ", you must choose a die from the Forgotten Realm.");
                System.out.println("Enter 1 to display Grimoire, 0 to proceed.");
                int i = takeNumberInput();
                while (i != 0 && i != 1) {
                    System.out.println("Enter a valid number");
                    i = takeNumberInput();
                }
                if (i == 1) {
                    System.out.println("\n+-----------------------------------------------------------------------+");
                    System.out.println("\n" + getPassivePlayer().getPlayerName().toUpperCase() + "'S GRIMOIRE:");
                    System.out.println(getPassivePlayer().getScoreSheet());
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
            System.out.println("Please enter a valid number");
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
        getGameStatus().setStatus(false);
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
        while(getActivePlayer().getTimeWarpCount()>0) {
            String s;
            if(getActivePlayer().getTimeWarpCount() == 1)
                s = " Time Warp";
            else
                s = " Time Warps";
            System.out.println("\nYou have "+getActivePlayer().getTimeWarpCount()+s);
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
            else{
                break;
            }
        }
    }
    public void arcaneBoostPrompt(){
        resetDice();
        while(getActivePlayer().getArcaneBoostCount()>0) {
            String s;
            if(getActivePlayer().getArcaneBoostCount() == 1) {
                s = " Arcane Boost";
            }
            else {
                s = " Arcane Boosts";
            }
            System.out.println("\n"+getActivePlayer().getPlayerName() + " : You have "+ getActivePlayer().getArcaneBoostCount()+s);
            System.out.println("Press 1 to use Arcane Boost, 0 to proceed");
            int i = takeNumberInput();
            while(i!=0 && i!=1){
                i = takeNumberInput();
            }
            if (i==1) {
                System.out.println("Choose a die to perform your Arcane Boost");
                printDice(getArcaneBoostDice());
                useArcaneBoost(getActivePlayer());
            }
            else {
                break;
            }
        }
        resetDice();
        while(getPassivePlayer().getArcaneBoostCount()>0) {
            String h;
            if(getPassivePlayer().getArcaneBoostCount() == 1)
                h = " Arcane Boost";
            else
                h = " Arcane Boosts";
            System.out.println("\n"+getPassivePlayer().getPlayerName() + " : You have "+ getPassivePlayer().getArcaneBoostCount()+h);
            System.out.println("Press 1 to use Arcane Boost, 0 to proceed");
            int j = takeNumberInput();
            while(j!=0 && j!=1){
                j = takeNumberInput();
            }
            if (j==1) {
                System.out.println("Choose a die to perform your Arcane Boost");
                printDice(getArcaneBoostDice());
                useArcaneBoost(getPassivePlayer());
            }
            else{
                break;
            }
        }
    }
    public void getRoundReward(){
        switch (getGameBoard().getGameStatus().getRound()) {
            case 1:
            case 3:
                getActivePlayer().addTimeWarpCount();
                System.out.println(getActivePlayer().getPlayerName()+", you received a Time Warp! You now have: "+getTimeWarpPowers(getActivePlayer()).length);
                break;
            case 2:
                getActivePlayer().addArcaneBoostCount();
                System.out.println(getActivePlayer().getPlayerName()+", you received an Arcane Boost! You now have: "+getArcaneBoostPowers(getActivePlayer()).length);
                break;
            case 4:
                System.out.println(getActivePlayer().getPlayerName()+", you received an Essence Bonus!");
                useEssenceBonus();
                break;
            default:
                System.out.println("You did not receive any reward");
        }
    }
    public void useBonus (Player player, Bonus bonus, int i)  {
        Realm r = bonus.getRealm();
        Dice d;
        switch (r) {
            case RED:
                d = new RedDice(i);
                if(player.getScoreSheet().getDragon().getAllPossibleMoves().length == 0){
                    System.out.println("No possible moves for this realm");
                    return;
                }
                break;
            case GREEN:
                d = new GreenDice(i);
                if(player.getScoreSheet().getGaia().getAllPossibleMoves().length == 0){
                    System.out.println("No possible moves for this realm");
                    return;
                }
                break;
            case BLUE:
                d = new BlueDice(i);
                if(player.getScoreSheet().getHydra().getAllPossibleMoves().length == 0){
                    System.out.println("No possible moves for this realm");
                    return;
                }
                break;
            case MAGENTA:
                d = new MagentaDice(i);
                if(player.getScoreSheet().getPhoenix().getAllPossibleMoves().length == 0){
                    System.out.println("No possible moves for this realm");
                    return;
                }
                break;
            case YELLOW:
                d = new YellowDice(i);
                if(player.getScoreSheet().getLion().getAllPossibleMoves().length == 0){
                    System.out.println("No possible moves for this realm");
                    return;
                }
                break;
            default:
                //throw new PlayerActionException();
                d = null;
        }
        if(d!=null) {
            makeMove(player, new Move(d, player.getScoreSheet().getCreatureByRealm(d), MoveType.BONUS));
        }
    }
    public void useArcaneBoost(Player player){
        player.subtractArcaneBoostCount();
        int i = takeNumberInput();
        while(i<0 || i>= getArcaneBoostDice().length){
            System.out.println("Please enter a valid number");
            i = takeNumberInput();
        }
        Dice d = getArcaneBoostDice()[i];
        Move m = new Move(d, player.getScoreSheet().getCreatureByRealm(d), MoveType.ARCANE_BOOST);
        makeMove(player, m);
        getAllDice()[i].setDiceStatus(DiceStatus.POWER_SELECTED);
        //getBonus(checkReward(m, player), player);

    }
    public void useEssenceBonus(){
        System.out.println("Select a realm to attack");
        System.out.println("\u001B[31mR0\u001B[0m"+"  |  "+"\u001B[32mG1\u001B[0m"+"  |  "+"\u001B[34mB2\u001B[0m"+"  |  "+"\u001B[35mM3\u001B[0m"+"  |  "+"\u001B[33mY4\u001B[0m");
        int i = takeNumberInput();
        while(i<0 || i>4){
            i = takeNumberInput();
        }
        Realm r;
        switch(i){
            case 0:
                r = Realm.RED;
                break;
            case 1:
                r = Realm.GREEN;
                break;
            case 2:
                r = Realm.BLUE;
                break;
            case 3:
                r = Realm.MAGENTA;
                break;
            case 4:
                r = Realm.YELLOW;
                break;
            default:
                r = null;
        }
        useBonusHelper(new Bonus(r), getActivePlayer());
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


    public void useReward(Reward reward, Player player){

        if(reward instanceof TimeWarp){
            player.addTimeWarpCount();
            System.out.println(player.getPlayerName()+", you received a Time Warp! You now have: "+player.getTimeWarpCount());
        }
        else if(reward instanceof ArcaneBoost){
            player.addArcaneBoostCount();
            System.out.println(player.getPlayerName()+", you received an Arcane Boost! You now have: "+player.getArcaneBoostCount());
        }
        else if(reward instanceof ElementalCrest) {
            player.getGameScore().addElementalCrest((ElementalCrest) reward);
            System.out.println(player.getPlayerName() + ", you received an Elemental Crest! You now have: " + player.getElementalCrest().length);
        }
        else if(reward instanceof Bonus){
            System.out.println("\n+-----------------------------------------------------------------------+");
            System.out.println("\n" + player.getPlayerName().toUpperCase() + "'S GRIMOIRE:");
            System.out.println(player.getScoreSheet());
            Realm r = ((Bonus) reward).getRealm();
            System.out.println("\n"+player.getPlayerName()+", you received a " + r + " Bonus!");
            useBonusHelper(reward,player);
        }
    }
    public void useBonusHelper(Reward reward, Player player) {
            Realm r = ((Bonus)reward).getRealm();
            System.out.println("Choose an attack value");
            int att = takeNumberInput();
            if(r == Realm.GREEN){
                while(att<2 || att>12) {
                    System.out.println("Please enter a valid number");
                    att = takeNumberInput();
                }
            }
            else{
                while(att<1 || att>6) {
                    System.out.println("Please enter a valid number");
                    att = takeNumberInput();
                }
            }
            useBonus(player, (Bonus) reward, att);
    }
    public void chooseForgottenRealmHelper(){
        chooseForgottenRealm();
        //getBonus(checkReward(move, getPassivePlayer()), getPassivePlayer());
    }


    public void chooseDieHelper(){
        chooseDie();
        //getBonus(checkReward(move, getActivePlayer()), getActivePlayer());
    }
    public int takeNumberInput(){
        while(true) {
            String input = scanner.nextLine();
            if (!input.matches("[0-9]+") || input.length()>9) {
                System.out.println("Please enter a valid number");
                continue;
            }
            return Integer.parseInt(input);
        }
    }

    public void invalidMoveHelper(Player player, Move move, Realm realm){
        if(move.getMoveType() == null){
            return;
        }
        switch(move.getMoveType()){
            case AVAILABLE:
                if(getAvailableDice().length == 1){
                    break;
                }
                printDice(getAvailableDice());
                chooseDieHelper();
                break;
            case FORGOTTEN_REALM:
                if(getForgottenRealmDice().length == 1){
                    break;
                }
                //printDice(getForgottenRealmDice());
                chooseForgottenRealmHelper();
                break;
            case ARCANE_BOOST:
                printDice(getArcaneBoostDice());
                useArcaneBoost(player);
                break;
            case BONUS:
                Bonus b = new Bonus(realm);
                useBonusHelper(b,player);
                break;
            case ESSENCE_BONUS:
                useEssenceBonus();
                break;
            default:
                System.out.println("DEFAULT");
        }
    }

//      || REWARDS ||
}

