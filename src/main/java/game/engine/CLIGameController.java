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
            endTurn();
            return null;
        }
        int i=(int)(Math.random()*getAvailableDice().length);
        System.out.println("Select a die: ");
        if (getActivePlayer().getPlayerType().equals("human")){           
             i = takeNumberInput();
        }
       else{
            int r=FindRealm(getAvailableDice(),Realm.RED);
            int g=FindRealm(getAvailableDice(),Realm.GREEN);
            int b=FindRealm(getAvailableDice(),Realm.BLUE);
            int m=FindRealm(getAvailableDice(),Realm.MAGENTA);
            int y=FindRealm(getAvailableDice(),Realm.YELLOW);
            int w=FindRealm(getAvailableDice(),Realm.WHITE);
            int c=0;
            for(int j=0;j<getAvailableDice().length;j++){
                if (getAvailableDice()[j].getValue()==6)
                    c++;
            }
            if(getGameStatus().getTurn()==1 && c>=3){
                if((y!=-1) && (getAvailableDice()[y].getValue()>=4) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[y]).length>0))
                    i=y;  
                else
                    if((r!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[r]).length>0))
                        i=r; 
                    else
                        if((w!=-1) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[w]).length>0))
                            i=w;
                        else
                            if((g!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[g]).length>0))
                                i=g; 
                            else
                                if((w!=-1) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[w]).length>0))
                            i=w;
                                else
                                    if((m!=-1) && (getAvailableDice()[m].getValue()==6) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[m]).length>0))
                                        i=m; 
                                    else
                                        i=(int)(Math.random()*getAvailableDice().length);
                }
            else
            if(getGameStatus().getTurn()==2 && c>=2){
                if((y!=-1) && (getAvailableDice()[y].getValue()>=4) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[y]).length>0))
                i=y;  
            else
                if((r!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[r]).length>0))
                    i=r; 
                else
                    if((w!=-1) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[w]).length>0))
                        i=w;
                    else
                        if((g!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[g]).length>0))
                            i=g; 
                        else
                            if((w!=-1) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[w]).length>0))
                        i=w;
                            else
                                if((m!=-1) && (getAvailableDice()[m].getValue()==6) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[m]).length>0))
                                    i=m; 
                                else
                                    i=(int)(Math.random()*getAvailableDice().length);
             }
            else
            if(getGameStatus().getTurn()==3 && c>=1){
                if((y!=-1) && (getAvailableDice()[y].getValue()>=4) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[y]).length>0))
                i=y;  
            else
                if((r!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[r]).length>0))
                    i=r; 
                else
                    if((w!=-1) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[w]).length>0))
                        i=w;
                    else
                        if((g!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[g]).length>0))
                            i=g; 
                        else
                            if((w!=-1) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[w]).length>0))
                        i=w;
                            else
                                if((m!=-1) && (getAvailableDice()[m].getValue()==6) && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[m]).length>0))
                                    i=m; 
                                else
                                    i=(int)(Math.random()*getAvailableDice().length);                
       }
       else 
       if((r!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[r]).length>0) && getAvailableDice()[r].getValue()<=3 && getGameStatus().getTurn()==1)
            i=r; 
            else
            if((w!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[w]).length>0)&& getAvailableDice()[w].getValue()<=3 && getGameStatus().getTurn()==1)
                i=w;
            else
            if((g!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[g]).length>0)&& getAvailableDice()[g].getValue()<=3&& getGameStatus().getTurn()==1)
                i=g; 
           

                else
                if((r!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[r]).length>0))
                    i=r; 
                else
                    if((w!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[w]).length>0))
                         i=w;
                    // else if((y!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[y]).length>0)&& getAvailableDice()[y].getValue()>=4)
                    // i=y;
                         else
                        if((g!=-1)  && (getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[g]).length>0))
                            i=g; 
                        else
                            i=(int)(Math.random()*getAvailableDice().length); 
                 }             // else{
                //     if(getAvailableDice().length>1){
                //         boolean flag=true;
                //         while(flag){
                //             i=(int)(Math.random()*getAvailableDice().length);
                //                 if((getPossibleMovesForADie(getActivePlayer(),getAvailableDice()[i])).length>0){
                //                     switch (getAvailableDice()[i].getRealm()) {
                //                     case RED: case GREEN: case WHITE: 
                //                             flag=false;
                //                                 break;
                //                     case YELLOW: 
                //                         if (getAvailableDice()[i].getValue()>=4 ||getPossibleMovesForAvailableDice(getActivePlayer()).length<6) {
                //                             flag=false;
                //                         }
                //                         break;
                //                     case MAGENTA :
                //                         if (getAvailableDice()[i].getValue()>=4 ||getPossibleMovesForAvailableDice(getActivePlayer()).length<6) {
                //                             flag=false;
                //                         }
                //                         break; 
                //                     // case WHITE : 
                //                     //         if (getAvailableDice()[i].getValue()>=1 ||getPossibleMovesForAvailableDice(getActivePlayer()).length<5) {
                //                     //             flag=false;
                //                     //         }
                //                     //             break;
                //                     //case BLUE:
                //                           default:  
                //                                 flag=false;
                                            
                //                             break;
                //                     }
                                        
                //                 }}
                //         }   
                //     else 
                //         i = (int)(Math.random()*getAvailableDice().length);                
                //  System.out.println(i);
                // }
              
                   
           
        while (i >= getAvailableDice().length || i<0){
            System.out.println("Please enter a valid number");
            i = takeNumberInput();
        }
        getActivePlayer().setSelectedDice(getAvailableDice()[i]);
        Move move = new Move(getActivePlayer().getSelectedDice(), getActivePlayer().getScoreSheet().getCreatureByRealm(getActivePlayer().getSelectedDice()), MoveType.AVAILABLE);
        getActivePlayer().getSelectedDice().setDiceStatus(DiceStatus.TURN_SELECTED);
        boolean b = makeMove(getActivePlayer(), move);
        for (Dice value : getAvailableDice()) {
            if(value.getValue() < getActivePlayer().getSelectedDice().getValue())
                value.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
        }
        if (b) {
            getGameBoard().getGameStatus().incrementTurn();
        }
        return move;
    }


    public Move chooseForgottenRealm(){
        int i=0;
        boolean bool = possibleMovesForArrayOfDice(getPassivePlayer(), getForgottenRealmDice());        
        if(!bool){
            System.out.println("No possible moves for Forgotten Realm dice");
            return null;
        }
        else{
        System.out.println("\n"+getPassivePlayer().getPlayerName()+ ", select a die from the forgotten realm: ");
        printDice(getForgottenRealmDice());
        if(getPassivePlayer().getPlayerType().equals("human")){           
            i = takeNumberInput();}
        else{
            int r=FindRealm(getForgottenRealmDice(),Realm.RED);
            int g=FindRealm(getForgottenRealmDice(),Realm.GREEN);
            int b=FindRealm(getForgottenRealmDice(),Realm.BLUE);
            int m=FindRealm(getForgottenRealmDice(),Realm.MAGENTA);
            int y=FindRealm(getForgottenRealmDice(),Realm.YELLOW);
            int w=FindRealm(getForgottenRealmDice(),Realm.WHITE);
            if((y!=-1) && (getForgottenRealmDice()[y].getValue()>=4) && (getPossibleMovesForADie(getPassivePlayer(),getForgottenRealmDice()[y]).length>0))
                i=y;
            else
                    if((r!=-1) &&  (getPossibleMovesForADie(getPassivePlayer(),getForgottenRealmDice()[r]).length>0))
                        i=r;
                    else
                        if((g!=-1) && (getPossibleMovesForADie(getPassivePlayer(),getForgottenRealmDice()[g]).length>0))
                            i=g;
                        else
                            if((w!=-1) && (getPossibleMovesForADie(getPassivePlayer(),getForgottenRealmDice()[w]).length>0))
                                i=w;
                            else 
                                if((m!=-1) && (getForgottenRealmDice()[m].getValue()>=5) && (getPossibleMovesForADie(getPassivePlayer(),getForgottenRealmDice()[m]).length>0))
                                        i=m;
                                    else  
                                        if((b!=-1) && (getForgottenRealmDice()[b].getValue()==6) && (getPossibleMovesForADie(getPassivePlayer(),getForgottenRealmDice()[b]).length>0))
                                            i=b;
                                        else
                                            i=(int)(Math.random()*getForgottenRealmDice().length);
               //int j=0;
        //    for(int j=0;j<getForgottenRealmDice().length;j++){
        //     if((getPossibleMovesForADie(getPassivePlayer(), getForgottenRealmDice()[j])).length>0){
        //         if(getForgottenRealmDice()[i].getValue()<getForgottenRealmDice()[j].getValue()){
        //             i=j;
        //         }
        //     }
        //    }
            //    while (j<getForgottenRealmDice().length) {
                
            // if(((getPossibleMovesForADie(getPassivePlayer(), getForgottenRealmDice()[j])).length>0) && (getForgottenRealmDice()[i].getValue()<getForgottenRealmDice()[j].getValue())){
            //         i = j;
            //     }
            //     j++;
            //   } 
               System.out.println(i);
        }
        while(i<0 || i>=getForgottenRealmDice().length){
            System.out.println("Please enter a valid number");
            i=takeNumberInput();
        }
        Move move = new Move(getForgottenRealmDice()[i], getPassivePlayer().getScoreSheet().getCreatureByRealm(getForgottenRealmDice()[i]),MoveType.FORGOTTEN_REALM);
        if(!getForgottenRealmDice()[i].getRealm().equals(Realm.WHITE)){
            getForgottenRealmDice()[i].setDiceStatus(DiceStatus.TURN_SELECTED); //TODO: test line
        }
        makeMove(getPassivePlayer(), move);
        return move;}
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
                    int i;
                    System.out.println(player.getScoreSheet().getDragon());
                    System.out.println("Select a dragon to attack with attack value: " + move.getDice().getValue());
                    if(player.getPlayerType().equals("human")){
                        i = takeNumberInput();
                        while(i<1 || i>4){
                            System.out.println("Please enter a valid number");
                            i = takeNumberInput();
                        }
                    }
                    else {
                        i=(int)(Math.random()*4)+1;
                        
                    }
                    
                    RedDice d = new RedDice(move.getDice().getValue());
                    d.selectsDragon(i);
                    try {
                        b = (player.getScoreSheet().getDragon().makeMove(new Move(d, player.getScoreSheet().getDragon())));
                    }
                    catch (PlayerActionException e) {
                        // if(player.getPlayerType().equals("human")){
                        System.out.println(e.getMessage());
                        //}
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
                    int q;
                    if(player.getPlayerType().equals("human")){
                        q = takeNumberInput();
                        while(q<0 || q>4){
                            System.out.println("Please enter a valid number");
                            q = takeNumberInput();
                        }
                    }
                    else{
                        if(player.getScoreSheet().getDragon().getPossibleMovesForADie(new RedDice(v)).length>0)
                                    q=0;
                                else
                                    if(v>=4 && player.getScoreSheet().getLion().getAllPossibleMoves().length>0)
                                        q=4;
                                
                                        else
                                
                                        if( player.getScoreSheet().getPhoenix().getPossibleMovesForADie(new MagentaDice(v)).length>0)
                                            q=3;
                                        else
                                            if(player.getScoreSheet().getHydra().getPossibleMovesForADie(new BlueDice(v)).length>0)
                                                q=2;
                                             else
                                                if(player.getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(v + getAllDice()[1].getValue())).length>0)
                                                    q=1;
                                            else   
                                                if( player.getScoreSheet().getLion().getAllPossibleMoves().length>0)
                                                    q=4;
                                                else
                                                    q=(int)(Math.random() * 4) +1;

                        System.out.println(q);
                    }
                    // System.out.println(q);
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
        for (Dice d : dice){
            if(getPossibleMovesForADie(player, d).length > 0){
                return true;
            }
        }
        return false;
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
        
        System.out.println("Welcome to Dice Realms: Quest for Elemental Crests!!!");
        System.out.println("Enter the number of players: (0, 1 or 2)");
        int i = takeNumberInput();
        while(i!=0&&i!=1 && i !=2){
            i = takeNumberInput();
        }
        if(i==2) {
            gameBoard = new GameBoard( i);
            System.out.println("Enter Player 1 name");
            String s1 = scanner.nextLine();
            System.out.println("Enter Player 2 name");
            String s2 = scanner.nextLine();
            gameBoard.setPlayer1(new HumanPlayer(s1, PlayerStatus.ACTIVE));
            gameBoard.setPlayer2(new HumanPlayer(s2, PlayerStatus.PASSIVE));
        }
        else {if(i==1){
            gameBoard = new GameBoard(i);
            System.out.println("Enter your name");
            String s1 = scanner.nextLine();
            String s2 ="COMPUTER";
            gameBoard.setPlayer1(new HumanPlayer(s1, PlayerStatus.ACTIVE));
            gameBoard.setPlayer2(new ComputerPlayer(s2, PlayerStatus.PASSIVE));
        }
        else{
            gameBoard = new GameBoard(i);
            String s1 = "COMPUTER1";
            String s2 ="COMPUTER2";
            gameBoard.setPlayer1(new ComputerPlayer(s1, PlayerStatus.ACTIVE));
            gameBoard.setPlayer2(new ComputerPlayer(s2, PlayerStatus.PASSIVE));
        }
        }
//        Dice d = new MagentaDice(5);
//        Move m = new Move(d,getActivePlayer().getScoreSheet().getPhoenix());
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        RedDice s = new RedDice(1);
//        s.selectsDragon(1);
//        m = new Move(s,getActivePlayer().getScoreSheet().getDragon());
//        makeMove(getActivePlayer(),m);
//        s.selectsDragon(2);
//        m = new Move(s,getActivePlayer().getScoreSheet().getDragon());
//        makeMove(getActivePlayer(),m);
//        s.selectsDragon(1);
//        m = new Move(s,getActivePlayer().getScoreSheet().getDragon());
//        makeMove(getActivePlayer(),m);
//        s.selectsDragon(2);
//        m = new Move(s,getActivePlayer().getScoreSheet().getDragon());
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        d = new BlueDice(6);
//        m = new Move(d,getActivePlayer().getScoreSheet().getHydra());
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
//        makeMove(getActivePlayer(),m);
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
                    int i;
                    if (getGameStatus().getTurn() == 4 || !thereAreAvailableDice()) {                       
                        System.out.println("\n"+getActivePlayer().getPlayerName()+", your time as active spellcaster is over. Press 1 to display Grimoire, 0 to end turn.");
                        if(getActivePlayer().getPlayerType().equals("human")){                          
                            i = takeNumberInput();
                        }
                        else{
                            i=1;
                        }
                        while(i!=0 && i!=1){
                            System.out.println("Enter a valid number");
                            i = takeNumberInput();
                        }
                        if (i == 1) {
                            System.out.println("\n" + getActivePlayer().getPlayerName().toUpperCase() + "'S GRIMOIRE:");
                            System.out.println(getActivePlayer().getScoreSheet());
                        }
                    }
                }
                int i;
                System.out.println("\n"+getPassivePlayer().getPlayerName()+", you must choose a die from the Forgotten Realm.");
                System.out.println("Enter 1 to display Grimoire, 0 to proceed.");
                if(getPassivePlayer().getPlayerType().equals("human")){
                     i = takeNumberInput();
                }
                else {
                    i=1;
                }

                while(i!=0 && i!=1){
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
            int i;
        if(getActivePlayer().getPlayerType().equals("human")){
             i = takeNumberInput();
        }
        else{
            i=1;
        }
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
        else if(score2 > score1)
            System.out.println(getGameBoard().getPlayer2().getPlayerName() + " has won the game!");
        else
            System.out.println("It's a draw!");
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
            int i=0;
            if(getActivePlayer().getPlayerType().equals("human")){
                 i = takeNumberInput();
            }
            else{ 
                // if(getAvailableDice().length>=0){
                // boolean flag=false;
                // for(int j=0;j<getAvailableDice().length;j++){
                //     if(getAvailableDice()[j].getValue()>=2){
                //         flag=true;
                //     }
                // }
                // if (flag)
                //     i=0;
                // else
                //     i=1;    
                // }
                    i = 0;
                    if(getPossibleMovesForAvailableDice(getActivePlayer()).length == 0)
                        i = 1;
                    System.out.println(i);
            }
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
            int i=0;
            if(getActivePlayer().getArcaneBoostCount() == 1) {
                s = " Arcane Boost";
            }
            else {
                s = " Arcane Boosts";
            }
            System.out.println("\n"+getActivePlayer().getPlayerName() + " : You have "+ getActivePlayer().getArcaneBoostCount()+s);
            System.out.println("Press 1 to use Arcane Boost, 0 to proceed");
            if(getActivePlayer().getPlayerType().equals("human")){
                i = takeNumberInput();
            }
            else{
                
                i=1;
            }
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
            int j=0;
            if(getPassivePlayer().getArcaneBoostCount() == 1)
                h = " Arcane Boost";
            else
                h = " Arcane Boosts";
            System.out.println("\n"+getPassivePlayer().getPlayerName() + " : You have "+ getPassivePlayer().getArcaneBoostCount()+h);
            System.out.println("Press 1 to use Arcane Boost, 0 to proceed");
            if(getPassivePlayer().getPlayerType().equals("human")){
                j = takeNumberInput();
            }
            else{
                j=1;
                // for(int i=0;i<getArcaneBoostDice().length;i++){
                //     if(getArcaneBoostDice()[i].getValue()>3 || getGameStatus().getRound()==6){
                //         j=1;
                //         break;
                //     }
                // }
                System.out.println(j);
            }
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
    public void useArcaneBoost(Player player)  {
        int i;
        if(player.getPlayerType().equals("human")){
            i = takeNumberInput();
        }
        else{
            //i=(int)(Math.random()*getArcaneBoostDice().length);
            //System.out.println(i);
            int r=FindRealm(getArcaneBoostDice(),Realm.RED);
            int g=FindRealm(getArcaneBoostDice(),Realm.GREEN);
            int b=FindRealm(getArcaneBoostDice(),Realm.BLUE);
            int m=FindRealm(getArcaneBoostDice(),Realm.MAGENTA);
            int y=FindRealm(getArcaneBoostDice(),Realm.YELLOW);
            int w=FindRealm(getArcaneBoostDice(),Realm.WHITE);
            if((y!=-1) && (getArcaneBoostDice()[y].getValue()>=4) && (getPossibleMovesForADie(player,getArcaneBoostDice()[y]).length>0))
               i=y;
               else
                    if((r!=-1) && (getPossibleMovesForADie(player,getArcaneBoostDice()[r]).length>0))
                        i=r;
                    else  
                        if((w!=-1) && (getArcaneBoostDice()[w].getValue()>=5) && (getPossibleMovesForADie(player,getArcaneBoostDice()[w]).length>0))
                            i=w;
                        else 
                        
                        if((g!=-1)  && (getPossibleMovesForADie(player,getArcaneBoostDice()[g]).length>0))
                            i=g;
                            else
                            if((m!=-1) && (getArcaneBoostDice()[m].getValue()>=5) && (getPossibleMovesForADie(player,getArcaneBoostDice()[m]).length>0))
                                i=m;
                            else{
                                do {
                                    i=(int)(Math.random()*getArcaneBoostDice().length);
                                }
                                while(getPossibleMovesForADie(player,getArcaneBoostDice()[i]).length==0);
                            }
                                System.out.println(i);
                            // if(getArcaneBoostDice().length==6){
            //     if((getPossibleMovesForADie(player,getArcaneBoostDice()[5]).length!=0) && (getArcaneBoostDice()[5].getValue()>=getArcaneBoostDice()[4].getValue()) && (getArcaneBoostDice()[5].getValue()>=getArcaneBoostDice()[3].getValue())){
            //         i=5;
            //     }
            //     else{
            //         if((getPossibleMovesForADie(player,getArcaneBoostDice()[4]).length>0) && (getArcaneBoostDice()[4].getValue()>=getArcaneBoostDice()[3].getValue())){
            //             i=4;}
            //             else{
            //                 if(getPossibleMovesForADie(player,getArcaneBoostDice()[3]).length>0){
            //                     i=3;
            //                 }    
            //             }            
            //         }
            //     }
            // else{
            // //    // while(getPossibleMovesForADie(player, getArcaneBoostDice()[i]).length==0){
            //          i=(int)(Math.random()*getArcaneBoostDice().length);
            // //     }
            // // }
            // System.out.println(i);
        }
    
        
    
        while(i<0 || i>= getArcaneBoostDice().length){
            System.out.println("Please enter a valid number");
            i = takeNumberInput();
        }
        Dice d = getArcaneBoostDice()[i];
        player.subtractArcaneBoostCount();
        Move m = new Move(d, player.getScoreSheet().getCreatureByRealm(d), MoveType.ARCANE_BOOST);
        if(!getArcaneBoostDice()[i].getRealm().equals(Realm.WHITE)){
            getArcaneBoostDice()[i].setDiceStatus(DiceStatus.POWER_SELECTED); // TODO: test line
        }
        makeMove(player, m);
        //getAllDice()[i].setDiceStatus(DiceStatus.POWER_SELECTED);
        //getBonus(checkReward(m, player), player);

    }
    public void useEssenceBonus(){
        int i;
        System.out.println("Select a realm to attack");
        System.out.println("\u001B[31mR0\u001B[0m"+"  |  "+"\u001B[32mG1\u001B[0m"+"  |  "+"\u001B[34mB2\u001B[0m"+"  |  "+"\u001B[35mM3\u001B[0m"+"  |  "+"\u001B[33mY4\u001B[0m");
        if(getActivePlayer().getPlayerType().equals("human")){
            i = takeNumberInput();
            while(i<0 || i>4){
                System.out.println("Please enter a valid number");
                i = takeNumberInput();
            }
        }
        else{
            if(getActivePlayer().getScoreSheet().getLion().getAllPossibleMoves().length>0)
                i=4;
            else
                if(getActivePlayer().getScoreSheet().getDragon().getAllPossibleMoves().length>0)
                    i= 0; 
                    else  
                        if(getActivePlayer().getScoreSheet().getGaia().getAllPossibleMoves().length>0)
                            i=1;
                        else
                            if(getActivePlayer().getScoreSheet().getPhoenix().getAllPossibleMoves().length>0)
                                i=3;
                            else
                                i=2;
            System.out.println(i);
        }
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
            int att;
            Realm r = ((Bonus)reward).getRealm();
            System.out.println("Choose an attack value");
            if(player.getPlayerType().equals("human")){
                att = takeNumberInput();
           
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
        }
        else{
            switch(r){
                case YELLOW: case BLUE: case MAGENTA: 
                    att=6; break;    
                case GREEN:
                    att=13;
                    do{
                        att--;
                    }
                    while(player.getScoreSheet().getGaia().getPossibleMovesForADie(new GreenDice(att)).length==0 && att > 0);
                    break;
                  case RED:
                    att=7;
                    do{
                        att--;
                    }
                    while(getPossibleMovesForADie(player, new RedDice(att)).length==0 && att > 0);
                    break;
                              
                default:
                    att=(int)(Math.random()*6)+1;
                    break;
            }
            System.out.println(att);
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
                if(!possibleMovesForArrayOfDice(getPassivePlayer(), getForgottenRealmDice()))
                    break;
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
public int FindRealm(Dice[] dicearray,Realm realm){
    int j=-1;
    for(int i=0;i<dicearray.length;i++) {
        if(dicearray[i].getRealm()==realm){
            j=i;
            break;
        }
    }
return j;
}

}

