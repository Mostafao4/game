# Project Skeleton

## Folder Structure

```
Dice-Realms/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── game/
│   │   │       ├── collectibles/ 
│   │   │       │   ├── Reward.java 
|   |   |       |   ├── RewardType.java
│   │   │       │   ├── Power.java 
│   │   │       │   ├── Bonus.java 
│   │   │       │   ├── ElementalCrest.java 
│   │   │       │   ├── ArcaneBoost.java 
│   │   │       │   ├── TimeWarp.java 
│   │   │       │   └── EssenceBonus.java 
│   │   │       │ 
│   │   │       ├── creatures/ 
│   │   │       │   ├── Creature.java 
|   |   |       |   ├── Realm.java 
│   │   │       │   ├── Dragon.java 
│   │   │       │   ├── Gaia.java 
│   │   │       │   ├── Hydra.java 
│   │   │       │   ├── Phoenix.java 
│   │   │       │   └── Lion.java 
│   │   │       │ 
│   │   │       ├── dice/ 
│   │   │       │   ├── Dice.java 
|   |   |       |   ├── DiceStatus.java 
│   │   │       │   ├── RedDice.java 
│   │   │       │   ├── GreenDice.java 
│   │   │       │   ├── BlueDice.java 
│   │   │       │   ├── MagentaDice.java 
│   │   │       │   ├── YellowDice.java 
│   │   │       │   └── ArcanePrism.java 
│   │   │       │ 
│   │   │       ├── engine/  
│   │   │       │   ├── GameController.java 
│   │   │       │   ├── CLIGameController.java 
│   │   │       │   ├── GameBoard.java 
│   │   │       │   ├── Player.java 
|   |   |       |   ├── PlayerStatus.java 
│   │   │       │   ├── ScoreSheet.java 
│   │   │       │   ├── GameStatus.java 
│   │   │       │   ├── GameScore.java 
|   |   |       |   ├── Move.java 
│   │   │       │   └── MoveType.java 
│   │   │       │ 
│   │   │       ├── exceptions/
│   │   │       │   ├── RewardException.java
│   │   │       │   ├── PlayerActionException.java
│   │   │       │   ├── InvalidMoveException.java
│   │   │       │   ├── InvalidDiceSelectionException.java
│   │   │       │   ├── DiceRollException.java
│   │   │       │   ├── CommandFormatException.java
│   │   │       │   └── ExhaustedResourceException.java
│   │   │       ├── gui/
|   |   |       |   ├── Controller.java
|   |   |       |   ├── DiceFace.java
|   |   |       |   ├── DiceRealms.java
|   |   |       |   ├── MainmenuController.java
|   |   |       |   ├── ModesceneController.java
|   |   |       |   └── Table.java
│   │   │       │
│   │   │       └── Main.java
│   │   │
│   │   └── resources/
│   │       ├── images/
│   │       ├── config/
│   │       │   ├── EmberfallDominionDiceValue.properties
│   │       │   ├── EmberfallDominionScore.properties
│   │       │   ├── EmberfallDominionRewards.properties
│   │       │   ├── RoundsRewards.properties
│   │       │   ├── MysticalSkyRewards.properties
│   │       │   ├── TerrasHeartland.properties
│   │       │   ├── TerrasHeartlandRewards.properties
│   │       │   ├── RadiantSvannaMultipliers.properties
│   │       │   ├── RadiantSvannaRewards.properties
│   │       │   ├── TideAbyssRewards.properties
│   │       │   └── TideAbyssScore.properties
│   │       └── EmptyScoreSheet.txt
│   │
│   └── test/
│       └── java/
│           └── game/
│               ├── collectibles/
│               ├── creatures/
│               ├── dice/
│               ├── engine/
|               |   └── CLIGameControllerTest.java
│               ├── exceptions/
│               └── gui/
│
├── pom.xml
├── ProjectSkeleton.md
├── TemplateSkeleton.md
├── Grades.md
└── README.md
```

## Packages


### game.collectibles

This package includes classes for different types of game rewards and powers that players can collect:

- `Reward`: Abstract base class for all rewards.
- `RewardType`: Enum containing types of rewards. 
- `Power`: Abstract base class for power-up abilities.
- `Bonus`: Base class for the bonus abilities.
- `ArcaneBoost`, `TimeWarp`, `EssenceBonus`: Various collectible items of power-ups and bonuses that provide advantages in gameplay.
- `ElementalCrest`: A unique collectible item, which represent the pinnacle achievement and it is the quest of the game to collect them all.

### game.creatures

This package houses classes that represent various mythical creatures in the game:

- `Creature`: Abstract base class for all creatures.
- `Realm`: Enum containing all six realms in the game.
- `Dragon`, `Gaia`, `Hydra`, `Phoenix`, `Lion`: Specific creature classes with unique attributes and behaviors.

### game.dice

This package includes classes for different types of dice and their statuses:

- `Dice`: Abstract base class for all dice.
- `RedDice`, `GreenDice`, `BlueDice`, `MagentaDice`, `YellowDice`, `ArcanePrism`: Specific dice types used in the game, each with unique properties.
- `DiceStatus`: Enum containing possible status of the dice throughout the game.


### game.engine

This package serves as the core for all game mechanics, containing both abstract and concrete classes that manage game flow, player interactions, and the game board:

- `GameController`: Abstract class that sets the foundational methods for game controllers.
- `CLIGameController`: Extends GameController, implementing the CLI (Command Line Interface) version of the game controller.
- `GameBoard`: Manages the layout and state of the game board, including dice and players.
- `Player`: Represents a player in the game, managing their status, score, and actions.
- `PlayerStatus`: Enum that ontains possible statuses of player, either active or passive.
- `ScoreSheet`: Keeps track of a player's scores across different realms.
- `GameStatus`: Manages the current status of the game, including rounds and turns.
- `GameScore`: Details the scoring logic and current scores for players.
- `Move`: Represents a player's move, including dice and creature interactions.
- `MoveType`: Enum containing all move types a player can make.

### game.exceptions

This package defines custom exceptions to handle various error scenarios specifically related to game actions:

- `RewardException`
- `PlayerActionException`
- `InvalidMoveException`
- `InvalidDiceSelectionException`
- `DiceRollException`
- `CommandFormatException`
- `ExhaustedResourceException`

### game.gui

The `game.gui` package houses classes related to the graphical user interface (GUI) of the game. This includes components for rendering game graphics, handling user input, and managing the visual presentation of game elements.

- `Controller`: Extends GameController, implementing the GUI (Graphical User Interface) version of the game controller.
- `DiceFace`: Controls the display of the dice.
- `DiceRealms`: Starts the game in the GUI and contains the primary stage.
- `MainmenuController`: Contains the second scene in the GUI.
- `ModeseneController`: Contains the main scene in multiplayer mode.
- `Table`: Defines Table to be used in the scoresheets in GUI.

## Classes

For each package, add the skeleton details for the class and duplicate as much as needed. As an example, the `GameController.java` skeleton is provided as guideline.

### `GameController` class

- **Package**: `game.engine`
- **Type**: Abstract Class
- **Description**: This abstract class represents the controller for the game. It defines the common blueprint for different controllers used in the game.

#### Methods:

1. `void startGame()`

    - **Description**: Initializes necessary components and starts the game loop.

2. `boolean switchPlayer()`

    - **Description**: Switches the role of the current active player to passive and vice versa, ensuring that the turn-taking mechanism functions correctly.
    - **Return Type**: `boolean`
        - `true` if the switch was successful,
        - `false` otherwise.

3. `Dice[] rollDice()`

    - **Description**: Rolls all available dice for the current turn, assigning each a random number from 1 to 6.
    - **Return Type**: Array of `Dice`
        - An array of the currently rolled dice.

4. `Dice[] getAvailableDice()`

    - **Description**: Gets the dice available for rolling or rerolling.
    - **Return Type**: Array of `Dice`
        - An array of dice available for the current turn.

5. `Dice[] getAllDice()`

    - **Description**: Gets all six dice, providing their current state and value within the game regardless of their location or status.
    - **Return Type**: Array of `Dice`
        - An array of all six dice, with each die's state and value.

6. `Dice[] getForgottenRealmDice()`

    - **Description**: Gets the dice currently available in the Forgotten Realm.
    - **Return Type**: Array of `Dice`
        - An array of dice that are currently in the Forgotten Realm.

7. `Move[] getAllPossibleMoves()`

    - **Description**: Gets all possible moves for all currently rolled dice for the active player.
    - **Return Type**: Array of `Move`
        - An array of all possible moves for all rolled dice.

8. `Move[] getPossibleMoves(Dice dice)`

    - **Description**: Gets all possible moves for a given dice for the active player.
    - **Parameters**:
        - `dice`: The dice to determine possible moves for.
    - **Return Type**: Array of `Move`
        - An array of possible moves for the given dice.

9. `GameBoard getGameBoard()`

    - **Description**: Gets the current game board, including all players and all score sheets.
    - **Return Type**: `GameBoard`
        - The current game board object.

10. `Player getPlayer()`

    - **Description**: Gets the current active player's information.
    - **Return Type**: `Player`
        - The active player object.

11. `ScoreSheet getScoreSheet()`

    - **Description**: Gets the score sheet for the current active player.
    - **Return Type**: `ScoreSheet`
        - The score sheet object for the current active player.

12. `GameStatus getGameStatus()`

    - **Description**: Gets the current game status, including round and turn information for the current active player.
    - **Return Type**: `GameStatus`
        - The current game status object.

13. `GameScore getGameScore()`

    - **Description**: Gets the current score of the game, including scores in each realm, number of elemental crests, and the total score for the current active player.
    - **Return Type**: `GameScore`
        - The current game score object.

14. `TimeWarp getTimeWarpPowers()`

    - **Description**: Gets the number of TimeWarp powers the active player has and their status.
    - **Return Type**: `TimeWarp`
        - The TimeWarp object for the current active player.

15. `ArcaneBoost getArcaneBoostPowers()`

    - **Description**: Gets the number of ArcaneBoost powers the active player has and their status.
    - **Return Type**: `ArcaneBoost`
        - The ArcaneBoost object for the current active player.

16. `boolean selectDice(Dice dice)`

    - **Description**: Selects a dice and adds it to the current turn of the active player, moving all other dice with less value to the Forgotten Realm.
    - **Parameters**:
        - `dice`: The dice to be selected.
    - **Return Type**: `boolean`
        - `true` if the selection was successful,
        - `false` otherwise.

17. `boolean makeMove(Dice dice, Creature creature)`
    - **Description**: Executes a move using the selected dice on a specified creature.
    - **Parameters**:
        - `dice`: The dice selected by the active player for the move.
        - `creature`: The target creature that the move is against.
    - **Return Type**: `boolean`
        - `true` if the move is successfully completed,
        - `false` otherwise.


### `CLIGameController` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: Controls the command-line interface (CLI) interactions for the game.

#### Methods:

1. `Dice[] rollDice()`
    - **Description**: Rolls all available dice on the game board.
    - **Return Type**: `Dice[]`
        - Array of dice after rolling.

2. `Dice[] getAvailableDice()`
    - **Description**: Retrieves all available dice.
    - **Return Type**: `Dice[]`
        - Array of available dice.

3. `Dice[] getAllDice()`
    - **Description**: Retrieves all dice on the game board.
    - **Return Type**: `Dice[]`
        - Array of all dice.

4. `Dice[] getForgottenRealmDice()`
    - **Description**: Retrieves all dice in the forgotten realm.
    - **Return Type**: `Dice[]`
        - Array of dice in the forgotten realm.

5. `boolean selectDice(Dice dice, Player player)`
    - **Description**: Selects a dice for a player and sets others to the forgotten realm.
    - **Parameters**:
        - `dice`: The dice to be selected.
        - `player`: The player selecting the dice.
    - **Return Type**: `boolean`
        - `true` if the selection is successful, `false` otherwise.

6. `Move chooseDie()`
    - **Description**: Allows the active player to choose a die for their turn.
    - **Return Type**: `Move`
        - The move made by the player.

7. `Move chooseForgottenRealm()`
    - **Description**: Allows the passive player to choose a die from the forgotten realm.
    - **Return Type**: `Move`
        - The move made by the player.

8. `boolean thereAreAvailableDice()`
    - **Description**: Checks if there are available dice on the game board.
    - **Return Type**: `boolean`
        - `true` if there are available dice, `false` otherwise.

9. `static void printDice(Dice[] dice)`
    - **Description**: Prints the dice in different colors based on their realms.
    - **Parameters**:
        - `dice`: The array of dice to be printed.

10. `void resetDice()`
    - **Description**: Resets all dice on the game board to available status.

11. `Dice[] getArcaneBoostDice()`
    - **Description**: Retrieves all dice except those selected for power.
    - **Return Type**: `Dice[]`
        - Array of dice not selected for power.

12. `Move[] getAllPossibleMoves(Player player)`
    - **Description**: Retrieves all possible moves for a player across all realms.
    - **Parameters**:
        - `player`: The player for whom moves are to be calculated.
    - **Return Type**: `Move[]`
        - Array of all possible moves.

13. `Move[] getPossibleMovesForAvailableDice(Player player)`
    - **Description**: Retrieves possible moves for available dice considering specific conditions.
    - **Parameters**:
        - `player`: The player for whom moves are to be calculated.
    - **Return Type**: `Move[]`
        - Array of possible moves for available dice.

14. `Move[] getPossibleMovesForADie(Player player, Dice dice)`
    - **Description**: Retrieves possible moves for a specific die of a player.
    - **Parameters**:
        - `player`: The player for whom moves are to be calculated.
        - `dice`: The die for which moves are to be calculated.
    - **Return Type**: `Move[]`
        - Array of possible moves for the specified die.

15. `Move[] getPossibleMovesForADieHelper(Player player, Dice dice)`
    - **Description**: Helper method to calculate possible moves for a die under certain conditions.
    - **Parameters**:
        - `player`: The player for whom moves are to be calculated.
        - `dice`: The die for which moves are to be calculated.
    - **Return Type**: `Move[]`
        - Array of possible moves for the specified die.

16. `boolean makeMove(Player player, Move move)`
    - **Description**: Executes a move for a player and handles exceptions.
    - **Parameters**:
        - `player`: The player making the move.
        - `move`: The move to be executed.
    - **Return Type**: `boolean`
        - `true` if the move is successful, `false` otherwise.

17. `boolean possibleMovesForArrayOfDice(Player player, Dice[] dice)`
    - **Description**: Checks if there are possible moves for an array of dice for a player.
    - **Parameters**:
        - `player`: The player for whom moves are to be checked.
        - `dice`: The array of dice to be checked.
    - **Return Type**: `boolean`
        - `true` if there are possible moves for any of the dice, `false` otherwise.

18. `GameBoard getGameBoard()`
    - **Description**: Retrieves the game board.
    - **Return Type**: `GameBoard`
        - The game board.

19. `Player getActivePlayer()`
    - **Description**: Retrieves the active player.
    - **Return Type**: `Player`
        - The active player.

20. `Player getPassivePlayer()`
    - **Description**: Retrieves the passive player.
    - **Return Type**: `Player`
        - The passive player.

21. `ScoreSheet getScoreSheet(Player player)`
    - **Description**: Retrieves the score sheet of a player.
    - **Parameters**:
        - `player`: The player whose score sheet is to be retrieved.
    - **Return Type**: `ScoreSheet`
        - The score sheet of the specified player.

22. `GameStatus getGameStatus()`
    - **Description**: Retrieves the game status.
    - **Return Type**: `GameStatus`
        - The game status.

23. `GameScore getGameScore(Player player)`
    - **Description**: Retrieves the game score of a player.
    - **Parameters**:
        - `player`: The player whose game score is to be retrieved.
    - **Return Type**: `GameScore`
        - The game score of the specified player.

24. `TimeWarp[] getTimeWarpPowers(Player player)`
    - **Description**: Retrieves time warp powers for a player.
    - **Parameters**:
        - `player`: The player whose time warp powers are to be retrieved.
    - **Return Type**: `TimeWarp[]`
        - Array of time warp powers.

25. `ArcaneBoost[] getArcaneBoostPowers(Player player)`
    - **Description**: Retrieves arcane boost powers for a player.
    - **Parameters**:
        - `player`: The player whose arcane boost powers are to be retrieved.
    - **Return Type**: `ArcaneBoost[]`
        - Array of arcane boost powers.

26. `void startGame()`
    - **Description**: Initializes and starts the game loop.
    - **Execution**:
        - Resets the game board.
        - Initializes players.
        - Starts the game loop.

27. `boolean switchPlayer()`
    - **Description**: Switches the active and passive players.
    - **Return Type**: `boolean`
        - `true` if the switch is successful.
        - `false` otherwise.

28. `void gameLoop()`
    - **Description**: Manages the game loop.
    - **Execution**:
        - Executes rounds and turns until the end of the game.
        - Calls various helper methods to manage different aspects of the game.

29. `void startTurn()`
    - **Description**: Starts a new turn for the active player.
    - **Execution**:
        - Displays the round number, turn number, and active player.
        - Rolls dice and displays available dice for the player.

30. `void endTurn()`
    - **Description**: Ends the current turn.
    - **Execution**:
        - Resets dice and dragon selection.
        - Switches players.
        - Manages round and part of round counts.

31. `void endGame()`
    - **Description**: Ends the game and displays the result.
    - **Execution**:
        - Closes the scanner.
        - Calculates and displays the scores and the winner of the game.

32. `void timeWarpPrompt()`
    - **Description**: Prompts the active player to use Time Warp powers if available.
    - **Execution**:
        - Checks if the active player has Time Warp powers.
        - Prompts the player to use Time Warp powers if available, then rolls dice and prints available dice.

33. `void arcaneBoostPrompt()`
    - **Description**: Prompts players to use Arcane Boost powers if available.
    - **Execution**:
        - Checks if players have Arcane Boost powers.
        - Prompts players to use Arcane Boost powers if available, then allows them to select a die for the boost.

34. `void getRoundReward()`
    - **Description**: Grants rewards based on the current round.
    - **Execution**:
        - Grants Time Warp or Arcane Boost powers in alternating rounds.
        - Grants an Essence Bonus in the fourth round.

35. `void useBonus(Player player, Bonus bonus, int i)`
    - **Description**: Executes a bonus action for a player.
    - **Parameters**:
        - `player`: The player who receives the bonus.
        - `bonus`: The bonus to be used.
        - `i`: The index of the die to be used for the bonus.

36. `void useArcaneBoost(Player player)`
    - **Description**: Executes an Arcane Boost action for a player.
    - **Parameters**:
        - `player`: The player who uses the Arcane Boost.

37. `void useEssenceBonus()`
    - **Description**: Executes an Essence Bonus action.
    - **Execution**:
        - Prompts the active player to select a realm to attack.
        - Executes the selected bonus action.

38. `Reward[] checkReward(Move move, Player player)`
    - **Description**: Checks the rewards for a move.
    - **Parameters**:
        - `move`: The move to be checked.
        - `player`: The player who made the move.
    - **Return Type**: `Reward[]`
        - An array of rewards based on the move and player.

39. `void getReward(Reward[] r, Player player)`
    - **Description**: Grants rewards to a player.
    - **Parameters**:
        - `r`: An array of rewards to be granted.
        - `player`: The player who receives the rewards.

40. `void useReward(Reward reward, Player player)`
    - **Description**: Executes a reward action for a player.
    - **Parameters**:
        - `reward`: The reward to be used.
        - `player`: The player who receives the reward.

41. `void useBonusHelper(Reward reward, Player player)`
    - **Description**: Helper method to execute a bonus action for a player.
    - **Parameters**:
        - `reward`: The bonus to be used.
        - `player`: The player who receives the bonus.

42. `void chooseForgottenRealmHelper()`
    - **Description**: Helper method to choose a die from the Forgotten Realm.

43. `void chooseDieHelper()`
    - **Description**: Helper method to choose a die.

44. `int takeNumberInput()`
    - **Description**: Takes numeric input from the user.
    - **Return Type**: `int`
        - The number entered by the user.

45. `void invalidMoveHelper(Player player, Move move, Realm realm)`
    - **Description**: Handles invalid moves by suggesting alternative actions.
    - **Parameters**:
        - `player`: The player who made the invalid move.
        - `move`: The invalid move.
        - `realm`: The realm of the move.

### `Player` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents the 2 players' names, active or passive, and score.

#### Methods:

1. `PlayerStatus getPlayerStatus()`
    - **Description**: This method checks whether the player is currently active or passive.
    - **Return Type**: `PlayerStatus`
        - `ACTIVE` if the player is active
        - `PASSIVE` if the player is passive

2. `void setPlayerStatus(PlayerStatus playerStatus)`
    - **Description**: This method sets the status of the players wether active or passive.
    - **Return Type**: `void` does not return


3. `GameScore getGameScore()`
    - **Description**: This method gets the total game score of the player.
    - **Return Type**: `GameScore`
        

4. `void setGameScore()`
    - **Description**: This method sets the game score at the beginning of the game.
    - **Return Type**: `void` does not return
        

5. `String getPlayerName()`
    - **Description**: This method returns the name of the player.
    - **Return Type**: `String`
        - `String` Player name.
      

6. `void setPlayer_name(String playerName)`
    - **Description**: This method sets the name of the player when the game start.
    - **Return Type**: `void` does not return.
        

7. `int getTimeWarpCount()`
    - **Description**:  This method returns the number of TimeWarps the player has.
    - **Return Type**: `int`
        - `int` Number of TimeWarps.
            

8. `void subtractTimeWarpCount()`
    - **Description**: This method subtracts the number of TimeWarps the player has by 1.
    - **Return Type**: `void` does not return.


9. `void addTimeWarpCount()`
    - **Description**: This method adds the number of TimeWarps the player has by 1.
    - **Return Type**: `void` does not return.    


10. `int getArcaneBoostCount()`
    - **Description**: This method returns the number of Arcane Boost the player has.
    - **Return Type**: `int`
        - `int` Number of Arcane boosts.
       

11. `void addArcaneBoostCount()`
    - **Description**: This method adds the number of TimeWarps the player has by 1.
    - **Return Type**: `void` does not return.


12. `void subtractArcaneBoostCount()`
    - **Description**: This method subtracts the number of TimeWarps the player has by 1.
    - **Return Type**: `void` does not return.
        

13. `ElementalCrest[] getElementalCrest()`
    - **Description**: This method returns an array of the Elemental crests the player has.
    - **Return Type**: `ElementalCrest[]`
        - `ElementalCrest[]` array of Elemental crests.
    

14. `void addElementalCrest(Realm r)`
    - **Description**: This method takes the realm and adds the elemental Crest of this realm.
    - **Return Type**: `void` does not return.


15. `Dice getSelectedDice()`
    - **Description**: This method gets the dice the player choose.
    - **Return Type**: `Dice`
        - `Dice` The dice selected.

16. `void setSelectedDice(Dice selectedDice)`
    - **Description**: This method takes the dice the player selected and assign this dice.
    - **Return Type**: `void` does not return.
        

17. `ScoreSheet getScoreSheet()`
    - **Description**: This method returns the scoresheet of all realms for the player.
    - **Return Type**: `ScoreSheet`
        -`ScoreSheet` The scoresheets of the 5 realms.


### `GameBoard` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: Represents the game board, containing players, game status, and dice.

#### Methods:

1. `Dice[] getDice()`
    - **Description**: Gets the array of dice on the game board.
    - **Return Type**: `Dice[]`
        - Array of dice.

2. `void setDice(Dice[] dice)`
    - **Description**: Sets the array of dice on the game board.
    - **Parameters**:
        - `dice`: Array of dice to set.

3. `Player getPlayer1()`
    - **Description**: Gets player 1.
    - **Return Type**: `Player`
        - Player 1.

4. `Player getPlayer2()`
    - **Description**: Gets player 2.
    - **Return Type**: `Player`
        - Player 2.

5. `void setPlayer1(Player player1)`
    - **Description**: Sets player 1.
    - **Parameters**:
        - `player1`: Player 1 to set.

6. `void setPlayer2(Player player2)`
    - **Description**: Sets player 2.
    - **Parameters**:
        - `player2`: Player 2 to set.

7. `GameStatus getGameStatus()`
    - **Description**: Gets the current game status.
    - **Return Type**: `GameStatus`
        - Current game status.

8. `String diceToString()`
    - **Description**: Returns a string representation of all dice.
    - **Return Type**: `String`
        - String representation of the dice.


### `Move` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: Represents a move in the game, containing details about the dice, creature, dragon number, and move type.

#### Methods:

1. `int compareTo(Move o)`
    - **Description**: Compares this move to another move for ordering.
    - **Parameters**:
        - `o`: The other move to compare to.
    - **Return Type**: `int`
        - Negative if this move is less than `o`, zero if equal, positive if greater.

2. `Dice getDice()`
    - **Description**: Gets the dice associated with the move.
    - **Return Type**: `Dice`
        - The dice.

3. `void setDice(Dice dice)`
    - **Description**: Sets the dice for the move.
    - **Parameters**:
        - `dice`: The dice to set.

4. `boolean isWhiteMove()`
    - **Description**: Checks if the move is a white move.
    - **Return Type**: `boolean`
        - `true` if it is a white move, `false` otherwise.

5. `void setWhiteMove(boolean whiteMove)`
    - **Description**: Sets whether the move is a white move.
    - **Parameters**:
        - `whiteMove`: `true` to set as white move, `false` otherwise.

6. `MoveType getMoveType()`
    - **Description**: Gets the type of the move.
    - **Return Type**: `MoveType`
        - The move type.

7. `void setMoveType(MoveType moveType)`
    - **Description**: Sets the type of the move.
    - **Parameters**:
        - `moveType`: The move type to set.

8. `Creature getCreature()`
    - **Description**: Gets the creature associated with the move.
    - **Return Type**: `Creature`
        - The creature.

9. `void setCreature(Creature creature)`
    - **Description**: Sets the creature for the move.
    - **Parameters**:
        - `creature`: The creature to set.

10. `int getDragonNumber()`
    - **Description**: Gets the dragon number associated with the move.
    - **Return Type**: `int`
        - The dragon number.

11. `String toString()`
    - **Description**: Returns a string representation of the move.
    - **Return Type**: `String`
        - String representation of the move.

### `ScoreSheet` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: Represents the score sheet in the game, containing information about various creatures and their scores.

#### Methods:

1. `ScoreSheet()`
    - **Description**: Constructor that initializes the creatures in the score sheet.

2. `String toString()`
    - **Description**: Returns a string representation of the score sheet with colored text for each creature.
    - **Return Type**: `String`
        - String representation of the score sheet.

3. `Creature getCreatureByRealm(Dice dice)`
    - **Description**: Gets the creature associated with the realm of the given dice.
    - **Parameters**:
        - `dice`: The dice to determine the realm.
    - **Return Type**: `Creature`
        - The creature associated with the realm of the dice.

4. `Dragon getDragon()`
    - **Description**: Gets the dragon creature from the score sheet.
    - **Return Type**: `Dragon`
        - The dragon creature.

5. `Gaia getGaia()`
    - **Description**: Gets the gaia creature from the score sheet.
    - **Return Type**: `Gaia`
        - The gaia creature.

6. `Hydra getHydra()`
    - **Description**: Gets the hydra creature from the score sheet.
    - **Return Type**: `Hydra`
        - The hydra creature.

7. `Phoenix getPhoenix()`
    - **Description**: Gets the phoenix creature from the score sheet.
    - **Return Type**: `Phoenix`
        - The phoenix creature.

8. `Lion getLion()`
    - **Description**: Gets the lion creature from the score sheet.
    - **Return Type**: `Lion`
        - The lion creature.


### `GameStatus` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: Represents the status of the game, including the current round, turn, part of round, and game status.

#### Methods:

1. `String toString()`
    - **Description**: Returns a string representation of the game status, indicating the current round, turn, and game status.
    - **Return Type**: `String`
        - String representation of the game status.

2. `int getRound()`
    - **Description**: Gets the current round number.
    - **Return Type**: `int`
        - The current round number.

3. `void incrementRound()`
    - **Description**: Increments the round number and resets the turn number to 1.

4. `int getPartOfRound()`
    - **Description**: Gets the current part of the round.
    - **Return Type**: `int`
        - The current part of the round.

5. `void incrementPartOfRound()`
    - **Description**: Increments the part of the round.

6. `void resetPartofRound()`
    - **Description**: Resets the part of the round to 0.

7. `int getTurn()`
    - **Description**: Gets the current turn number.
    - **Return Type**: `int`
        - The current turn number.

8. `void incrementTurn()`
    - **Description**: Increments the turn number.

9. `void resetTurn()`
    - **Description**: Resets the turn number to 1.

10. `boolean isStatus()`
    - **Description**: Checks if the game is ongoing.
    - **Return Type**: `boolean`
        - `true` if the game is ongoing, `false` otherwise.

11. `void setStatus(boolean status)`
    - **Description**: Sets the status of the game.
    - **Parameters**:
        - `status`: `true` for ongoing game, `false` for game end.


### `GameScore` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: Represents the score calculation in the game, including the score sheet and elemental crests.

#### Methods:

1. `int getScore()`
    - **Description**: Calculates and returns the total score by summing up scores from all realms and adding bonuses from elemental crests.
    - **Return Type**: `int`
        - The total score.

2. `ScoreSheet getScoreSheet()`
    - **Description**: Gets the score sheet associated with the game score.
    - **Return Type**: `ScoreSheet`
        - The score sheet.

3. `int getRedRealmScore()`
    - **Description**: Gets the score from the red realm (Dragon).
    - **Return Type**: `int`
        - The score from the red realm.

4. `int getGreenRealmScore()`
    - **Description**: Gets the score from the green realm (Gaia).
    - **Return Type**: `int`
        - The score from the green realm.

5. `int getBlueRealmScore()`
    - **Description**: Gets the score from the blue realm (Hydra).
    - **Return Type**: `int`
        - The score from the blue realm.

6. `int getMagentaRealmScore()`
    - **Description**: Gets the score from the magenta realm (Phoenix).
    - **Return Type**: `int`
        - The score from the magenta realm.

7. `int getYellowRealmScore()`
    - **Description**: Gets the score from the yellow realm (Lion).
    - **Return Type**: `int`
        - The score from the yellow realm.

8. `ElementalCrest[] getElementalCrest()`
    - **Description**: Gets an array of elemental crests associated with the game score.
    - **Return Type**: `ElementalCrest[]`
        - Array of elemental crests.

9. `void addElementalCrest(ElementalCrest e)`
    - **Description**: Adds an elemental crest to the game score.
    - **Parameters**:
        - `e`: The elemental crest to add.

### `PlayerStatus` enum

- **Package**: `game.engine`
- **Type**: Enum
- **Description**: Represents the status of a player in the game, either active or passive.

### `MoveType` enum

- **Package**: `game.engine`
- **Type**: Enum
- **Description**: Represents the type of move in the game, including available moves, moves for forgotten realms, arcane boosts, bonuses, and essence bonuses.

### `Dice` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: This class represents the dice that will be rolled each turn.

#### Methods:

1. `void roll()`
    - **Description**: Rolls a die and sets its value between 1 & 6, inclusive.
    - **Return Type**: `void`

2. `int getValue()`

    - **Description**: Returns the value of the die.
    - **Return Type**: `int`
        - Value of the die.

3. `void setValue(int value)`

    - **Description**: Sets the value of the die to a given int.
    - **Parameters**:
        - `value`: The value of the die to be set.
    - **Return Type**: `void`

4. `Realm getRealm()`

    - **Description**: Returns the realm/color of the die.
    - **Return Type**: `Realm`
        - A realm from the six realms (RED,GREEN,BLUE,MAGENTA,YELLOW,WHITE).

5. `void setRealm(Realm realm)`

    - **Description**: Setter method to set the realm/color of a die to the given parameter.
    - **Parameters**:
        - `realm`: The realm that the die would be set to.
    - **Return Type**: `void`

6. `DiceStatus getDiceStatus()`

    - **Description**: Returns the dice statud of a given die.
    - **Return Type**: `DiceStatus`
        - Status of the die.

7. `void setDiceStatus(DiceStatus diceStatus)`

    - **Description**: Setter method to set a diceStatus to a die.
    - **Parameters**:
        - `diceStatus`: The dice status to be set.
    - **Return Type**: `void`

8. `String toString()`

    - **Description**: Abstract method overwrite to be able to print instances of each die.
    - **Return Type**: `String`

### `RedDice` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: Represents a red-colored dice in the game, associated with a dragon number.

#### Fields:

1. `int dragonNumber`
    - **Description**: Represents the number of the dragon associated with this red dice.

#### Methods:

1. `RedDice(int value)`
    - **Description**: Constructor for initializing a red dice with a specified value.
    - **Parameters**:
        - `value`: The initial value of the red dice.

2. `void selectsDragon(int i)`
    - **Description**: Sets the dragon number associated with this red dice.
    - **Parameters**:
        - `i`: The dragon number to set.

3. `int getDragonNumber()`
    - **Description**: Gets the dragon number associated with this red dice.
    - **Return Type**: `int`
        - The dragon number.

4. `void resetDragonNumber()`
    - **Description**: Resets the dragon number associated with this red dice to 0.

5. `String toString()`
    - **Description**: Returns a string representation of the red dice, including its value.
    - **Return Type**: `String`
        - String representation of the red dice.

### `GreenDice` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: Represents a green-colored dice in the game.

#### Methods:

1. `GreenDice(int value)`
    - **Description**: Constructor for initializing a green dice with a specified value.
    - **Parameters**:
        - `value`: The initial value of the green dice.

2. `String toString()`
    - **Description**: Returns a string representation of the green dice, including its value.
    - **Return Type**: `String`
        - String representation of the green dice.

### `BlueDice` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: Represents a blue-colored dice in the game.

#### Methods:

1. `BlueDice(int value)`
    - **Description**: Constructor for initializing a blue dice with a specified value.
    - **Parameters**:
        - `value`: The initial value of the blue dice.

2. `String toString()`
    - **Description**: Returns a string representation of the blue dice, including its value.
    - **Return Type**: `String`
        - String representation of the blue dice.

### `MagentaDice` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: Represents a magenta-colored dice in the game.

#### Methods:

1. `MagentaDice(int value)`
    - **Description**: Constructor for initializing a magenta dice with a specified value.
    - **Parameters**:
        - `value`: The initial value of the magenta dice.

2. `String toString()`
    - **Description**: Returns a string representation of the magenta dice, including its value.
    - **Return Type**: `String`
        - String representation of the magenta dice.

### `YellowDice` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: Represents a yellow-colored dice in the game.

#### Methods:

1. `YellowDice(int value)`
    - **Description**: Constructor for initializing a yellow dice with a specified value.
    - **Parameters**:
        - `value`: The initial value of the yellow dice.

2. `String toString()`
    - **Description**: Returns a string representation of the yellow dice, including its value.
    - **Return Type**: `String`
        - String representation of the yellow dice.

### `ArcanePrism` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: Represents an arcane prism (white-colored dice) in the game.

#### Methods:

1. `ArcanePrism(int value)`
    - **Description**: Constructor for initializing an arcane prism with a specified value.
    - **Parameters**:
        - `value`: The initial value of the arcane prism.

2. `boolean status()`
    - **Description**: Gets the status of the arcane prism.
    - **Return Type**: `boolean`
        - `true` if the arcane prism is active, `false` otherwise.

3. `String toString()`
    - **Description**: Returns a string representation of the arcane prism, including its value.
    - **Return Type**: `String`
        - String representation of the arcane prism.

### `DiceStatus` enum

- **Package**: `game.dice`
- **Type**: Enum
- **Description**: Represents the status of a dice in the game. Enum values: `AVAILABLE`, `TURN_SELECTED`, `POWER_SELECTED`, `FORGOTTEN_REALM`.



### `Creature` class

- **Package**: `game.creatures`
- **Type**: Abstract Class
- **Description**: This class represents all creatures of all realms.

### `Realm` enum

- **Package**: `game.collectibles`
- **Type**: Enum
- **Description**: This enum represents the possible realms of the creatures.

### `Pyroclast_Dragon` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: This class represents the Dragons of the Red Realm and their structure, hitpoints, and currently alive or dead.

#### Methods:


1. `Reward [] checkBonus() method`
    - **Description**: Checks whether the player gets the bonus or not after eliminating a common part in all dragons.
    - **Return Type**: Array of `Reward`
        - An array of reward that are collected after eliminating a common part in all dragons.
2. `Reward checkBonusHelper(String s) method`
    - **Description**: This method is called inside the checkBonus() method to return the required reward according to the configfile.
    - **Parameters**: String
        - `The string is the reward to be returned from the configfile.`:
    - **Return Type**: `Reward`
        - `The reward that is corrosponding to the parameter`
3. `int getPoints() method`
    - **Description**: To check whether the player will get the points or not after killing a dragon.
    - **Return Type**: `int`
        - `int` Score received after killing a dragon.0
4. `Move [] getAllPossibleMoves () method`
    - **Description**: The method get all the parts in every dragon that was not attacked.
    - **Return Type**: Array of `Move`
        - Array of move that have all the dragon parts that was not attacked.
5. `Move [] getAllPossibleMovesForADie () method`
    - **Description**: To change the hit area the player chose to zero to indicate that this place is already hit and if a part is hit the method return true and false otherwise.
    - **Return Type**: Array of `Move`
        - Array of move that have all the dragon parts that was not attacked for the specified die.
6. `boolean makeMove (Move move) method`
    - **Description**: To change the hit area the player chose to zero to indicate that this place is already hit and if a part is hit the method return true and false otherwise.
    - **Parameters**: 'Move'
        - `which contains the dice, the creature and the dragon number.`:
    - **Return Type**: `boolean`
        - `true` if the move is made
        - `false` if the move is not made
7. `String toString() method`
    - **Description**: The method returns the score sheet of the red realm and it uses helper methods to update the values inside the score sheet.
    - **Return Type**: `String`
        - `String` Score sheet of the red realm.
8. `String scoreSheetHelperDiceValue(int i, int j)`
    - **Description**: The method returns the dice value from the config file and it returns X if this dice value was attacked.
    - **Parameters**: `int`
        - `which contains the dice, the creature and the dragon number.`:
    - **Return Type**: `String`
        - `String` Dice value from the configfile
9. `String scoreSheetHelperScore(int j)`
    - **Description**: The method returns score from the config file and it returns X if the score is already taken.
    - **Parameters**: `int`
        - `j`: The number of column
    - **Return Type**: `String`
        - `String` Score from the configfile
10. `String scoreSheetHelperBonus(int i)`
    - **Description**: The method returns the bonus from the config file and it returns X if the score is already taken.
    - **Parameters**: `int`
        - `i`: The number of row.
    - **Return Type**: `String`
        - `String` Bonus from the configfile


### `Gaia_Guardian` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: This class represents the Guardians of the Green Realm and their structure, hit-points, and currently alive or dead.

#### Methods:

1. `int getGreenRealmScore()`
    - **Description**: Gets the current score by looking for the number of Gaia defeated.
    - **Return Type**: `int`
        - Represents the score from the green realm.

2.  `Reward showVerticalRewards()`
    - **Description**: Checks whether all Gaia Guardians in one column were defeated.
    - **Return Type**: `Reward`
        - Object that represents the reward that the player may earn after finishing a column.

3. `Reward getHorizontalBonus()`
    - **Description**: Checks whether all Gaia Guardians in one row were defeated.
    - **Return Type**: `Collectibles`
        - Object that represents the reward that the player may earn after finishing a row.

4. `Reward[] checkReward()`
    - **Description**: Checks all rewards earned, whether from columns or rows.
    - **Return Type**: `Reward[]`
        - Returns the rewards that the player earned in an array.

5.  `void readConfigRewards()`
    - **Description**: Reads the rewards configuration file.
    - **Return Type**: `void`

6.  `void loadRewards(String[] rewardsStrings, Reward[] rewards)`
    - **Description**: Assign the rewards read from the config file to their correct positions.
    - **Parameters**:
        - `rewardsStrings`: Array containing the names of all rewards.
        - `rewards` : Reward array containing the actual rewards.
    - **Return Type**: `void`

7.  `void readConfigScores()`
    - **Description**: Reads the green realm scoring from the config file.
    - **Return Type**: `void`

8.  `boolean attackGaia(Dice combined)`
    - **Description**: Attacks a gaia guardian.
    - **Parameters**:
        - `combined`: Die with combined value of green and white dice.
    - **Return Type**: `boolean`
        - Returns true if a gaia guardian was successfully attacked and false otherwise.

9.  `boolean makeMove(Move m)`
    - **Description**: Makes a move by calling the attackGaia() method.
    - **Parameters**:
        - `m`: Move m which contains the dice and creature.
    - **Return Type**: `boolean`
        - Returns true if a gaia guardian was successfully attacked and false otherwise.

10.  `Move[] getAllPossibleMoves()`
    - **Description**: Checks all moves possible in the green realm.
    - **Return Type**: `Move[]`
        - Array of moves containing all possible moves in green realm.

11.  `Move[] getPossibleMovesForADie(Dice die)`
    - **Description**: Checks for possible moves given a die.
    - **Parameters**:
        - `die`: Die to be compared to undefeated gaia guardians.
    - **Return Type**: `Move[]`
        - Array containing all possible moves given a specific die.

12.  `void editRewards()`
    - **Description**: Edits array of rewards in the green realm after every move made.
    - **Return Type**: `void`

13.  `int getDefeatedGaias()`
    - **Description**: Checks the number of gaias defeated.
    - **Return Type**: `int`
        - Number of gaias already defeated.

14.  `int getRemainingGaias()`
    - **Description**: Checks the number of gaias undefeated.
    - **Return Type**: `int`
        - Number of gaias still undefeated.

15.  `String toString()`
    - **Description**: Shows the current scoresheet of the green realm.
    - **Return Type**: `String`
        - Contains the current score sheet of the green realm.

16.  `String scoreSheetHelper(int rowNum)`
    - **Description**: Fills the scoresheet with an 'X' if the corresponding gaia guardian is defeated and with its number if not.
    - **Parameters**:
        - `rowNum`: The number of the row in the scoresheet.
    - **Return Type**: `String`
        - String representing the state of the gaia guardians.

17.  `String scoreSheetHelper2()`
    - **Description**: Fills the score row of the scoresheet.
    - **Return Type**: `String`
        - String representing the score guidelines given from the config file.

18.  `String scoreSheetHelper3()`
    - **Description**: Fills the last row containing the rewards.
    - **Return Type**: `String`
        - String representing the rewards after defeating a whole column.

19.  `String initialsRows(int num)`
    - **Description**: Gets the initials of the row rewards.
    - **Parameters**:
        - `num`: Integer to represent the index of the current row reward.
    - **Return Type**: `String`
        - String containing the first letter of each word from the reward name.

20.  `String initialsColumns(int num)`
    - **Description**: Gets the initials of the column rewards.
    - **Parameters**:
        - `num`: Integer to represent the index of the current column reward.
    - **Return Type**: `String`
        - String containing the first letter of each word from the reward name.

21.  `String getInitials(String reward)`
    - **Description**: Gets the initials of the rewards.
    - **Parameters**:
        - `reward`: String that represents the reward name.
    - **Return Type**: `String`
        - String containing the first letter of each word from the reward name.

### `Hydra` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: Represents a Hydra creature with multiple heads, scoring, and rewards mechanics in the game.

#### Methods:

1. `int getScore()`
    - **Description**: Returns the score based on the heads killed.
    - **Return Type**: `int`
        - The score based on the number of heads killed.

2. `Reward[] checkReward()`
    - **Description**: Checks and returns the rewards based on the number of heads killed.
    - **Return Type**: `Reward[]`
        - Array of rewards based on the heads killed.

3. `boolean makeMove(Move move) throws InvalidMoveException`
    - **Description**: Makes a move and updates the state of the hydra's heads.
    - **Parameters**:
        - `move`: The move to be made.
    - **Return Type**: `boolean`
        - `true` if the move is successful, `false` otherwise.
    - **Throws**: `InvalidMoveException`
        - If the move is invalid.

4. `Move[] getAllPossibleMoves()`
    - **Description**: Gets all possible moves for the hydra.
    - **Return Type**: `Move[]`
        - Array of all possible moves.

5. `Move[] getPossibleMovesForADie(Dice dice)`
    - **Description**: Gets possible moves for a specific die.
    - **Parameters**:
        - `dice`: The die to get possible moves for.
    - **Return Type**: `Move[]`
        - Array of possible moves for the specified die.

6. `void editReward()`
    - **Description**: Creates an array of rewards from the configuration file.
    - **Return Type**: `void`

7. `String toString()`
    - **Description**: Returns a string representation of the Hydra and its current state.
    - **Return Type**: `String`
        - The string representation of the Hydra.

8. `String currhydra(int i)`
    - **Description**: Determines which hydra is currently being attacked.
    - **Parameters**:
        - `i`: The index of the current head.
    - **Return Type**: `String`
        - The identifier of the current hydra.

9. `int headsKilled()`
    - **Description**: Returns the total number of heads killed.
    - **Return Type**: `int`
        - The total number of heads killed.

10. `String[] XinScoresheet()`
    - **Description**: Creates a string array indicating the state of each head in the score sheet.
    - **Return Type**: `String[]`
        - The state of each head in the score sheet.

11. `Move[] removeNullsAndFill(Move[] moves)`
    - **Description**: Removes nulls from the moves array and fills remaining slots with dummy moves.
    - **Parameters**:
        - `moves`: The array of moves.
    - **Return Type**: `Move[]`
        - The cleaned and filled array of moves.


### `Phoenix` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: Represents a Phoenix creature with scoring, attack mechanics, and rewards in the game.

#### Methods:

1. `boolean makeMove(Move move)`
    - **Description**: Makes a move and updates the state of the Phoenix's attacks.
    - **Parameters**:
        - `move`: The move to be made.
    - **Return Type**: `boolean`
        - `true` if the move is successful, `false` otherwise.
    - **Throws**: 
        - `InvalidMoveException`: If the maximum number of attacks has been reached.
        - `InvalidDiceSelectionException`: If the dice value is not greater than the previous attack.

2. `Reward[] checkReward()`
    - **Description**: Checks and returns the rewards based on the attacks made.
    - **Return Type**: `Reward[]`
        - Array of rewards based on the attacks made.

3. `Move[] getAllPossibleMoves()`
    - **Description**: Gets all possible moves for the Phoenix.
    - **Return Type**: `Move[]`
        - Array of all possible moves.

4. `Move[] getPossibleMovesForADie(Dice dice)`
    - **Description**: Gets possible moves for a specific die.
    - **Parameters**:
        - `dice`: The die to get possible moves for.
    - **Return Type**: `Move[]`
        - Array of possible moves for the specified die.

5. `int getMagentaRealmScore()`
    - **Description**: Returns the total score based on the attacks made in the Magenta realm.
    - **Return Type**: `int`
        - The total score based on the attacks made.

6. `String toString()`
    - **Description**: Returns a string representation of the Phoenix's current state, including attacks and rewards.


### `Solar_Lion` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: This class represents the Lions of the Yellow Realm and their structure, hitpoints, and currently alive or dead.

#### Methods: 

1. `void editReward`

    - **Description**: Creates array rew type string and adds rewards from config to it.
    - **Return Type**: `void` does not return.
     
2. `void editMultipliers()`

    - **Description**: Creates array mult type string and adds multipliers from config to it and edits multiplier array type int.
    - **Return Type**: `void` does not return.
   
3. `boolean makeMove(Move move)`

    - **Description**: increments hitnum(counter) & adds dice value to array diceNum.
    - **Parameters**:
        - `move`: The move to be perfromed.
    - **Return Type**: `boolean`
        - `true` if the move was successful,
        - `false` otherwise.

4. `int getYellowRealmScore()`

    - **Description**: adds the turns score to the total score and returns it.
    - **Return Type**: `int`
        - `int` total score of yellow realm.


5. `Reward getReward()`

    - **Description**: Checks rewards and returns the supposed reward & makes used reward an x.
    - **Return Type**: `Reward`
        - `Reward` The reward won after this hit.

6. ` Move[] getAllPossibleMoves()`

    - **Description**: gets an array type move with all the possible moves before rolling the dice.
    - **Return Type**: array of `Move`
        - `Move` array of all possible moves before rolling the dice.    

7. `Move[] getPossibleMovesForADie(Dice dice)`

    - **Description**: gets an array type move with all the possible moves after the dice is rolled.
    - **Parameters**:
        - `dice`: The dice after it is rolled.
    - **Return Type**: array of `Move`
        - `Move` array of all possible moves after rolling the dice.   

8. `String toString()`

    - **Description**: Returns the scoresheet.
    - **Return Type**: `String`
        - `String` scoresheet of yellow realm.


### `Reward` class
- **Package**: `game.collectibles`
- **Type**: Abstract Class
- **Description**: This abstract class serves as a blueprint for all the bonuses and boosts.
- 
- #### Methods:
- 
1. `RewardType getRewardType()`
    - **Description**: Returns the reward type.
    - **Return Type**: `RewardType`
        - `Type of reward`

### `Bonus` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Bonuses.

### `Power` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Powers.

### `Essence_Bonus` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Essence Bonus.

### `Arcane_Boost` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Arcane Boost power.

### `Timewarp` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Time Warp power and its status and how many the player has.

### `Elemental_Crest` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Elemental Crest collectibles and their status.

### `Controller` class
- **Package**: `game.gui`
- **Type**: Class
- **Description**: This class extends CLIGameController, and used as a game controller for the gui.
- 
- #### Methods:
- 
1. `void setPlayerNames(String player1, String player2)`
    - **Description**: Setter method to set player names.
    - **Parameters**:
        - `player1`: Name of the first player.
        - `player2`: Name of the second player.
    - **Return Type**: `void` does not return

2. `void roll()`
    - **Description**: Rolls the dice for the gui.
    - **Return Type**: `void` does not return

3. `void handleButtonPress(javafx.event.ActionEvent event)`
    - **Description**: JavaFX method to handle button press.
    - **Parameters**:
        - `event`: Event to execute when button is pressed.
    - **Return Type**: `void` does not return

4. `Button[] getButtons()`
    - **Description**: Returns array of buttons with a die in each index.
    - **Return Type**: `Button[]`
        -Array of type Button

5. `void highlightGreenPossibleMoves()`
    - **Description**: Highlights possible moves in the green realm in gui.
    - **Return Type**: `void` does not return

6. `void highlightBluePossibleMoves()`
    - **Description**: Highlights possible moves in the blue realm in gui.
    - **Return Type**: `void` does not return

### `DiceFace` class
- **Package**: `game.gui`
- **Type**: Class
- **Description**: This class represents the dice faces in the gui.
- 
- #### Methods:
- 
1. `void updateFace(int value)`
    - **Description**: Changes the appearance of the dice.
    - **Parameters**:
        - `value`: Value of the die.
    - **Return Type**: `void` does not return



